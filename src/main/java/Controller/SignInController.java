package Controller;

import View.AbstractView;
import View.SignInView;
import Model.User;

public class SignInController extends AbstractController {


    /**
     * The constructor
     */
    public SignInController()
    {

    }

    /**
     * This function will set the right view for this class
     */
    public void setView(AbstractView abstractView) {
        if (abstractView instanceof SignInView)
            super.setView(abstractView);
        else {
            super.setView(null);
        }
    }


    /**
     * Transitions to the sign up screen
     */
    public void signUp() {
        viewChanger.signUp();
        viewChanger.setupView(database);
    }

    /**
     * Signs the user in, in case he exists and password is correct (right now it only opens user settings).
     */
    public void signIn() {

        //Get the user
        User user = database.getUser(((SignInView)this.view).getUsernameText());

        //If the user does exist
        if (user != null) {

            //If the password matches
            if (((SignInView)this.view).getPasswordText().equals(user.password)) {
                database.setCurrentUser(user);
                viewChanger.mainMenu();
                viewChanger.setupView(database);
                viewChanger.fillNameOfUserInMainMenu();

                }
            else {
                ((SignInView)this.view).setComments("Password is incorrect!");

            }
        }
        //If the user does not exist
        else {
            ((SignInView)this.view).setComments("Username does not exist!");

        }
    }
}


