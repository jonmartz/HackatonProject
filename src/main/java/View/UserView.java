package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Controller.SettingsController;
import Controller.UserController;
import Model.IModel;

import java.io.IOException;

/**
 * Manages the transitions between the different views associated with user account management.
 */
public class UserView {
    private Stage stage;
    private FXMLLoader fxmlLoader;


    /**
     * Constructor
     *
     * @param stage of view
     */
    public UserView(Stage stage) {
        this.stage = stage;
    }

    /**
     * Gets the controller that was initialized after the fxml was loaded, and sets it's model and view pointers.
     */
    public void setupView(IModel userDatabase) {
        AbstractView abstractView = fxmlLoader.getController();
        abstractView.getController().setUserDatabase(userDatabase);
        abstractView.getController().setUserView(this);
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
            stage.setScene(new Scene(root, 500, 275));
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
     * Transitions to the user settings screen.
     */
    public void textBox() {
        try {
            fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("textBox.fxml").openStream());
            stage.setTitle("Text Box");
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

    public void fillTextInPersonalArea() {
        PersonalAreaView personalAreaView = fxmlLoader.getController();
        personalAreaView.fillTextsWithUser();
    }

    public void personalArea() {
        try {
            fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("personalArea.fxml").openStream());
            stage.setTitle("Parsonal Area");
            stage.setScene(new Scene(root, 300, 275));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
