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
 * Manages the transitions between the different views.
 */
public class ViewChanger {
    private Stage stage;
    private Stage secondaryStage;
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

    /**
     * Transitions to the sign in screen.
     */
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

    /**
     * Transitions to the search vacation screen.
     */
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

    /**
     * Transitions to the publish vacation screen.
     */
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

    /**
     * Transitions to the MailBox screen.
     */
    public void mailBox() {
        try {
            fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("mailBox.fxml").openStream());
            stage.setTitle("Mail Box");
            stage.setScene(new Scene(root, 790 , 450));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Transitions to the view message screen.
     * @param fxml - The fxml that describes the message
     * @param kind - The kind of message
     */
    public void messageView(String fxml,String kind)
    {
        try {
            fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getClassLoader().getResource(fxml).openStream());
            String note = ""+kind.charAt(0);
            note = note.toUpperCase();
            kind = note+kind.substring(1);
            stage.setTitle(kind+" Message");
            stage.setScene(new Scene(root, 790 , 450));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Transitions to the vacation details screen.
     */
    public void detailsVacation() {
        try {
            fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("detailsVacation.fxml").openStream());
            // Create new stage
            Stage secondaryStage = new Stage();
            this.secondaryStage = secondaryStage;
            secondaryStage.setTitle("Vacation Details");
            secondaryStage.setScene(new Scene(root, 790 , 450));
            secondaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Transitions to the last view
     */
    public void lastView() {
        if (lastView.equals("publishVacation")) publishVacation();
        else if (lastView.equals("searchVacation")) searchVacation();
//        else if (lastView.equals("buyVacation")) buyVacation(); // todo: uncomment
        else searchVacation(); // default window is vacation search
    }

    /**
     * setter
     * @param lastView to set
     */
    public void setLastView(String lastView) {
        this.lastView = lastView;
    }

    public void closeSecondaryStage()
    {
        if(secondaryStage!=null)
        {
            secondaryStage.close();
            secondaryStage = null;
        }
    }

}
