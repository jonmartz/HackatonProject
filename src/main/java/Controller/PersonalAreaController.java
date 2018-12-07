package Controller;

import View.PersonalAreaView;
import Model.User;
import javafx.scene.control.ButtonType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Controller for the user personalArea.fxml screen, where the user can update account details or delete the account.
 */
public class PersonalAreaController extends AbstractController {

    /**
     * Fills all fields with user details for the user to update his details more easily.
     */
    public void fillFieldsWithUserDetails() {
        //Get the current user
        User currentUser= database.getCurrentUser();

        //Fill the fields with the user's data
        PersonalAreaView personalAreaView = (PersonalAreaView) view;
        personalAreaView.setUsernameText(currentUser.username);
        personalAreaView.setPasswordText(currentUser.password);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(currentUser.birthdate, formatter);
        personalAreaView.setBirthdayValue(localDate);

        personalAreaView.setFirstNameText(currentUser.firstName);
        personalAreaView.setLastNameText(currentUser.lastName);
        personalAreaView.setCityText(currentUser.city);

        personalAreaView.KeyReleased();
    }

    /**
     * Updates the birthday string, after a date in the date picker has been picked.
     */
    public void birthdatePicked() {
        PersonalAreaView personalAreaView = (PersonalAreaView) view;
        LocalDate birthdayDate = personalAreaView.getBirthdayValue();
        if (isAgeLegal(birthdayDate)) {
            personalAreaView.setBirthdayString(birthdayDate.toString());
            personalAreaView.KeyReleased();
        } else {
            personalAreaView.setComments("Age must be over 18 years");
            personalAreaView.clearBirthdayPicker();
        }
    }

    /**
     * Saves user's details after he has updated them in the respective text fields.
     * Username is updated only if the new username is available.
     */
    public void saveChanges() {
        //Get the current user
        User user = database.getCurrentUser();
        PersonalAreaView personalAreaView = (PersonalAreaView) view;

        //Check if we change the current user and not another one
        if (user.username.equals(personalAreaView.getUsernameText())
                || database.getUser(personalAreaView.getUsernameText()) == null) {
            //For each field, update if necessary
            if (!user.password.equals(personalAreaView.getPasswordText()))
                database.updateUser(user.username,"password", personalAreaView.getPasswordText());
            if (!user.birthdate.equals(personalAreaView.getBirthdayString()))
                database.updateUser(user.username,"birthdate", personalAreaView.getBirthdayString());
            if (!user.firstName.equals(personalAreaView.getFirstNameText()))
                database.updateUser(user.username,"firstName", personalAreaView.getFirstNameText());
            if (!user.lastName.equals(personalAreaView.getLastNameText()))
                database.updateUser(user.username,"lastName", personalAreaView.getLastNameText());
            if (!user.city.equals(personalAreaView.getCityText()))
                database.updateUser(user.username,"city", personalAreaView.getCityText());
            if (!user.username.equals(personalAreaView.getUsernameText()))
                database.updateUser(user.username,"username", personalAreaView.getUsernameText());
            // update the user pointer in the model to match the saved changes
            database.setCurrentUser(database.getUser(personalAreaView.getUsernameText()));
        }
        else {
            personalAreaView.setComments("Username already exists!");
        }
    }

    /**
     * Deletes the user, if he accepts in the dialogue.
     */
    public void deleteUser() {
        if (view.getResultFromWarning("Delete your profile?") == ButtonType.NO) return;
        database.deleteUser(database.getCurrentUser().username);
        signIn();
    }

    @Override
    protected void FillAllData() {
        fillFieldsWithUserDetails();
    }
}
