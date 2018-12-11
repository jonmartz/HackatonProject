package View;

import Controller.CompletionMessageController;

import java.net.URL;
import java.util.ResourceBundle;

public class CompletionMessageView  extends MessageView  {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CompletionMessageController completionMessageController =new CompletionMessageController();
        this.setController(completionMessageController);
        completionMessageController.setView(this);

    }
}
