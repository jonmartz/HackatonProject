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

/**
 * Controller for the user settings screen, where the user can update account details or delete the account.
 */
public class SettingsController extends UserController{

    @FXML
    public TextField username;
    public TextField password;
    public DatePicker birthdatePicker;
    public TextField firstName;
    public TextField lastName;
    public TextField city;
    public Button saveChanges;
    public String birthdate;
    public TextField comments; // Problems in user input are shown here

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * Fills all fields with user details for the user to update his details more easily.
     */
    public void fillFieldsWithUserDetails() {
        User currentUser= userDatabase.getCurrentUser();
        username.setText(currentUser.username);
        password.setText(currentUser.password);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(currentUser.birthdate, formatter);
        birthdatePicker.setValue(localDate);

        firstName.setText(currentUser.firstName);
        lastName.setText(currentUser.lastName);
        city.setText(currentUser.city);

        KeyReleased();
    }

    /**
     * Activates after user types in a text field, in order to enable/disable the sign in button
     * and write in the comments field.
     */
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

    /**
     * Updates the birthday string, after a date in the date picker has been picked.
     */
    public void birthdatePicked() {
        birthdate = birthdatePicker.getValue().toString();
        KeyReleased();
    }

    /**
     * Saves user's details after he has updated them in the respective text fields.
     * Username is updated only if the new username is available.
     */
    public void saveChanges() {
        User user = userDatabase.getCurrentUser();
        if (user.username.equals(username.getText())
                || userDatabase.getUser(username.getText()) == null) {
            if (!user.password.equals(password.getText()))
                userDatabase.updateUser(user.username,"password",password.getText());
            if (!user.birthdate.equals(birthdate))
                userDatabase.updateUser(user.username,"birthdate",birthdate);
            if (!user.firstName.equals(firstName.getText()))
                userDatabase.updateUser(user.username,"firstName",firstName.getText());
            if (!user.lastName.equals(lastName.getText()))
                userDatabase.updateUser(user.username,"lastName",lastName.getText());
            if (!user.city.equals(city.getText()))
                userDatabase.updateUser(user.username,"city",city.getText());
            if (!user.username.equals(username.getText()))
                userDatabase.updateUser(user.username,"username",username.getText());
            // update the user pointer in the model to match the saved changes
            userDatabase.setCurrentUser(userDatabase.getUser(username.getText()));
        }
        else {
            comments.setText("Username already exists!");
        }
    }

    /**
     * Deletes the user.
     */
    public void deleteUser() {
        userDatabase.deleteUser(userDatabase.getCurrentUser().username);
        mainMenu();
    }

    /**
     * Transitions to the main menu.
     */
    public void mainMenu() {
        userDatabase.setCurrentUser(null);
        userView.mainMenu();
        userView.setupController(userDatabase);
    }
    public void searchUser()
    {
        userView.searchUser();
        userView.setupController(userDatabase);
    }


}
