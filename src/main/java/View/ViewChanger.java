package View;

import Controller.AbstractController;
import Controller.PersonalAreaController;
import Model.Database;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Manages the transitions between the different views associated with user account management.
 */
public class ViewChanger {
    private Stage stage;
    private FXMLLoader fxmlLoader;
    private String lastView = "";
    /**
     * Constructor
     * @param stage of view
     */
    public ViewChanger(Stage stage) {
        this.stage = stage;
    }

    /**
     * Gets the controller that was initialized after the fxml was loaded, and sets it's model and view pointers.
     */
    public void setupView(Database database) {
        AbstractView abstractView = fxmlLoader.getController();
        AbstractController controller = abstractView.getController();
        controller.setDatabase(database);
        controller.setViewChanger(this);
    }

    /**
     * Transitions to the search user screen.
     */
    public void searchUser() {
        try {
            fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("userSearch.fxml").openStream());
            stage.setTitle("Search User");
            stage.setScene(new Scene(root, 790 , 450));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Transitions to the sign up screen.
     */
    public void signUp() {
        try {
            fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("signUp.fxml").openStream());
            stage.setTitle("Sign up");
            stage.setScene(new Scene(root, 790 , 450));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void signIn() {
        try {
            fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("signIn.fxml").openStream());
            stage.setTitle("Sign In");
            stage.setScene(new Scene(root, 790 , 450));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Transitions to the user personalArea.fxml screen.
     */
    public void personalArea() {
        try {
            fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("personalArea.fxml").openStream());
            stage.setTitle("Personal Area");
            stage.setScene(new Scene(root, 790 , 450));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchVacation() {
        try {
            fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("vacationSearch.fxml").openStream());
            stage.setTitle("Search Vacation");
            stage.setScene(new Scene(root, 790 , 450));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void publishVacation() {
        try {
            fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("publishVacation.fxml").openStream());
            stage.setTitle("Publish Vacation");
            stage.setScene(new Scene(root, 790 , 450));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lastView() {
        if (lastView.equals("publishVacation")) publishVacation();
        else if (lastView.equals("searchVacation")) searchVacation();
//        else if (lastView.equals("buyVacation")) buyVacation(); // todo: uncomment
        else searchVacation(); // default window is vacation search
    }

    public void setLastView(String lastView) {
        this.lastView = lastView;
    }
}
