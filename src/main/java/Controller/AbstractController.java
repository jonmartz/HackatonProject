package Controller;

import Model.Database;
import Model.Vacation;
import View.AbstractView;
import Model.User;
import View.ViewChanger;
import View.DetailsVacationView;
import javafx.scene.control.ButtonType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Abstract class for a controller that interacts with user account related views.
 */
public abstract class AbstractController {

    protected Database database; //The model
    protected ViewChanger viewChanger; //the viewChanger - he class that responsible to display the fxml's
    protected AbstractView view;// The view assigned to the controller

    /**
     * Sets the controller's ViewChanger
     * @param viewChanger to set
     */
    public void setViewChanger(ViewChanger viewChanger) {
        this.viewChanger = viewChanger;
    }

    /**
     * This function will set the view
     * @param abstractView - The view
     */
    public void setView(AbstractView abstractView){
        this.view = abstractView;
    }
    /**
     * Sets the controller's model and then calls FillAllData that function needs the model to be set
     * @param database to set
     */
    public void setDatabase(Database database) {
        this.database = database;
        view.updateMainMenuButtons();
        FillAllData();
    }

    /**
     * Use the database to fill all necessary data in the current view
     */
    protected abstract void FillAllData();

    /**
     * Gets a user object from database
     * @param username of user
     * @return the user, or null if user doesn't exist.
     */
    public User getUser(String username) {
        return database.getUser(username);
    }

    /**
     * Updates the birthday string, after a date in the date picker has been picked.
     */
    public boolean isAgeLegal(LocalDate datePicked) {
        if (datePicked != null)
            return !datePicked.isAfter(LocalDate.now().minusYears(18));
        return false;
    }

    /**
     * Gets a vacation from database
     * @param vacationID of vacation
     * @return vacation object
     */
    public Vacation GetVacation(String vacationID) {
        return database.getVacation(vacationID);
    }


    /**
     * Gets all the vacations from database
     * @return list with all vacations
     */
    public ArrayList<Vacation> GetAllVacations() {
        return database.getAllVacations();
    }

    /**
     * Get a list of all countries in database
     * @return
     */
    public HashSet<String> GetAllCountries() {
        return database.getAllCountries();
    }

    /**
     * Transitions to the sign up screen
     */
    public void signIn() {
        viewChanger.signIn();
        viewChanger.setupView(database);
    }

    /**
     * signs user out and transitions to the sign up screen
     */
    public void signOut() {
        database.setCurrentUser(null);
        viewChanger.signIn();
        viewChanger.setupView(database);
    }

    /**
     * Transitions to the vacation search screen
     */
    public void vacationSearch() {
        setLastView("searchVacation");
        viewChanger.searchVacation();
        viewChanger.setupView(database);
    }

    /**
     * Transitions to vacation details
     * @param vacationID id of vacation to check
     */
    public void CheckVacation(String vacationID) {
        database.setCurrentVacation(GetVacation(vacationID));
        System.out.println(vacationID);
        viewChanger.detailsVacation();
        viewChanger.setupView(database);
        //todo: jump to vacation details screen
    }

    /**
     * Transitions to the vacation publishing screen
     */
    public void vacationPublish() {
        setLastView("publishVacation");
        if (getCurrentUser() == null){
            if (view.getResultFromWarning("You must sign in to publish a vacation. Sign in?") == ButtonType.YES)
                signIn();
        }
        else {
            viewChanger.publishVacation();
            viewChanger.setupView(database);
        }
    }

    /**
     * Transitions to the user search window
     */
    public void searchUser()
    {
        viewChanger.searchUser();
        viewChanger.setupView(database);
    }

    /**
     * Transitions to the user search window
     */
    public void personalArea() {
        viewChanger.personalArea();
        viewChanger.setupView(database);
    }

    /**
     * Returns the current user object
     * @return user object
     */
    public User getCurrentUser(){
        return database.getCurrentUser();
    }

    /**
     * Set the last view so after signing up, the user is taken back to the last view he was on
     * @param lastView to go back to after signing in
     */
    public void setLastView(String lastView) {
        viewChanger.setLastView(lastView);
    }
}
