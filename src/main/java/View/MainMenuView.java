package View;

import Controller.MainMenuController;
import Controller.SettingsController;
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
    public Button signIn;
    public Button signUp;
    public Button vacationSearch;
    public Button vacationAdvertising;
    public Button personalArea;
    public Button searchUser;
    public Text comments;

    private boolean alreadySignIn;
    public String currenUser;

    @Override
    /**
     * This function will initialize an instance of this class
     */
    public void initialize(URL location, ResourceBundle resources) {
        //Assigning the view to the controller and backwards
        MainMenuController mainMenuController= new MainMenuController();
        this.setController(mainMenuController);
        mainMenuController.setView(this);
        alreadySignIn = false;
    }


    /**
     * Activates after user types in a text field, in order to enable/disable the sign in button
     * and write in the comments field.
     */
    public void KeyReleased() {
        try{
            if(alreadySignIn) {
                comments.setText("Hellow " + currenUser + ", Welcome back!");
                signUp.setDisable(true);
                signIn.setDisable(true);
                personalArea.setDisable(false);
                vacationAdvertising.setDisable(false);
                searchUser.setDisable(false);
            }
            else {
                vacationAdvertising.setDisable(true);
                personalArea.setDisable(true);
                searchUser.setDisable(true);
            }
        }
        catch (Exception e) {
        }
    }
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
     * This function will fill the fields with the user's data
     */
    public void fillNameOfUserInMainMenu(){((MainMenuController)this.getController()).fillNameOfUserInMainMenu();}

    public void showUsernameCommend(String username){
        currenUser = username;
        alreadySignIn = true;
        KeyReleased();
    }


    public void signUp()
    {
        ((MainMenuController)this.getController()).signUp();
    }

    //public void setusername(String username)
      // {comments.setText("Hellow" + username + ", Welcome back!");}

    public void signIn()
    {
        ((MainMenuController)this.getController()).signIn();
    }

    /**
     * This function will occur when the "Search For User" button is pressed
     */
    public void searchUser()
    {
        ((MainMenuController)this.getController()).searchUser();
    }

    public void personalArea() {((MainMenuController)this.getController()).personalArea();}

}