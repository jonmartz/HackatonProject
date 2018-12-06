package View;

import Controller.AbstractController;
import javafx.fxml.Initializable;

/**
 * This class represents an abstract view
 */
public abstract class AbstractView implements Initializable {

    private AbstractController abstractController; //The controller assigned to the view

    /**
     * This function will set the controller of the iew
     * @param controller - The given controller
     */
    public void setController(AbstractController controller){this.abstractController = controller;}

    /**
     * This function will return the controller
     * @return - The instance of the controller of ths view
     */
    public AbstractController getController(){return this.abstractController;}
}
