package View;

import Controller.AcceptanceMessageController;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * View for the acceptance message screen
 */
public class AcceptanceMessageView extends MessageView {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AcceptanceMessageController acceptanceMessageController=new AcceptanceMessageController();
        this.setController(acceptanceMessageController);
        acceptanceMessageController.setView(this);
    }
}
