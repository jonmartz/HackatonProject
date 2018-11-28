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


    /**
     * The constructor
     */
    public MainMenuController() {

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