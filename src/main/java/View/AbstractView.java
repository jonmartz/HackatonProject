package View;

import Controller.UserController;
import javafx.fxml.Initializable;

public abstract class AbstractView implements Initializable {
    private UserController userController;
    public void setController(UserController controller){this.userController = controller;}
    public UserController getController(){return this.userController;}
}
