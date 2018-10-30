package View;

import Controller.MainMenuController;
import Controller.UserController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class is the view that is responsible for the Main menu window
 */
public class MainMenuView extends AbstractView {

    @FXML
    public TextField username; // The username textfield
    public TextField password; // The password textfield
    public Text comments; // Problems in user input are shown here
    public Button signIn; // The "SignIn" button

    /**
     * Ths functrion will assign the given controller to it self if it's the right one
     * @param controller - The given controller
     */
    public void setController(UserController controller){
        if(controller instanceof MainMenuController)
            super.setController(controller);
        else
        {
            super.setController(null);
        }
    }

    /**
     * Activates after user types in a text field, in order to enable/disable the sign in button
     * and write in the comments field.
     */
    public void KeyReleased() {
        try {
            if (username.getText().isEmpty() || password.getText().isEmpty()) {
                signIn.setDisable(true);
                comments.setText("Enter name and password");
            } else {
                signIn.setDisable(false);
                comments.setText("");
            }
        } catch (Exception e) {
            signIn.setDisable(true);
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


    @Override
    /**
     * This function will initialize an instance of this class
     */
    public void initialize(URL location, ResourceBundle resources) {
        //Assigning the view to the controller and backwards
        MainMenuController mainMenuController= new MainMenuController();
        this.setController(mainMenuController);
        mainMenuController.setView(this);

    }

    /**
     * This function will occur the SignIn button will be pressed
     */
    public void signIn()
    {
        ((MainMenuController)this.getController()).signIn();
    }
    /**
     * This function will occur the SignUp button will be pressed
     */
    public void signUp()
    {
        ((MainMenuController)this.getController()).signUp();
    }
}
