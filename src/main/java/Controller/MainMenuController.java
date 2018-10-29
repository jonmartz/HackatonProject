package Controller;

import View.AbstractView;
import View.MainMenuView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import Model.User;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the main menu, where a new user can sign up or an existing user sign in.
 */
public class MainMenuController extends UserController {



    public MainMenuController()
    {

    }

    @Override
    public void setView(AbstractView abstractView) {
        if(abstractView instanceof MainMenuView)
            super.setView(abstractView);
        else
        {
            super.setView(null);
        }
    }



    /**
     * Transitions to the sign up screen
     */
    public void signUp() {
        userView.signUp();
        userView.setupView(userDatabase);
    }

    /**
     * Signs the user in, in case he exists and password is correct (right now it only opens user settings).
     */
    public void signIn() {
        User user = userDatabase.getUser(((MainMenuView)this.view).getUsernameText());
        if (user != null) {
            if (((MainMenuView)this.view).getPasswordText().equals(user.password)) {
                userDatabase.setCurrentUser(user);
                userView.settings();
                userView.setupView(userDatabase);
                userView.fillFieldsWithUserDetails();
            }
            else {
                ((MainMenuView)this.view).setComments("Password is incorrect!");

            }
        }
        else {
            ((MainMenuView)this.view).setComments("Username does not exist!");

        }
    }


}
