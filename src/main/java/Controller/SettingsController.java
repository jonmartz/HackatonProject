package Controller;

import View.AbstractView;
import View.SettingsView;
import View.UserSearchView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import Model.User;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * Controller for the user settings screen, where the user can update account details or delete the account.
 */
public class SettingsController extends UserController{

    @Override
    /**
     * This function will set the right view for this class
     */
    public void setView(AbstractView abstractView) {
        if (abstractView instanceof SettingsView)
            super.setView(abstractView);
        else {
            super.setView(null);
        }
    }

    /**
     * Fills all fields with user details for the user to update his details more easily.
     */
    public void fillFieldsWithUserDetails() {
        //Get the current user
        User currentUser= userDatabase.getCurrentUser();

        //Fill the fields with the user's data
        SettingsView settingsView = (SettingsView) view;
        settingsView.setUsernameText(currentUser.username);
        settingsView.setPasswordText(currentUser.password);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(currentUser.birthdate, formatter);
        settingsView.setBirthdayValue(localDate);

        settingsView.setFirstNameText(currentUser.firstName);
        settingsView.setLastNameText(currentUser.lastName);
        settingsView.setCityText(currentUser.city);

        settingsView.KeyReleased();
    }



    /**
     * Updates the birthday string, after a date in the date picker has been picked.
     */
    public void birthdatePicked() {
        SettingsView settingsView = (SettingsView) view;
        LocalDate birthdayDate = settingsView.getBirthdayValue();
        if (isAgeLegal(birthdayDate)) {
            settingsView.setBirthdayString(birthdayDate.toString());
            settingsView.KeyReleased();
        } else {
            settingsView.setComments("Age must be over 18 years");
            settingsView.clearBirthdayPicker();
        }
    }

    /**
     * Saves user's details after he has updated them in the respective text fields.
     * Username is updated only if the new username is available.
     */
    public void saveChanges() {
        //Get the current user
        User user = userDatabase.getCurrentUser();
        SettingsView settingsView = (SettingsView) view;

        //Check if we change the current user and not another one
        if (user.username.equals(settingsView.getUsernameText())
                || userDatabase.getUser(settingsView.getUsernameText()) == null) {
            //For each field, update if necessary
            if (!user.password.equals(settingsView.getPasswordText()))
                userDatabase.updateUser(user.username,"password",settingsView.getPasswordText());
            if (!user.birthdate.equals(settingsView.getBirthdayString()))
                userDatabase.updateUser(user.username,"birthdate",settingsView.getBirthdayString());
            if (!user.firstName.equals(settingsView.getFirstNameText()))
                userDatabase.updateUser(user.username,"firstName",settingsView.getFirstNameText());
            if (!user.lastName.equals(settingsView.getLastNameText()))
                userDatabase.updateUser(user.username,"lastName",settingsView.getLastNameText());
            if (!user.city.equals(settingsView.getCityText()))
                userDatabase.updateUser(user.username,"city",settingsView.getCityText());
            if (!user.username.equals(settingsView.getUsernameText()))
                userDatabase.updateUser(user.username,"username",settingsView.getUsernameText());
            // update the user pointer in the model to match the saved changes
            userDatabase.setCurrentUser(userDatabase.getUser(settingsView.getUsernameText()));
        }
        else {
            settingsView.setComments("Username already exists!");
        }
    }

    /**
     * Deletes the user.
     */
    public void deleteUser() {
        userDatabase.deleteUser(userDatabase.getCurrentUser().username);
        mainMenu();
    }

    /**
     * Transitions to the main menu.
     */
    public void mainMenu() {
        userDatabase.setCurrentUser(null);
        userView.mainMenu();
        userView.setupView(userDatabase);
    }

    /**
     * Transitions to the user search window
     */
    public void searchUser()
    {
        userView.searchUser();
        userView.setupView(userDatabase);
    }


}
