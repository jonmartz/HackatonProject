package Controller;

import Model.Database;
import View.PersonalAreaView;
import Model.User;
import View.SignUpView;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Controller for the user personalArea.fxml screen, where the user can update account details or delete the account.
 */
public class PersonalAreaController extends AbstractController {

    @Override
    protected void FillAllData(){

        //Get the current user
        User currentUser= database.getCurrentUser();

        //Fill the fields with the user's data
        PersonalAreaView view = (PersonalAreaView) this.view;
        view.setUsernameText(currentUser.username);
        view.setPasswordText(currentUser.password);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(currentUser.birthdate, formatter);
        view.setBirthdayValue(localDate);
        view.setFirstNameText(currentUser.firstName);
        view.setLastNameText(currentUser.lastName);
        view.description.setText(currentUser.description);
        view.setCityText(currentUser.city);
        view.setPictureFilePath(currentUser.pictureFilePath);
        Image image = this.view.getImage(currentUser.pictureFilePath);
        if (image != null) view.pictureImageView.setImage(image);

        view.KeyReleased();
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
            if (!user.pictureFilePath.equals(personalAreaView.getPictureFilePath()))
                database.updateUser(user.username,"picture", personalAreaView.getPictureFilePath());
            if (!user.phoneNumber.equals(personalAreaView.phone.getText()))
                database.updateUser(user.username,"phoneNumber", personalAreaView.phone.getText());
            if (!user.description.equals(personalAreaView.description.getText()))
                database.updateUser(user.username,"description", personalAreaView.description.getText());



            // update the user pointer in the model to match the saved changes
            database.setCurrentUser(database.getUser(personalAreaView.getUsernameText()));

            personalAreaView.setComments("Changes saved successfully!");
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

    /**
     * This function is called after clicking on the profile picture field, to add a profile picture.
     */
    public void AddPicture() {
        PersonalAreaView view = (PersonalAreaView) this.view;
        String pictureFilePath = view.getFilePath("Choose profile picture");
        if (pictureFilePath == null) return;
        view.pictureFilePath = pictureFilePath;
        Image image = view.getImage(view.pictureFilePath);
        if (image != null) view.pictureImageView.setImage(image);
        view.KeyReleased();
    }

    /**
     * This function will return the number of unread messages
     * @return - The number of unread messages
     */
    public int getNumberOfUnreadMessages()
    {
        try
        {
            return getCurrentUser().mailBox.numOfUnreadMesages();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }

    }
    @Override
    public void setDatabase(Database database) {
        super.setDatabase(database);
        ((PersonalAreaView)view).setMailBoxText();
    }
}
