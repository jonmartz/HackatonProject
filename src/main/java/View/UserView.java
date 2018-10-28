package main.java.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.Controller.SettingsController;
import main.java.Controller.UserController;
import main.java.Model.IModel;

import java.io.IOException;

/**
 * Manages the transitions between the different views associated with user account management.
 */
public class UserView  implements IUserView {
    private Stage stage;
    private FXMLLoader fxmlLoader;

    /**
     * Constructor
     * @param stage of view
     */
    public UserView(Stage stage) {
        this.stage = stage;
    }

    /**
     * Gets the controller that was initialized after the fxml was loaded, and sets it's model and view pointers.
     */
    public void setupController(IModel userDatabase) {
        UserController controller = fxmlLoader.getController();
        controller.setUserDatabase(userDatabase);
        controller.setUserView(this);
    }

    /**
     * Transitions to the main menu.
     */
    public void mainMenu() {
        try {
            fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("mainMenu.fxml").openStream());
            stage.setTitle("Welcome");
            stage.setScene(new Scene(root, 300, 275));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Transitions to the search user screen.
     */
    public void searchUser() {

        try {
            fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("userSearch.fxml").openStream());
            stage.setTitle("Search User");
            stage.setScene(new Scene(root, 500 , 275));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Transitions to the sign up screen.
     */
    public void signUp() {
        try {
            fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("signUp.fxml").openStream());
            stage.setTitle("Sign up");
            stage.setScene(new Scene(root, 300, 275));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Transitions to the user settings screen.
     */
    public void settings() {
        try {
            fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("settings.fxml").openStream());
            stage.setTitle("User Settings");
            stage.setScene(new Scene(root, 300, 275));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fills the user details in the user settings screen.
     */
    public void fillFieldsWithUserDetails() {
        SettingsController settingsController = fxmlLoader.getController();
        settingsController.fillFieldsWithUserDetails();
    }
}
