package main.java.Controller;

import javafx.fxml.Initializable;
import main.java.Model.IModel;
import main.java.Model.User;
import main.java.View.IUserView;
import main.java.View.UserView;

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
}
