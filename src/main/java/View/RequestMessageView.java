package View;

import Controller.RequestMessageController;

import java.net.URL;
import java.util.ResourceBundle;

public class RequestMessageView  extends MessageView  {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RequestMessageController requestMessageController = new RequestMessageController();
        this.setController(requestMessageController);
        requestMessageController.setView(this);

    }

    public void acceptRequest()
    {

        ((RequestMessageController)this.getController()).acceptRequest();
    }
}
