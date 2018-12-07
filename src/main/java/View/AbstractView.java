package View;

import Controller.AbstractController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class represents an abstract view. Holds the main menu functions.
 */
public abstract class AbstractView implements Initializable {

    @FXML
    public Button signIn;
    public Button vacationSearch;
    public Button vacationAdvertising;
    public Button personalArea;
    public Button searchUser;

    private AbstractController controller; //The controller assigned to the view

    /**
     * This function will set the controller of the view
     * @param controller - The given controller
     */
    public void setController(AbstractController controller){
        this.controller = controller;
    }

    /**
     * If user is signed in, set main menu accordingly.
     */
    public void updateMainMenuButtons(){
        if (controller.getCurrentUser() != null){
            // User is signed in
            signIn.setText("Sign out");
            personalArea.setDisable(false);
            searchUser.setDisable(false);
        }
    }

    /**
     * This function will return the controller
     * @return - The instance of the controller of this view
     */
    public AbstractController getController(){return this.controller;}

    /**
     * Transitions to the user sign in window
     */
    public void signIn() {
        if (controller.getCurrentUser() == null) controller.signIn();
        else controller.signOut();
    }

    /**
     * Transitions to the user search window
     */
    public void searchUser() { controller.searchUser(); }

    /**
     * Transitions to the vacation search window
     */
    public void vacationSearch() { controller.vacationSearch(); }

    /**
     * Transitions to the vacation publish window
     */
    public void vacationPublish() { controller.vacationPublish(); }

    /**
     * Transitions to the personal area window
     */
    public void personalArea() { controller.personalArea(); }

    /**
     * Show warning and ask for user's confirmation
     * @param text of warning
     * @return user's answer
     */
    public ButtonType getResultFromWarning(String text){
        Alert alert = new Alert(Alert.AlertType.WARNING, text, ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        return alert.getResult();
    }
}
