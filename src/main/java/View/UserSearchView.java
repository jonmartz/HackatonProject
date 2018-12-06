package View;

import Controller.AbstractController;
import Controller.AbstractSearchController;
import Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;
/**
 * This class is the view that is responsible for the Settings window
 */
public class UserSearchView extends AbstractView{
    @FXML
    public Text enter_username;//The "enter username" text
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
    public Text error_message; //The text that will display the error messages

    public TextField enter_username_textfield;//The textfiled that will contain the username of the user that we want to search
    public Button search_user_button;//The "Search" button

    /**
     * Ths functrion will assign the given controller to it self if it's the right one
     * @param controller - The given controller
     */
    public void setController(AbstractController controller) {
        if (controller instanceof AbstractSearchController)
            super.setController(controller);
        else {
            super.setController(null);
        }
    }



    /**
     * Fills the user details in the user settings screen.
     */
    public void fillFieldsWithUserDetails(User user) {
        //Set all fields to "Visible"
        username.setVisible(true);
        first_name.setVisible(true);
        last_name.setVisible(true);
        birthday.setVisible(true);
        city.setVisible(true);
        username_display.setVisible(true);
        first_name_display.setVisible(true);
        last_name_display.setVisible(true);
        birthday_display.setVisible(true);
        city_display.setVisible(true);

        //Set the textFields's text to be the user's data
        username_display.setText(user.username);
        first_name_display.setText(user.firstName);
        last_name_display.setText(user.lastName);
        birthday_display.setText(user.birthdate);
        city_display.setText(user.city);
        error_message.setVisible(false);
        enter_username_textfield.setText("");
    }

    /**
     * This function will display an error message
     * @param error - The error message
     */
    public void displayError(String error){
        //Set all fields to "Invisible"
        username.setVisible(false);
        first_name.setVisible(false);
        last_name.setVisible(false);
        birthday.setVisible(false);
        city.setVisible(false);
        username_display.setVisible(false);
        first_name_display.setVisible(false);
        last_name_display.setVisible(false);
        birthday_display.setVisible(false);
        city_display.setVisible(false);

        //Turn the text f the error message to visible
        error_message.setVisible(true);
        //Set the text of the Error's text
        error_message.setText(error);

    }

    /**
     *This function will return the text in the username textfield
     * @return - The text in the username textfield
     */
    public String getEnterUsernameTextField(){
        return enter_username_textfield.getText();
    }

    @Override
    /**
     * This function will initialize an instance of this class
     */
    public void initialize(URL location, ResourceBundle resources) {

        AbstractSearchController userSearchController = new AbstractSearchController();
        this.setController(userSearchController);
        userSearchController.setView(this);

        //Set all fields to "Invisible"
        username.setVisible(false);
        first_name.setVisible(false);
        last_name.setVisible(false);
        birthday.setVisible(false);
        city.setVisible(false);
        username_display.setVisible(false);
        first_name_display.setVisible(false);
        last_name_display.setVisible(false);
        birthday_display.setVisible(false);
        city_display.setVisible(false);
        error_message.setVisible(false);
    }

    /**
     * This function will occur when the "Go Back" button is pressed
     */
    public void goBack(){
        ((AbstractSearchController)this.getController()).goBack();
    }

    /**
     * This function will occur when the "Search" button is pressed
     */
    public void searchForUser()
    {
        ((AbstractSearchController)this.getController()).searchForUser();
    }
}
