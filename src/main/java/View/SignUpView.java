package View;

import Controller.SignUpController;
import Controller.AbstractController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    public ImageView pictureImageView; // to show the profile picture
    public TextField phone;
    public TextField description;

    public String pictureFilePath; // path of picture file

    /**
     * This function will initialize an instance of this class
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SignUpController signUpController = new SignUpController();
        this.setController(signUpController);
        signUpController.setView(this);

        // Add listeners
        addTextListener(firstName, "[A-Za-z]*", "[^A-Za-z]");
        addTextListener(lastName, "[A-Za-z]*", "[^A-Za-z]");
        addTextListener(city, "[A-Za-z]*", "[^A-Za-z]");
        birthdatePicker.setValue(LocalDate.now().minusYears(18));
    }

    /**
     * Activates after user types in a text field, in order to enable/disable the sign in button
     * and write in the commentsText field.
     */
    public void KeyReleased() {
        try {
            if (username.getText().isEmpty()
                    || password.getText().isEmpty() || password.getText().length() < 8
                    || birthdate.isEmpty() || firstName.getText().isEmpty()
                    || lastName.getText().isEmpty() || city.getText().isEmpty()
                    || pictureFilePath.isEmpty()) {
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
     * Check if password >= 8
     */
    public void passwordTyped(){
        KeyReleased();
        String passwordString = password.getText();
        if (passwordString.isEmpty() || passwordString.length() < 8){
            comments.setText("Password must be at least 8 chars");
        }
    }

    /**
     * Ths functrion will assign the given controller to it self if it's the right one
     * @param controller - The given controller
     */
    public void setController(AbstractController controller) {
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
     * Getter for picture file
     * @return path of file
     */
    public String getPictureFilePath() { return pictureFilePath; }
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
     * @param birthdate to set
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
     * This function will occur when the "SignUp" button is pressed
     */
    public void signUp()
    {
        ((SignUpController)this.getController()).signUp();
    }

    /**
     * This function is called after clicking on the profile picture field, to add a profile picture.
     */
    public void AddPicture() { ((SignUpController)this.getController()).AddPicture(); }
}
