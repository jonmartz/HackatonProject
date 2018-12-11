package View;

import Controller.AcceptanceMessageController;

import java.net.URL;
import java.util.ResourceBundle;

public class AcceptanceMessageView extends MessageView {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AcceptanceMessageController acceptanceMessageController=new AcceptanceMessageController();
        this.setController(acceptanceMessageController);
        acceptanceMessageController.setView(this);

    }

    public void payment()
    {
        ((AcceptanceMessageController)this.getController()).payment();
    }
}
