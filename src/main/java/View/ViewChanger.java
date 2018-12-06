package View;

import Model.Database;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Manages the transitions between the different views associated with user account management.
 */
public class ViewChanger {
    private Stage stage;
    private FXMLLoader fxmlLoader;


    /**
     * Constructor
     * @param stage of view
     */
    public ViewChanger(Stage stage) {
        this.stage = stage;
    }

    /**
     * Gets the controller that was initialized after the fxml was loaded, and sets it's model and view pointers.
     */
    public void setupView(Database database) {
        AbstractView abstractView = fxmlLoader.getController();
        abstractView.getController().setDatabase(database);
        abstractView.getController().setViewChanger(this);
    }


    /**
     * Transitions to the main menu.
     */
    public void mainMenu() {
        try {
            fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("mainMenu.fxml").openStream());
            stage.setTitle("Welcome to Vacation4U");
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
            Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("userSearch.fxml").openStream());
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
            Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("signUp.fxml").openStream());
            stage.setTitle("Sign up");
            stage.setScene(new Scene(root, 300, 275));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void signIn() {
        try {
            fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("signIn.fxml").openStream());
            stage.setTitle("Sign In");
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
            Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("settings.fxml").openStream());
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
        SettingsView settingsView = fxmlLoader.getController();
        settingsView.fillFieldsWithUserDetails();
    }

    /**
     * Fills the user details in the user settings screen.
     */
    public void fillNameOfUserInMainMenu() {
        MainMenuView mainMenuView = fxmlLoader.getController();
        mainMenuView.fillNameOfUserInMainMenu();
    }


    public void searchVacation() {
        try {
            fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("vacationSearch.fxml").openStream());
            stage.setTitle("Vacation Search");
            stage.setScene(new Scene(root, 790, 430));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
