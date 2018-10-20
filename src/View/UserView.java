package View;

import Controller.UserController;
import Model.IModel;
import Model.UserDatabase;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UserView {
    private Stage stage;
    private FXMLLoader fxmlLoader;

    public UserView(Stage stage) {
        this.stage = stage;
    }

    public void mainMenu() {
        try {
            fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("mainMenu.fxml").openStream());
            stage.setTitle("Welcome");
            stage.setScene(new Scene(root, 300, 275));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void signUp() {
        try {
            fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("signUp.fxml").openStream());
            stage.setTitle("Sign up");
            stage.setScene(new Scene(root, 300, 275));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setupController(UserDatabase userDatabase) {
        UserController controller = fxmlLoader.getController();
        controller.setUserDatabase(userDatabase);
        controller.setUserView(this);
    }
}
