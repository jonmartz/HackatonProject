package Controller;


import Model.User;
import View.AbstractView;
import View.UserSearchView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Controller.UserController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserSearchController extends UserController {
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
        User selectedUser=this.userDatabase.getUser(username);
        if(selectedUser==null)
        {
            ((UserSearchView)view).displayError("User does not exist");
            return;
        }
        if(selectedUser.username.equals(userDatabase.getCurrentUser().username))
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
        userView.settings();
        userView.setupView(userDatabase);
        userView.fillFieldsWithUserDetails();
    }
}