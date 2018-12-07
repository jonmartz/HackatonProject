package Controller;

import Model.Database;
import Model.Vacation;
import View.AbstractView;
import Model.User;
import View.ViewChanger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.time.LocalDate;
import java.util.ArrayList;

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
     * Sets the controller's model
     * @param database to set
     */
    public void setDatabase(Database database) {
        this.database = database;
        view.updateMainMenuButtons();

        if (this instanceof PersonalAreaController)
            ((PersonalAreaController) this).fillFieldsWithUserDetails();
    }

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
     * Gets all the vacations from database
     * @return list with all vacations
     */
    public ArrayList<Vacation> GetAllVacations() {
        return database.getAllVacations();
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
        viewChanger.searchVacation();
        viewChanger.setupView(database);
    }

    /**
     * Transitions to the vacation publishing screen
     */
    public void vacationPublish() {
        viewChanger.publishVacation();
        viewChanger.setupView(database);
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
     * Show warning and ask for user's confirmation
     * @param text of warning
     * @return user's answer
     */
    public ButtonType getResultFromWarning(String text){
        Alert alert = new Alert(Alert.AlertType.WARNING, text, ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        return alert.getResult();
    }

    /**
     * Returns the current user object
     * @return user object
     */
    public User getCurrentUser(){
        return database.getCurrentUser();
    }
}
