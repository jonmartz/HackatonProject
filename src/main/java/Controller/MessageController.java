package Controller;

import Model.Database;
import View.MailBoxView;
import View.MessageView;

/**
 * Controller for the message views
 */
public class MessageController extends AbstractController {

    @Override
    protected void FillAllData() {
    }

    /**
     * return to mail box
     */
    public void goBack()
    {
        viewChanger.mailBox();
        viewChanger.setupView(database);
        this.setCurrentMessage(null);
    }

    @Override
    public void setDatabase(Database database) {
        super.setDatabase(database);
        ((MessageView)view).displayMessage();
    }

    /**
     * Delete the message from mail box
     * @param id of message
     */
    public void deleteMessage(int id)
    {
        database.deleteMessage(id);
        getCurrentUser().mailBox.removeMessage(id);
        goBack();
    }

}
