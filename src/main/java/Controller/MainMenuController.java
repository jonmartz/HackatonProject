package Controller;

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

    @FXML
    public TextField username;
    public TextField password;
    public Text comments; // Problems in user input are shown here
    public Button signIn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * Activates after user types in a text field, in order to enable/disable the sign in button
     * and write in the comments field.
     */
    public void KeyReleased() {
        try {
            if (username.getText().isEmpty() || password.getText().isEmpty()) {
                signIn.setDisable(true);
                comments.setText("Enter name and password");
            } else {
                signIn.setDisable(false);
                comments.setText("");
            }
        } catch (Exception e) {
            signIn.setDisable(true);
            comments.setText("Enter name and password");
        }
    }

    /**
     * Transitions to the sign up screen
     */
    public void signUp() {
        userView.signUp();
        userView.setupController(userDatabase);
    }

    /**
     * Signs the user in, in case he exists and password is correct (right now it only opens user settings).
     */
    public void signIn() {
        User user = userDatabase.getUser(username.getText());
        if (user != null) {
            if (password.getText().equals(user.password)) {
                userDatabase.setCurrentUser(user);
                userView.settings();
                userView.setupController(userDatabase);
                userView.fillFieldsWithUserDetails();
            }
            else {
                comments.setText("Password is incorrect!");
            }
        }
        else {
            comments.setText("Username does not exist!");
        }
    }
}
