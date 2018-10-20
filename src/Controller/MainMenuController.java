package Controller;

import Users.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController extends UserController {

    @FXML
    public TextField username;
    public TextField password;
    public TextField comments;
    public Button signIn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

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

    public void signUp() {
        userView.signUp();
        userView.setupController(userDatabase);
    }

    public void signIn() {
        User user = userDatabase.getUser(username.getText());
        if (user != null) {
            if (password.getText().equals(user.password)) {
                // Add transition to user settings here
                System.out.println("Signed in");
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
