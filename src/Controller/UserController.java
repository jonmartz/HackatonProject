package Controller;

import Model.UserDatabase;
import Model.User;
import View.UserView;
import javafx.fxml.Initializable;

public abstract class UserController implements Initializable {
    protected UserDatabase userDatabase;
    protected UserView userView;
    public User user;

    public void setUserView(UserView userView) {
        this.userView = userView;
    }

    public void setUserDatabase(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    public User getUser(String username) {
        return userDatabase.getUser(username);
    }
}
