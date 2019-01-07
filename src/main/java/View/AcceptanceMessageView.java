package View;

import Controller.AcceptanceMessageController;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class AcceptanceMessageView extends MessageView {
//    public Button confirmTradeButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AcceptanceMessageController acceptanceMessageController=new AcceptanceMessageController();
        this.setController(acceptanceMessageController);
        acceptanceMessageController.setView(this);
    }

//    public void confirmTrade()
//    {
//        ((AcceptanceMessageController)this.getController()).confirmTrade();
//    }
}
