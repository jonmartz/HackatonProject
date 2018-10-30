package Controller;

import View.AbstractView;
import javafx.scene.control.DatePicker;
import Model.IModel;
import Model.User;
import View.UserView;

import java.time.LocalDate;

/**
 * Abstract class for a controller that interacts with user account related views.
 */
public abstract class UserController {

    protected IModel userDatabase; //The model
    protected UserView userView; //the userView - he class that responsible to display the fxml's
    protected AbstractView view;// The view assigned to the controller

    /**
     * Sets the controller's UserView
     * @param userView to set
     */
    public void setUserView(UserView userView) {
        this.userView = userView;
    }

    /**
     * This function will set the view
     * @param abstractView - The view
     */
    public void setView(AbstractView abstractView){this.view = abstractView;};
    /**
     * Sets the controller's model
     * @param userDatabase to set
     */
    public void setUserDatabase(IModel userDatabase) {
        this.userDatabase = userDatabase;
    }

    /**
     * Gets a user object from database
     * @param username of user
     * @return the user, or null if user doesn't exist.
     */
    public User getUser(String username) {
        return userDatabase.getUser(username);
    }

    /**
     * Updates the birthday string, after a date in the date picker has been picked.
     */
    public boolean isAgeLegal(LocalDate datePicked) {
        if (datePicked != null)
            return !datePicked.isAfter(LocalDate.now().minusYears(18));
        return false;
    }
}
