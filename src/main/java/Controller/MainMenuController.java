package Controller;

import View.*;
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



    /**
     * The constructor
     */
    public MainMenuController() {}

    public void fillNameOfUserInMainMenu() {
        User currentUser= userDatabase.getCurrentUser();
        MainMenuView mainMenuView = (MainMenuView) view;
        mainMenuView.showUsernameCommend(currentUser.username);

    }

    /**
     * Transitions to the sign up screen
     */
    public void signUp() {
        userView.signUp();
        userView.setupView(userDatabase);
    }

    /**
     * Transitions to the user search window
     */
    public void searchUser()
    {
        userView.searchUser();
        userView.setupView(userDatabase);
    }

    public void signIn() {
        userView.signIn();
        userView.setupView(userDatabase);
    }

    /**
     * Transitions to the sign up screen
     */
    public void personalArea() {
        userView.personalArea();
        userView.setupView(userDatabase);
        userView.fillTextInPersonalArea();
    }






    @Override
    /**
     * This function will set the right view for this class
     */
    public void setView(AbstractView abstractView) {
        if (abstractView instanceof MainMenuView)
            super.setView(abstractView);
        else {
            super.setView(null);
        }
    }
}