package View;

import Controller.PublishController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ComboBox;

public class PublishVacationView extends AbstractView{

    @FXML
    public Button pubishVacation;
    public TextField destinetionContryTXT;
    public TextField flightCompanyTXT;
    public TextField baggageTXT;
    public TextField adultCountTextBox;
    public TextField kidCountTextBox;
    public TextField babyCountTextBox;
    public TextField priceTextField;
    public ComboBox kindOfVacationTXT;
    public ComboBox kindOfSleepingPlaceTXT;
    public ComboBox theRateOfTheSleepingPlaceTXT;
    public ComboBox isTheSleepingCostsIncludesTXT;
    public ComboBox isThereReturnFlightTXT;
    public DatePicker toDateDatePicker;
    public DatePicker fromDateDatePicker;
    public Text commentsText; // Problems in user input are shown here
    public String ticketPicturePath;
    public ImageView ticketsImageView;

    private int ticketCount;

    /**
     * This function will initialize an instance of this class
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PublishController publishController = new PublishController();
        this.setController(publishController);
        publishController.setView(this);
        isTheSleepingCostsIncludesTXT.getItems().removeAll(isTheSleepingCostsIncludesTXT.getItems());
        isTheSleepingCostsIncludesTXT.getItems().addAll("yes","no");
        isThereReturnFlightTXT.getItems().removeAll(isThereReturnFlightTXT.getItems());
        isThereReturnFlightTXT.getItems().addAll("yes","no");
        kindOfVacationTXT.getItems().addAll("Urban", "Exotic", "Nature");
        kindOfSleepingPlaceTXT.getItems().addAll("Hotel", "Cabin", "Apartment");
        theRateOfTheSleepingPlaceTXT.getItems().addAll("1","2","3","4","5");

        // Add observers to keep text legal
        addTextListener(destinetionContryTXT, "[A-Za-z]*", "[^A-Za-z]");
        addTextListener(flightCompanyTXT, "[A-Za-z]*", "[^A-Za-z]");
        addTextListener(baggageTXT, "\\d*", "[^\\d]");
        addTextListener(adultCountTextBox, "\\d*", "[^\\d]");
        addTextListener(kidCountTextBox, "\\d*", "[^\\d]");
        addTextListener(babyCountTextBox, "\\d*", "[^\\d]");
        addTextListener(priceTextField, "\\d*", "[^\\d]");
    }

    /**
     * Activates after user types in a text field, in order to enable/disable the publish button
     * and write in the commentsText field.
     */
    public void CheckEnablePublishButton() {
        try {
            if (destinetionContryTXT.getText().isEmpty()
                    || ticketCount == 0
                    || flightCompanyTXT.getText().isEmpty()
                    || baggageTXT.getText().isEmpty()
                    || kindOfVacationTXT.getValue().toString().isEmpty()
                    || kindOfSleepingPlaceTXT.getValue().toString().isEmpty()
                    || theRateOfTheSleepingPlaceTXT.getValue().toString().isEmpty()
                    || fromDateDatePicker.getValue().toString().isEmpty()
                    || toDateDatePicker.getValue().toString().isEmpty()
                    || isThereReturnFlightTXT.getValue().toString().isEmpty()
                    || isTheSleepingCostsIncludesTXT.getValue().toString().isEmpty()
                    || ticketPicturePath.isEmpty()) {
                pubishVacation.setDisable(true);
                commentsText.setText("Please fill all fields");
            } else {
                pubishVacation.setDisable(false);
                commentsText.setText("");
            }
        } catch (Exception e) {
            pubishVacation.setDisable(true);
            commentsText.setText("Please fill all fields");
        }
    }

    /**
     * Checks that only numbers are set in ticket number fields
     */
    public void CheckTicketCountText() {

        String adults = adultCountTextBox.getText();
        String kids = kidCountTextBox.getText();
        String babies = babyCountTextBox.getText();

        // Replace chars if theyre not numbers. do apart from listeners to avoid waiting
        if (!adults.matches("\\d*") || !kids.matches("\\d*") || !babies.matches("\\d*")) {
            adultCountTextBox.setText(adults.replaceAll("[^\\d]", ""));
            kidCountTextBox.setText(kids.replaceAll("[^\\d]", ""));
            babyCountTextBox.setText(babies.replaceAll("[^\\d]", ""));
//            setComments("only numbers allowed in \"country\"");
        }

        adults = adultCountTextBox.getText();
        kids = kidCountTextBox.getText();
        babies = babyCountTextBox.getText();

        ticketCount = 0;
        try {
            if (!adults.isEmpty()) ticketCount += Integer.parseInt(adultCountTextBox.getText());
            if (!kids.isEmpty()) ticketCount += Integer.parseInt(kidCountTextBox.getText());
            if (!babies.isEmpty()) ticketCount += Integer.parseInt(babyCountTextBox.getText());
            CheckEnablePublishButton();

        } catch (NumberFormatException e) {
            setComments("Woah, too many tickets!!!");
        }
    }

    public void setComments(String comment)
    {
        this.commentsText.setText(comment);
    }

    public String getDestinetionContryTXT()
    {
      return destinetionContryTXT.getText();
    }

    public String getFlightCompanyTXT()
    {
        return flightCompanyTXT.getText();
    }

    public String getBaggageTXT()
    {
        return baggageTXT.getText();
    }

    public String getKindOfVacationTXT()
    {
        return kindOfVacationTXT.getValue().toString();
    }

    public String getKindOfSleepingPlaceTXT()
    {
        return kindOfSleepingPlaceTXT.getValue().toString();
    }

    public String getTheRateOfTheSleepingPlaceTXT()
    {
        return theRateOfTheSleepingPlaceTXT.getValue().toString();
    }

    public String getTodateStr(){return this.toDateDatePicker.getValue().toString();}

    public String getFromdateStr(){return this.fromDateDatePicker.getValue().toString();}


    public void activeToDate(){
        ((PublishController)this.getController()).ToDatePicked();
    }

    public void activeFromDate(){
        ((PublishController)this.getController()).FromDatePicked();
    }

    public void publish() { ((PublishController)this.getController()).Publish(); }

    /**
     * Clear the to date picker text
     */
    public void ClearToDatePicker() {
        this.toDateDatePicker.getEditor().clear();
    }
    /**
     * This function will clear the text in the datePicker field
     */
    public void ClearFromDatePicker() {
        this.fromDateDatePicker.getEditor().clear();
    }


    /**
     * This function is called after clicking on the ticket picture field.
     */
    public void AddPicture() { ((PublishController)this.getController()).AddPicture(); }

    /**
     * Getter
     * @return yes or no
     */
    public String getSleepingCostIncluded() {
        return isTheSleepingCostsIncludesTXT.getValue().toString();
    }

    /**
     * Getter
     * @return yes or no
     */
    public String getReturnFlight() {
        return isThereReturnFlightTXT.getValue().toString();
    }
}
