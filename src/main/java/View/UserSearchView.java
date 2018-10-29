package View;

import Controller.UserController;
import Controller.UserSearchController;
import Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class UserSearchView extends AbstractView{
    @FXML
    public Text enter_username;
    public Text username;
    public Text first_name;
    public Text last_name;
    public Text birthday;
    public Text city;
    public Text username_display;
    public Text first_name_display;
    public Text last_name_display;
    public Text birthday_display;
    public Text city_display;
    public Text error_message;

    public TextField enter_username_textfield;
    public Button search_user_button;


    public void setController(UserController controller) {
        if (controller instanceof UserSearchController)
            super.setController(controller);
        else {
            super.setController(null);
        }
    }



    /**
     * Fills the user details in the user settings screen.
     */
    public void fillFieldsWithUserDetails(User user) {
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

        username_display.setText(user.username);
        first_name_display.setText(user.firstName);
        last_name_display.setText(user.lastName);
        birthday_display.setText(user.birthdate);
        city_display.setText(user.city);
        error_message.setVisible(false);
        enter_username_textfield.setText("");
    }
    public void displayError(String error){
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
        error_message.setVisible(true);

        error_message.setText(error);

    }
    public TextField getEnterUsernameTextField(){
        return enter_username_textfield;
    }
    public void initialize(URL location, ResourceBundle resources) {
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

    public void goBack(){
        ((UserSearchController)this.getController()).goBack();
    }
    public void searchForUser()
    {
        ((UserSearchController)this.getController()).searchForUser();
    }
}
