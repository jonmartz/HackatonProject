package Controller;

import View.AbstractView;
import View.MainMenuView;
import Model.User;

/**
 * Controller for the main menu, where a new user can sign up or an existing user sign in.
 */
public class MainMenuController extends AbstractController {


    /**
     * The constructor
     */
    public MainMenuController() {

    }

    public void fillNameOfUserInMainMenu() {
        User currentUser= database.getCurrentUser();
        MainMenuView mainMenuView = (MainMenuView) view;
        mainMenuView.showUsernameCommend(currentUser.username);
    }

    /**
     * Transitions to the sign up screen
     */
    public void signUp() {
        viewChanger.signUp();
        viewChanger.setupView(database);
    }

    /**
     * Transitions to the vacation search screen
     */
    public void vacationSearch() {
        viewChanger.searchVacation();
        viewChanger.setupView(database);
    }

    /**
     * Transitions to the user search window
     */
    public void searchUser()
    {
        viewChanger.searchUser();
        viewChanger.setupView(database);
    }

    public void signIn() {
        viewChanger.signIn();
        viewChanger.setupView(database);
    }

    /**
     * This function will set the right view for this class
     */
    @Override
    public void setView(AbstractView abstractView) {
        if (abstractView instanceof MainMenuView)
            super.setView(abstractView);
        else {
            super.setView(null);
        }
    }
}