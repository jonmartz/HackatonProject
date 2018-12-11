package Controller;

import Model.Database;
import Model.MailBox;
import View.MailBoxView;

public class MailBoxController extends AbstractController {


    @Override
    protected void FillAllData() {

    }

    public MailBox getMailBox()
    {
        return getCurrentUser().mailBox;
    }

    public void goBack()
    {
        viewChanger.personalArea();
        viewChanger.setupView(database);
    }

    @Override
    public void setDatabase(Database database) {
        super.setDatabase(database);
        ((MailBoxView)view).displayTable();
    }

}
