package Controller;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void KeyReleased() {
        try {
            if (username.getText().isEmpty() || password.getText().isEmpty() || birthdate.isEmpty()
                    || firstName.getText().isEmpty() || lastName.getText().isEmpty() || city.getText().isEmpty()) {
                signUp.setDisable(true);
            } else signUp.setDisable(false);
        } catch (Exception e) {
            signUp.setDisable(true);
        }
    }

    public void birthdatePicked() {
        birthdate = birthdatePicker.getValue().toString();
        System.out.println(birthdate);
        KeyReleased();
    }

    public void signUp() {
        userDatabase.addUser(username.getText(), password.getText(), birthdate, firstName.getText(),
                lastName.getText(), city.getText());
        userView.mainMenu();
        userView.setupController(userDatabase);
    }

    public void addUser(String username, String password, String birthdate, String firstName,
                        String lastName, String city) {
        userDatabase.addUser(username, password, birthdate, firstName, lastName, city);
    }

    public void mainMenu() {
        userView.mainMenu();
        userView.setupController(userDatabase);
    }
}
