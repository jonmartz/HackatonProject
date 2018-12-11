package View;

import Controller.MessageController;
import Model.Message;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class MessageView extends AbstractView {

    public Text senderText;
    public Text dateText;
    public Text timeText;
    public Text headlineText;
    public Text contentText;


    public void displayMessage()
    {
        Message message = this.getController().getCurrentMessage();
        senderText.setText(message.getSender());
        dateText.setText(message.getDate());
        timeText.setText(message.getTime());
        headlineText.setText(message.getHeadline());
        contentText.setText(message.getContent());
    }

    public void goBack()
    {
        ((MessageController)this.getController()).goBack();
    }
    public void deleteMessage()
    {
        MessageController messageController = ((MessageController)this.getController());
        messageController.deleteMessage(messageController.getCurrentMessage().getId());
    }
}
