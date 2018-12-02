package Controller;

import Model.User;
import View.AbstractView;
import View.MainMenuView;
import View.SettingsView;
import View.SignUpView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Controller for the sign up screen, where a new user can create an account.
 */
public class SignUpController extends UserController {


    /**
     * The constructor
     */
    public SignUpController()
    {

    }

    /**
     * Updates the birthday string, after a date in the date picker has been picked.
     */
    public void birthdatePicked() {
        SignUpView signUpView = (SignUpView) view;
        LocalDate birthdayDate = signUpView.getBirthdayValue();
        if (isAgeLegal(birthdayDate)) {
            signUpView.setBirthdayString(birthdayDate.toString());
            signUpView.KeyReleased();
        } else {
            signUpView.setComments("Age must be over 18 years");
            signUpView.clearBirthdayPicker();
        }
    }


    /**
     * Creates the new user's account, in case the username is available.
     */
    public void signUp() {
        SignUpView signUpView = (SignUpView) view;
        User user = userDatabase.getUser(signUpView.getUsernameText());
        if (user == null) {
            userDatabase.addUser(signUpView.getUsernameText(), signUpView.getPasswordText(), signUpView.getBirthdayString(), signUpView.getFirstNameText(),
                    signUpView.getLastNameText(), signUpView.getCityText());
                 userView.signIn();
                 userView.setupView(userDatabase);
        }
        else {
            signUpView.setComments("Username already exists!");
        }
    }

    /**
     * Transitions to the main menu.
     */
    public void mainMenu() {
        userView.mainMenu();
        userView.setupView(userDatabase);
    }

    @Override
    /**
     * This function will set the right view for this class
     */
    public void setView(AbstractView abstractView) {

        if(abstractView instanceof SignUpView)
            super.setView(abstractView);
        else
        {
            super.setView(null);
        }
    }
}
