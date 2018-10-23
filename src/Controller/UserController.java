package Controller;

import Model.UserDatabase;
import Model.User;
import View.UserView;
import javafx.fxml.Initializable;

/**
 * Abstract class for a controller that interacts with user account related views.
 */
public abstract class UserController implements Initializable {
    protected UserDatabase userDatabase;
    protected UserView userView;

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
    public void setUserDatabase(UserDatabase userDatabase) {
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
}
