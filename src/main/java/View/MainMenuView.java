package View;

import Controller.MainMenuController;
import Controller.UserController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuView extends AbstractView {

    @FXML
    public TextField username;
    public TextField password;
    public Text comments; // Problems in user input are shown here
    public Button signIn;


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

    public String getUsernameText(){
        return username.getText();
    }

    public String getPasswordText()
    {
        return this.password.getText();
    }

    public void setComments(String comment)
    {
        comments.setText(comment);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MainMenuController mainMenuController= new MainMenuController();
        this.setController(mainMenuController);
        mainMenuController.setView(this);

    }
    public void signIn()
    {
        ((MainMenuController)this.getController()).signIn();
    }
    public void signUp()
    {
        ((MainMenuController)this.getController()).signUp();
    }
}
