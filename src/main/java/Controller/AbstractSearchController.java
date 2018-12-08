package Controller;


import Model.User;
import View.AbstractView;
import View.UserSearchView;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class AbstractSearchController extends AbstractController {
    private Stage stage;
    private FXMLLoader fxmlLoader;

    /**
     * This function will set the right view for this class
     */
    public void setView(AbstractView abstractView) {
        if (abstractView instanceof UserSearchView)
            super.setView(abstractView);
        else {
            super.setView(null);
        }
    }

    /**
     * This function will activate when the button is pressed
     */
    public void searchForUser()
    {
        //get the username to look for
        String username = ((UserSearchView)view).getEnterUsernameTextField();
        if(username.equals(""))
        {
            ((UserSearchView)view).displayError("Must enter username");
            return;
        }
        User selectedUser=this.database.getUser(username);
        if(selectedUser==null)
        {
            ((UserSearchView)view).displayError("User does not exist");
            return;
        }
        if(selectedUser.username.equals(database.getCurrentUser().username))
        {
            ((UserSearchView)view).displayError("You are the user!!");
            return;
        }
        //If the username is valid display it's data
        ((UserSearchView)view).fillFieldsWithUserDetails(selectedUser);

    }


    /**
     * This function will transit to the setting's window
     */
    public void goBack()
    {
        viewChanger.mainMenu();
        viewChanger.setupView(database);
        viewChanger.fillNameOfUserInMainMenu();
    }
}