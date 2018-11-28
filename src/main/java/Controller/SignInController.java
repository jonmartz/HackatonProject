package Controller;

import View.AbstractView;
import View.MainMenuView;
import View.SignInView;
import View.UserSearchView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import Model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class SignInController extends UserController {


    /**
     * The constructor
     */
    public SignInController()
    {

    }

    /**
     * This function will set the right view for this class
     */
    public void setView(AbstractView abstractView) {
        if (abstractView instanceof SignInView)
            super.setView(abstractView);
        else {
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

        //Get the user
        User user = userDatabase.getUser(((SignInView)this.view).getUsernameText());

        //If the user does exist
        if (user != null) {

            //If the password matches
            if (((SignInView)this.view).getPasswordText().equals(user.password)) {
                userDatabase.setCurrentUser(user);
                userView.settings();
                userView.setupView(userDatabase);
                userView.fillFieldsWithUserDetails();
            }
            else {
                ((SignInView)this.view).setComments("Password is incorrect!");

            }
        }
        //If the user does not exist
        else {
            ((SignInView)this.view).setComments("Username does not exist!");

        }
    }
}


