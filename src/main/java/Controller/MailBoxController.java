package Controller;

import Model.Database;
import Model.MailBox;
import View.MailBoxView;

/**
 * Controller for the mailbox view
 */
public class MailBoxController extends AbstractController {


    @Override
    protected void FillAllData() {
        ((MailBoxView)view).displayTable();
    }

    /**
     * Getter
     * @return user's mailbox
     */
    public MailBox getMailBox()
    {
        return getCurrentUser().mailBox;
    }

    /**
     * Go back to personal space
     */
    public void goBack()
    {
        viewChanger.personalArea();
        viewChanger.setupView(database);
    }

    @Override
    public void setDatabase(Database database) {
        super.setDatabase(database);
//        ((MailBoxView)view).displayTable();
    }

}
