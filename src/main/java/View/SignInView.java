package View;

import Controller.SignInController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * View for the sign in screen
 */
public class SignInView extends AbstractView {

    @FXML
    public TextField username; // The username textfield
    public TextField password; // The password textfield
    public Text comments; // Problems in user input are shown here
    public Button signInButton; // The "SignIn" button

    /**
     * This function will initialize an instance of this class
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SignInController SignInController = new SignInController();
        this.setController(SignInController);
        SignInController.setView(this);
    }

    /**
     * Activates after user types in a text field, in order to enable/disable the sign in button
     * and write in the commentsText field.
     */
    public void KeyReleased() {
        try {
            if (username.getText().isEmpty() || password.getText().isEmpty()) {
                signInButton.setDisable(true);
                comments.setText("Enter name and password");
            } else {
                signInButton.setDisable(false);
                comments.setText("");
            }
        } catch (Exception e) {
            signInButton.setDisable(true);
            comments.setText("Enter name and password");
        }
    }

    /**
     * This function will return the username text
     * @return - The username text
     */
    public String getUsernameText(){
        return username.getText();
    }

    /**
     * This function will return the text in the passworf field
     * @return - The text in the password field
     */
    public String getPasswordText()
    {
        return this.password.getText();
    }

    /**
     * Tihs function will set the comment section
     * @param comment - The given comment
     */
    public void setComments(String comment)
    {
        comments.setText(comment);
    }

    /**
     * This function will occur the SignIn button will be pressed
     */
    public void signInUser() { ((SignInController)this.getController()).signInUser(); }

    /**
     * This function will occur the SignUp button will be pressed
     */
    public void signUp()
    {
        ((SignInController)this.getController()).signUp();
    }
}








