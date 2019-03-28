package Controller;

import Model.Database;
import Model.Message;
import Model.Vacation;
import View.AbstractView;
import Model.User;
import View.ViewChanger;
import javafx.scene.control.ButtonType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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

//    /**
//     * Gets a vacation from database
//     * @param vacationID of vacation
//     * @return vacation object
//     */
//    public Vacation GetVacation(String vacationID) {
//        return database.getVacation(vacationID);
//    }

    /**
     * Gets all the (where purchase is not accepted) vacations from database
     * @return list with all vacations
     */
    public HashSet<String> GetAllCourses() {
        return database.getAllCourses();
    }

//    /**
//     * Gets all the vacations that current user is owner of
//     * @return list with all vacations
//     */
//    public ArrayList<Vacation> GetAllVacationsOfCurrentUser() {
//        ArrayList<Vacation> vacations = database.getAllVacations();
//        String currentUserID = database.getCurrentUser().username;
//        ArrayList<Vacation> usersVacations = new ArrayList<>();
//        for (Vacation vacation : vacations){
//            if (vacation.ownerID.equals(currentUserID))
//                usersVacations.add(vacation);
//        }
//        return usersVacations;
//    }

//    /**
//     * Get a list of all countries in database
//     * @return
//     */
//    public HashSet<String> GetAllCountries() {
//        return database.getAllCountries();
//    }

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
    public void CheckUser(String vacationID) {
//        database.setCurrentVacation(GetVacation(vacationID));
        viewChanger.detailsVacation();
        viewChanger.setupView(database);
    }

//    /**
//     * Transitions to vacation request screen
//     * @param vacation vacation to check
//     */
//    public void RequestVacationScreen(Vacation vacation) {
//        database.setCurrentVacation(vacation);
//        viewChanger.requestVacation();
//        viewChanger.setupView(database);
//    }

    /**
     * Transitions to the vacation publishing screen
     */
    public void vacationPublish() {
        setLastView("publishVacation");
        if (getCurrentUser() == null){
            if (view.getResultFromWarning("You must sign in to publish. Go to sign in screen?") == ButtonType.YES)
                signIn();
        }
        else {
            viewChanger.publishVacation();
            viewChanger.setupView(database);
        }
    }

    public List<String> learningUsers(String course_id, String semester, String year ){
        return database.learningUsers( course_id, semester, year);
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
     * Transitions to the MailBox window
     */
    public void mailBox()
    {

        viewChanger.mailBox();
        viewChanger.setupView(database);

    }

    /**
     * Transitions to the MailBox window
     */
    public void message(String fxml,String kind)
    {

        viewChanger.messageView(fxml,kind);
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
     * Opens a window with the user's vacations
     */
    public void myVacations() {
        viewChanger.myVacations();
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
     * set the current message in database
     * @param currentMessage to set
     */
    public void setCurrentMessage(Message currentMessage)
    {
        this.database.setCurrentMessage(currentMessage);
    }

    /**
     * get the message that is currently being viewed
     * @return message
     */
    public Message getCurrentMessage(){
        return database.getCurrentMessage();
    }

    /**
     * Set the last view so after signing up, the user is taken back to the last view he was on
     * @param lastView to go back to after signing in
     */
    public void setLastView(String lastView) {
        viewChanger.setLastView(lastView);
    }
}
