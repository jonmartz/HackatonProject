package Controller;

import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import Model.IModel;
import Model.User;
import View.IUserView;
import View.UserView;

import java.time.LocalDate;

/**
 * Abstract class for a controller that interacts with user account related views.
 */
public abstract class UserController implements Initializable {
    protected IModel userDatabase;
    protected IUserView userView;

    /**
     * Sets the controller's view
     * @param userView to set
     */
    public void setUserView(UserView userView) {
        this.userView = userView;
    }

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
    public boolean isAgeLegal(DatePicker birthdatePicker) {
        LocalDate datePicked = birthdatePicker.getValue();
        if (datePicked != null)
            return !datePicked.isAfter(LocalDate.now().minusYears(8));
        return false;
    }
}
