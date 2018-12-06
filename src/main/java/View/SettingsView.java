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
/**
 * This class is the view that is responsible for the Settings window
 */
public class SettingsView extends AbstractView {


    @FXML
    public TextField username;//The username textfield
    public TextField password;//The password textfield
    public DatePicker birthdatePicker;//The birthdatePicker
    public TextField firstName;//The firstName textfield
    public TextField lastName;//The lastName textField
    public TextField city;//The city textfield
    public String birthdate; //The birthday value
    public Text comments; // Problems in user input are shown here
    public Button saveChanges;//The "SaveChanges" button


    @Override
    /**
     * This function will initialize an instance of this class
     */
    public void initialize(URL location, ResourceBundle resources) {
        SettingsController settingsController = new SettingsController();
        this.setController(settingsController);
        settingsController.setView(this);
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
     * Ths functrion will assign the given controller to it self if it's the right one
     * @param controller - The given controller
     */
    public void setController(UserController controller) {
        if (controller instanceof SettingsController)
            super.setController(controller);
        else {
            super.setController(null);
        }
    }

    /**
     * This function will fill the fields with the user's data
     */
    public void fillFieldsWithUserDetails(){((SettingsController)this.getController()).fillFieldsWithUserDetails();}

    /**
     * This function will return the birthday value
     * @return - The birthday value
     */
    public LocalDate getBirthdayValue()
    {
        return this.birthdatePicker.getValue();
    }

    /**
     * This function will set the username textfield
     * @param text - The username
     */
    public void setUsernameText(String text)
    {
        username.setText(text);
    }

    /**
     * This function will return the text in the password field
     * @return - The text in the password field
     */
    public String getPasswordText()
    {
        return password.getText();
    }

    /**
     * This function will return the text in the username field
     * @return - The text in the username field
     */
    public String getUsernameText()
    {
        return username.getText();
    }
    /**
     * This function will return the text in the firstName field
     * @return - The text in the firstName field
     */
    public String getFirstNameText()
    {
        return firstName.getText();
    }
    /**
     * This function will return the text in the lastName field
     * @return - The text in the lastName field
     */
    public String getLastNameText()
    {
        return lastName.getText();
    }
    /**
     * This function will return the text in the city field
     * @return - The text in the city field
     */
    public String getCityText()
    {
        return city.getText();
    }

    /**
     * This function will set the password textfield
     * @param text - The password
     */
    public void setPasswordText(String text)
    {
        password.setText(text);
    }
    /**
     * This function will set the firstName textfield
     * @param text - The firstName
     */
    public void setFirstNameText(String text)
    {
        firstName.setText(text);
    }
    /**
     * This function will set the lastName textfield
     * @param text - The lastName
     */
    public void setLastNameText(String text)
    {
        lastName.setText(text);
    }
    /**
     * This function will set the city textfield
     * @param text - The city
     */
    public void setCityText(String text)
    {
        city.setText(text);
    }

    /**
     * This function will set the value of the datePicker
     * @param localDate - The given date
     */
    public void setBirthdayValue(LocalDate localDate)
    {
        birthdatePicker.setValue(localDate);
    }

    /**
     * This function will set the value of the string birthday value
     * @param birthdate
     */
    public void setBirthdayString(String birthdate)
    {
        this.birthdate = birthdate;
    }

    /**
     * This function will return the value of the string birthday value
     * @return - The value of the birthday in String
     */
    public String getBirthdayString()
    {
        return this.birthdate;
    }

    /**
     * This function will set the comment text
     * @param comment - The given comment
     */
    public void setComments(String comment)
    {
        this.comments.setText(comment);
    }

    /**
     * This function will clear the text in the datePicker field
     */
    public void clearBirthdayPicker()
    {
        this.birthdatePicker.getEditor().clear();
    }

    /**
     * Ths function will occur when the user pick a date i the datePicker
     */
    public void birthdatePicked() {
        ((SettingsController)this.getController()).birthdatePicked();
    }

    /**
     * This function will occur when the "SaveChanges" button is pressed
     */
    public void saveChanges()
    {
        ((SettingsController)this.getController()).saveChanges();
    }

    /**
     * This function will occur when the "SignOut" button is pressed
     */
    public void mainMenu()
    {
        ((SettingsController)this.getController()).mainMenu();
    }

    /**
     * This function will occur when the "Search For User" button is pressed
     */
    public void searchUser()
    {
        ((SettingsController)this.getController()).searchUser();
    }

    /**
     * This function will occur when the Delete account button is pressed
     */
    public void deleteUser()
    {
        ((SettingsController)this.getController()).deleteUser();
    }
}