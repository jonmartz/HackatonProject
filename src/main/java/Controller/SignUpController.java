package Controller;

import Model.User;
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

    @FXML
    public TextField username;
    public TextField password;
    public DatePicker birthdatePicker;
    public TextField firstName;
    public TextField lastName;
    public TextField city;
    public Button signUp;
    public String birthdate;
    public Text comments; // Problems in user input are shown here

    @Override
    public void initialize(URL location, ResourceBundle resources) { }

    /**
     * Activates after user types in a text field, in order to enable/disable the sign in button
     * and write in the comments field.
     */
    public void KeyReleased() {
        try {
            if (username.getText().isEmpty() || password.getText().isEmpty() || birthdate.isEmpty()
                    || firstName.getText().isEmpty() || lastName.getText().isEmpty() || city.getText().isEmpty()) {
                signUp.setDisable(true);
                comments.setText("Please fill all fields");
            } else {
                signUp.setDisable(false);
                comments.setText("");
            }
        } catch (Exception e) {
            signUp.setDisable(true);
            comments.setText("Please fill all fields");
        }
    }

    /**
     * Updates the birthday string, after a date in the date picker has been picked.
     */
    public void birthdatePicked() {
        if (isAgeLegal(birthdatePicker)) {
            birthdate = birthdatePicker.getValue().toString();
            KeyReleased();
        } else {
            comments.setText("Age must be over 18 years");
            birthdatePicker.getEditor().clear();
        }
    }

    /**
     * Creates the new user's account, in case the username is available.
     */
    public void signUp() {
        User user = userDatabase.getUser(username.getText());
        if (user == null) {
            userDatabase.addUser(username.getText(), password.getText(), birthdate, firstName.getText(),
                    lastName.getText(), city.getText());
            userView.mainMenu();
            userView.setupController(userDatabase);
        }
        else {
            comments.setText("Username already exists!");
        }
    }

    /**
     * Transitions to the main menu.
     */
    public void mainMenu() {
        userView.mainMenu();
        userView.setupController(userDatabase);
    }
}
