package Controller;

import Model.Database;
import View.MailBoxView;
import View.MessageView;

public class MessageController extends AbstractController {

    @Override
    protected void FillAllData() {
    }

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
    public void deleteMessage(int id)
    {
        database.deleteMessage(id);
        getCurrentUser().mailBox.removeMessage(id);
        goBack();
    }

}
