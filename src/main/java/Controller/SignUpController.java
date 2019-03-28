package Controller;

import Model.User;
import View.AbstractView;
import View.SignUpView;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;

/**
 * Controller for the sign up screen, where a new user can create an account.
 */
public class SignUpController extends AbstractController {
    /**
     * Updates the birthday string, after a date in the date picker has been picked.
     */
    public void birthdatePicked() {
        SignUpView signUpView = (SignUpView) view;
        LocalDate birthdayDate = signUpView.getBirthdayValue();
        if (isAgeLegal(birthdayDate)) {
            signUpView.setBirthdayString(birthdayDate.toString());
            signUpView.KeyReleased();
        } else {
            signUpView.setComments("Age must be over 18 years");
            signUpView.clearBirthdayPicker();
        }
    }

    /**
     * Creates the new user's account, in case the username is available.
     */
    public void signUp() {
        SignUpView signUpView = (SignUpView) view;
        User user = database.getUser(signUpView.getUsernameText());
        if (user == null) {
            database.addUser(signUpView.getUsernameText(), signUpView.getPasswordText(),
                    signUpView.getBirthdayString(), signUpView.getFirstNameText(),
                    signUpView.getLastNameText(), signUpView.getCityText(),signUpView.phone.getText(),
                    signUpView.description.getText(),signUpView.pictureFilePath);
            database.setCurrentUser(database.getUser(signUpView.getUsernameText()));
            viewChanger.lastView();
            viewChanger.setupView(database);
        }
        else {
            signUpView.setComments("Username already exists!");
        }
    }

    @Override
    protected void FillAllData() {
        // Nothing to do here
    }

    /**
     * This function is called after clicking on the profile picture field, to add a profile picture.
     */
    public void AddPicture() {
        try {
            SignUpView signUpView = (SignUpView) this.view;
            String pictureFilePath = signUpView.getFilePath("Choose profile picture");
            if (pictureFilePath == null) return;
            signUpView.pictureFilePath = pictureFilePath;
            FileInputStream inputstream = new FileInputStream(signUpView.pictureFilePath);
            Image image = new Image(inputstream);
            signUpView.pictureImageView.setImage(image);
            signUpView.KeyReleased();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
