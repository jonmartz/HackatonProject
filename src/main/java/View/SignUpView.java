package View;

import Controller.MainMenuController;
import Controller.SignUpController;
import Controller.UserController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * This class is the view that is responsible for the SignUp window
 */
public class SignUpView extends AbstractView {

    @FXML
    public TextField username;//The username textfield
    public TextField password;//The password textfield
    public DatePicker birthdatePicker;//The birthdatePicker
    public TextField firstName;//The firstName textfield
    public TextField lastName;//The lastName textField
    public TextField city;//The city textfield
    public String birthdate; //The birthday value
    public Text comments; // Problems in user input are shown here
    public Button signUp;//The "SignUp" button


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


    @Override
    /**
     * This function will initialize an instance of this class
     */
    public void initialize(URL location, ResourceBundle resources) {
        SignUpController signUpController = new SignUpController();
        this.setController(signUpController);
        signUpController.setView(this);
    }
    /**
     * Ths functrion will assign the given controller to it self if it's the right one
     * @param controller - The given controller
     */
    public void setController(UserController controller) {
        if (controller instanceof SignUpController)
            super.setController(controller);
        else {
            super.setController(null);
        }
    }
    /**
     * This function will return the birthday value
     * @return - The birthday value
     */
    public LocalDate getBirthdayValue() {
        return this.birthdatePicker.getValue();
    }
    /**
     * This function will return the text in the password field
     * @return - The text in the password field
     */
    public String getPasswordText() {
        return password.getText();
    }
    /**
     * This function will return the text in the username field
     * @return - The text in the username field
     */
    public String getUsernameText() {
        return username.getText();
    }
    /**
     * This function will return the text in the firstName field
     * @return - The text in the firstName field
     */
    public String getFirstNameText() {
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
     * This function will set the value of the string birthday value
     * @param birthdate
     */
    public void setBirthdayString(String birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * This function will return the value of the string birthday value
     * @return - The value of the birthday in String
     */
    public String getBirthdayString() {
        return this.birthdate;
    }
    /**
     * This function will set the comment text
     * @param comment - The given comment
     */
    public void setComments(String comment) {
        this.comments.setText(comment);
    }
    /**
     * This function will clear the text in the datePicker field
     */
    public void clearBirthdayPicker() {
        this.birthdatePicker.getEditor().clear();
    }
    /**
     * Ths function will occur when the user pick a date i the datePicker
     */
    public void birthdatePicked()
    {
        ((SignUpController)this.getController()).birthdatePicked();
    }

    /**
     * This function will occur when the "Go Back" button is pressed
     */
    public void mainMenu()
    {
        ((SignUpController)this.getController()).mainMenu();
    }

    /**
     * This function will occur when the "SignUp" button is pressed
     */
    public void signUp()
    {
        ((SignUpController)this.getController()).signUp();
    }


}
