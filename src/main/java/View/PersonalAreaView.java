package View;

import Controller.*;
import Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * This class is the view that is responsible for the Settings window
 */

public class PersonalAreaView extends AbstractView {
    @FXML
    public Text username;// The "Username" text
    public Text first_name;// The "First Name" text
    public Text last_name;// The l"Last Name" text
    public Text birthday;// The "birthday text
    public Text city;// The "City" text
    public Text username_display;//The text that will display the searched user's username
    public Text first_name_display;//The text that will display the searched user's first name
    public Text last_name_display;//The text that will display the searched user's last name
    public Text birthday_display; //The text that will display the searched user's birthday
    public Text city_display; //The text that will display the searched user's city

    public Text comments;
    public Button settings;
    public Button textBox;
    public Button MainMenu;
    public Button deleteUser;

    public String userName;
    /**
     * Ths functrion will assign the given controller to it self if it's the right one
     * @param controller - The given controller
     */
    public void setController(UserController controller) {
        if (controller instanceof PersonalAreaController)
            super.setController(controller);
        else {
            super.setController(null);
        }
    }


    @Override
    /**
     * This function will initialize an instance of this class
     */
    public void initialize(URL location, ResourceBundle resources) {
        //Assigning the view to the controller and backwards
        PersonalAreaController personalAreaController= new PersonalAreaController();
        this.setController(personalAreaController);
        personalAreaController.setView(this);
    }


    /**
     * Activates after user types in a text field, in order to enable/disable the sign in button
     * and write in the comments field.
     */
    public void KeyReleased() {
        try{
        }
        catch (Exception e) {
        }
    }

    public void fillTextsWithUser(){((PersonalAreaController)this.getController()).fillText();}

    public void fillTextsWithUserDetails(User currentUser){
        comments.setText("Hellow " + currentUser.username + ", Welcome back!");
        username_display.setText(currentUser.username);
        first_name_display.setText(currentUser.firstName);
        last_name_display.setText(currentUser.lastName);
        birthday_display.setText(currentUser.birthdate);
        city_display.setText(currentUser.city);
        userName = currentUser.username;
    }

    /**
     * This function will occur when the "Go Back" button is pressed
     */
    public void goBack(){
        ((PersonalAreaController)this.getController()).goBack();
    }

    /**
     * This function will occur when the "Search" button is pressed
     */
    public void settings()
    {
        ((PersonalAreaController)this.getController()).setting();
    }

    /**
     * This function will occur when the "Search" button is pressed
     */
    public void deleteUser()
    {
        ((PersonalAreaController)this.getController()).deleteUser();
    }

    /**
     * This function will occur when the "Search" button is pressed
     */
    public void textBox()
    {
        ((PersonalAreaController)this.getController()).textBox();
    }

}
