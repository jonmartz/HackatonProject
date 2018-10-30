package View;

import Controller.UserController;
import javafx.fxml.Initializable;

/**
 * This class represents an abstract view
 */
public abstract class AbstractView implements Initializable {

    private UserController userController; //The controller assigned to the view

    /**
     * This function will set the controller of the iew
     * @param controller - The given controller
     */
    public void setController(UserController controller){this.userController = controller;}

    /**
     * This function will return the controller
     * @return - The instance of the controller of ths view
     */
    public UserController getController(){return this.userController;}
}
