package Controller;

import Users.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

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
    public TextField comments;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

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

    public void birthdatePicked() {
        birthdate = birthdatePicker.getValue().toString();
        KeyReleased();
    }

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

    public void mainMenu() {
        userView.mainMenu();
        userView.setupController(userDatabase);
    }
}
