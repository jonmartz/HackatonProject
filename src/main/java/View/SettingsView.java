package View;

import Controller.SettingsController;
import Controller.UserController;
import Controller.UserSearchController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SettingsView extends AbstractView {


    @FXML
    public TextField username;
    public TextField password;
    public DatePicker birthdatePicker;
    public TextField firstName;
    public TextField lastName;
    public TextField city;
    public Button saveChanges;
    public String birthdate;
    public Text comments; // Problems in user input are shown here


    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

    public void setController(UserController controller) {
        if (controller instanceof SettingsController)
            super.setController(controller);
        else {
            super.setController(null);
        }
    }

    public LocalDate getBirthdayValue()
    {
        return this.birthdatePicker.getValue();
    }
    public void setUsernameText(String text)
    {
        username.setText(text);
    }

    public String getPasswordText()
    {
        return password.getText();
    }
    public String getUsernameText()
    {
        return username.getText();
    }
    public String getFirstNameText()
    {
        return firstName.getText();
    }
    public String getLastNameText()
    {
        return lastName.getText();
    }
    public String getCityText()
    {
        return city.getText();
    }
    public boolean isBirthDayEmpty()
    {
        return birthdate.isEmpty();
    }

    public void setPasswordText(String text)
    {
        password.setText(text);
    }
    public void setFirstNameText(String text)
    {
        firstName.setText(text);
    }
    public void setLastNameText(String text)
    {
        lastName.setText(text);
    }
    public void setCityText(String text)
    {
        city.setText(text);
    }

    public void setBirthdayValue(LocalDate localDate)
    {
        birthdatePicker.setValue(localDate);
    }

    public void setBirthdayString(String birthdate)
    {
        this.birthdate = birthdate;
    }
    public String getBirthdayString()
    {
        return this.birthdate;
    }
    public void setComments(String comment)
    {
        this.comments.setText(comment);
    }
    public void clearBirthdayPicker()
    {
        this.birthdatePicker.getEditor().clear();
    }
    public void birthdatePicked() {
        ((SettingsController)this.getController()).birthdatePicked();
    }
    public void saveChanges()
    {
        ((SettingsController)this.getController()).saveChanges();
    }
    public  void mainMenu()
    {
        ((SettingsController)this.getController()).mainMenu();
    }
    public void searchUser()
    {
        ((SettingsController)this.getController()).searchUser();
    }
    public void deleteUser()
    {
        ((SettingsController)this.getController()).deleteUser();
    }
}
