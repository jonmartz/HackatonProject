package Controller;

import View.AbstractView;
import View.SettingsView;
import Model.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Controller for the user settings screen, where the user can update account details or delete the account.
 */
public class SettingsController extends AbstractController {

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
        User currentUser= database.getCurrentUser();

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
        User user = database.getCurrentUser();
        SettingsView settingsView = (SettingsView) view;

        //Check if we change the current user and not another one
        if (user.username.equals(settingsView.getUsernameText())
                || database.getUser(settingsView.getUsernameText()) == null) {
            //For each field, update if necessary
            if (!user.password.equals(settingsView.getPasswordText()))
                database.updateUser(user.username,"password",settingsView.getPasswordText());
            if (!user.birthdate.equals(settingsView.getBirthdayString()))
                database.updateUser(user.username,"birthdate",settingsView.getBirthdayString());
            if (!user.firstName.equals(settingsView.getFirstNameText()))
                database.updateUser(user.username,"firstName",settingsView.getFirstNameText());
            if (!user.lastName.equals(settingsView.getLastNameText()))
                database.updateUser(user.username,"lastName",settingsView.getLastNameText());
            if (!user.city.equals(settingsView.getCityText()))
                database.updateUser(user.username,"city",settingsView.getCityText());
            if (!user.username.equals(settingsView.getUsernameText()))
                database.updateUser(user.username,"username",settingsView.getUsernameText());
            // update the user pointer in the model to match the saved changes
            database.setCurrentUser(database.getUser(settingsView.getUsernameText()));
        }
        else {
            settingsView.setComments("Username already exists!");
        }
    }

    /**
     * Deletes the user.
     */
    public void deleteUser() {
        database.deleteUser(database.getCurrentUser().username);
        mainMenu();
    }

    /**
     * Transitions to the main menu.
     */
    public void mainMenu() {
        database.setCurrentUser(null);
        viewChanger.mainMenu();
        viewChanger.setupView(database);
    }




}
