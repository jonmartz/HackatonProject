package Controller;

import View.AbstractView;
import View.SignInView;
import Model.User;

/**
 * Controller for the sign in view
 */
public class SignInController extends AbstractController {

    @Override
    protected void FillAllData() {
        // nothing to do here
    }

    /**
     * Transitions to the sign up screen
     */
    public void signUp() {
        viewChanger.signUp();
        viewChanger.setupView(database);
    }

    /**
     * Signs the user in, in case he exists and password is correct (right now it only opens user personalArea.fxml).
     */
    public void signInUser() {

        //Get the user
        User user = database.getUser(((SignInView)this.view).getUsernameText());

        //If the user does exist
        if (user != null) {

            //If the password matches
            if (((SignInView)this.view).getPasswordText().equals(user.password)) {
                database.setCurrentUser(user);
                viewChanger.lastView();
                viewChanger.setupView(database);
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


