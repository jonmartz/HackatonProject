package Controller;

import Model.User;
import View.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Controller.UserController;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class PersonalAreaController extends UserController{


    public PersonalAreaController() {}
    @Override
    /**
     * This function will set the right view for this class
     */
    public void setView(AbstractView abstractView) {
        if (abstractView instanceof PersonalAreaView)
            super.setView(abstractView);
        else {
            super.setView(null);
        }
    }

    /**
     * Transitions to the sign up screen
     */
    public void textBox() {
        userView.textBox();
        userView.setupView(userDatabase);
    }


    /**
     * Transitions to the sign up screen
     */
    public void setting() {
        User user = userDatabase.getUser(((PersonalAreaView)this.view).userName);
        userDatabase.setCurrentUser(user);
        userView.settings();
        userView.setupView(userDatabase);
        userView.fillFieldsWithUserDetails();
    }



    /**
     * Deletes the user.
     */
    public void deleteUser() {
        userDatabase.deleteUser(userDatabase.getCurrentUser().username);
        mainMenu();
    }

    /**
     * Transitions to the main menu.
     */
    public void mainMenu() {
        userDatabase.setCurrentUser(null);
        userView.mainMenu();
        userView.setupView(userDatabase);
    }

    public void fillText() {
        User currentUser = userDatabase.getCurrentUser();
        PersonalAreaView personalAreaView = (PersonalAreaView) view;
        personalAreaView.fillTextsWithUserDetails(currentUser);

    }


    /**
     * This function will transit to the setting's window
     */
    public void goBack()
    {
        userView.mainMenu();
        userView.setupView(userDatabase);
        userView.fillNameOfUserInMainMenu();
    }

}