package Controller;

import Model.Database;
import Model.Vacation;
import View.AbstractView;
import Model.User;
import View.ViewChanger;

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
    public void setView(AbstractView abstractView){this.view = abstractView;};
    /**
     * Sets the controller's model
     * @param database to set
     */
    public void setDatabase(Database database) {
        this.database = database;
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
}
