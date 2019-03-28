package View;

import Controller.PublishController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.ComboBox;

/**
 * View for the vacation publish screen
 */
public class PublishVacationView extends AbstractView{

    @FXML
    public Button addCourseButton;
    public TextField semesterTextField;
    public TextField yearTextField;

    public ComboBox courseTextBox;

    /**
     * This function will initialize an instance of this class
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PublishController publishController = new PublishController();
        this.setController(publishController);
        publishController.setView(this);

        // Add observers to keep text legal
        addTextListener(semesterTextField, "[A-Za-z]*", "[^A-Za-z]");
        addTextListener(yearTextField, "[A-Za-z]*", "[^A-Za-z]");

    }

    /**
     * Activates after user types in a text field, in order to enable/disable the publish button
     * and write in the commentsText field.
     */
    public void CheckEnablePublishButton() {
        try {
            if (semesterTextField.getText().isEmpty()
                    || yearTextField.getText().isEmpty()
                    || courseTextBox.getValue().toString().isEmpty())
          {
                addCourseButton.setDisable(true);
            } else {
                addCourseButton.setDisable(false);
            }
        } catch (Exception e) {
            addCourseButton.setDisable(true);
        }
    }

    /**
     * getter
     */
    public String getSemesterTextField()
    {
      return semesterTextField.getText();
    }
    /**
     * getter
     */
    public String getYearTextField()
    {
        return yearTextField.getText();
    }

    /**
     * getter
     */
    public String getCourseTextBox()
    {
        return courseTextBox.getValue().toString();
    }

    /**
     * Publish vacation
     */
    public void publish() { ((PublishController)this.getController()).Publish(); }
}
