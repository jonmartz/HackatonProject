package View;

import Controller.AbstractController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
        try {
            if (controller.getCurrentUser() != null) {
                // User is signed in
                signIn.setText("Sign out");
                personalArea.setDisable(false);
                searchUser.setDisable(false);
            } // some views doesnt have main menu, so ignore exceptions
        } catch (Exception ignored){}
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
     * Transitions to the personal MailBox window
     */
    public void mailBox() { controller.mailBox();}

    /**
     * Opens a window with the user's vacations
     */
    public void myVacations() {
        controller.myVacations();
    }

    /**
     * Transitions to the personal Message window
     */
    public void messageView(String fxml,String kind) { controller.message(fxml,kind);}
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

    /**
     * Show pop up to user
     * @param text to display in pop up
     */
    public void ShowPopUp(String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, text);
        alert.showAndWait();
    }

    /**
     * Opens a "browse" window for the user to choose a file.
     * @param title of browse window
     * @return path of file chosen
     */
    public String getFilePath(String title) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        File file = fileChooser.showOpenDialog(null);
        if (file != null) return file.getAbsolutePath();
        return null;
    }

    /**
     * Get an image from file
     * @param pictureFilePath of file
     * @return image object
     */
    public Image getImage(String pictureFilePath){
        Image image = null;
        try {
            FileInputStream inputstream = new FileInputStream(pictureFilePath);
            image = new Image(inputstream);
        } catch (FileNotFoundException ignored) { }
        return image;
    }


    /**
     * Add a listener to text field to keep text legal
     * @param textField to listen
     * @param toMatch format the text must have
     * @param toReplace chars to replace in case text is not in format
     */
    public void addTextListener(TextField textField, String toMatch, String toReplace) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(toMatch))
                textField.setText(newValue.replaceAll(toReplace, ""));
        });
    }
}
