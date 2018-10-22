package Controller;

import Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class SettingsController extends UserController {

    @FXML
    public TextField username;
    public TextField password;
    public DatePicker birthdatePicker;
    public TextField firstName;
    public TextField lastName;
    public TextField city;
    public Button saveChanges;
    public String birthdate;
    public TextField comments;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        autofillFields();
    }

    private void autofillFields() {
        username.setText(user.username);
        password.setText(user.password);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(user.birthdate, formatter);
        birthdatePicker.setValue(localDate);

        firstName.setText(user.firstName);
        lastName.setText(user.lastName);
        city.setText(user.city);
    }

    public void KeyReleased() {
        try {
            if (username.getText().isEmpty() || password.getText().isEmpty() || birthdate.isEmpty()
                    || firstName.getText().isEmpty() || lastName.getText().isEmpty() || city.getText().isEmpty()) {
                saveChanges.setDisable(true);
                comments.setText("Please fill all fields");
            } else {
                saveChanges.setDisable(false);
                comments.setText("");
            }
        } catch (Exception e) {
            saveChanges.setDisable(true);
            comments.setText("Please fill all fields");
        }
    }

    public void birthdatePicked() {
        birthdate = birthdatePicker.getValue().toString();
        KeyReleased();
    }

    public void saveChanges() {
        if (user.username.equals(username.getText()) || userDatabase.getUser(username.getText()) == null) {
            if (!user.password.equals(password.getText()))
                userDatabase.updateUser(user.password,"password",password.getText());
            if (!user.birthdate.equals(birthdate))
                userDatabase.updateUser(user.birthdate,"birthdate",birthdate);
            if (!user.firstName.equals(firstName.getText()))
                userDatabase.updateUser(user.firstName,"firstName",firstName.getText());
            if (!user.lastName.equals(lastName.getText()))
                userDatabase.updateUser(user.lastName,"lastName",lastName.getText());
            if (!user.city.equals(city.getText()))
                userDatabase.updateUser(user.city,"city",city.getText());
            if (!user.username.equals(username.getText()))
                userDatabase.updateUser(user.username,"username",username.getText());
            //userView.mainMenu();
            //userView.setupController(userDatabase);
        }
        else {
            comments.setText("Username already exists!");
        }
    }

    public void mainMenu() {
        userView.mainMenu();
        userView.setupController(userDatabase);
        userView.loadUser(null);
    }
}
