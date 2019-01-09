package Controller;


import Model.User;
import View.AbstractView;
import View.UserSearchView;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

/**
 * Controller for the user search view
 */
public class UserSearchController extends AbstractController {

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

    @Override
    protected void FillAllData() {

    }
}