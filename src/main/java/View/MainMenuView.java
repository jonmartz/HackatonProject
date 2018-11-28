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
}