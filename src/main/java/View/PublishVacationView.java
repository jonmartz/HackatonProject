package View;

import Controller.PublishController;
import Controller.AbstractController;
import Model.Database;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.DatePicker;
import java.time.LocalDate;
import javafx.scene.control.ComboBox;

public class PublishVacationView extends AbstractView{

    @FXML
    private int vacationID;
    public Button pubishVacation;
    public TextField destinetionContryTXT;
    public TextField NumOfTicketsTXT;
    public TextField flightCompanyTXT;
    public TextField baggageTXT;
    public TextField kindOfVacationTXT;
    public TextField kindOfSleepingPlaceTXT;
    public TextField theRateOfTheSleepingPlaceTXT;
    public DatePicker toDateTXT;
    public DatePicker fromDateTXT;
    public ComboBox kindOfTicketTXT;
    public ComboBox isTheSleepingCostsIncludesTXT;
    public ComboBox isThereReturnFlightTXT;
    public Text comments; // Problems in user input are shown here
    public String todate;
    public String fromdate;
    public String KindOfTicketSTR;
    public String isTheSleepingCostsIncludesSTR;
    public String isThereReturnFlightSTR;

    /**
     * This function will initialize an instance of this class
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PublishController publishController = new PublishController();
        this.setController(publishController);
        publishController.setView(this);
        kindOfTicketTXT.getItems().removeAll(kindOfTicketTXT.getItems());
        kindOfTicketTXT.getItems().addAll("adult", "child", "baby");
        isTheSleepingCostsIncludesTXT.getItems().removeAll(isTheSleepingCostsIncludesTXT.getItems());
        isTheSleepingCostsIncludesTXT.getItems().addAll("yes,no");
        isThereReturnFlightTXT.getItems().removeAll(isThereReturnFlightTXT.getItems());
        isThereReturnFlightTXT.getItems().addAll("yes,no");
    }

    /**
     * Activates after user types in a text field, in order to enable/disable the publish button
     * and write in the comments field.
     */
    public void KeyReleased() {
        try {
            if (destinetionContryTXT.getText().isEmpty() || NumOfTicketsTXT.getText().isEmpty() || flightCompanyTXT.getText().isEmpty()
                    || baggageTXT.getText().isEmpty() || kindOfVacationTXT.getText().isEmpty() || kindOfSleepingPlaceTXT.getText().isEmpty()
                    || theRateOfTheSleepingPlaceTXT.getText().isEmpty() || todate.isEmpty() || fromdate.isEmpty()||KindOfTicketSTR.isEmpty()
                    ||isThereReturnFlightSTR.isEmpty()||isTheSleepingCostsIncludesSTR.isEmpty()) {
                pubishVacation.setDisable(true);
                comments.setText("Please fill all fields");
            } else {
                pubishVacation.setDisable(false);
                comments.setText("");
            }
        } catch (Exception e) {
            pubishVacation.setDisable(true);
            comments.setText("Please fill all fields");
        }
    }

    public void setController(AbstractController controller) {
        if (controller instanceof PublishController)
            super.setController(controller);
        else {
            super.setController(null);
        }
    }
    /**
     * This function will return the text in the vacationID field
     * @return - The text in the vacationID field
     */
    public String getvacationIDText() {
       String toReturn= ""+vacationID;
       return toReturn;
    }

    /**
     * This function will clear the text in the datePicker field
     */
    public void cleartoDateTXT() {
        this.toDateTXT.getEditor().clear();
    }

    public void clearfromDateTXT() {
        this.fromDateTXT.getEditor().clear();
    }

    public String getDestinetionContryTXT()
    {
      return destinetionContryTXT.getText();
    }

    public String getNumOfTicketsTXT()
    {
        return NumOfTicketsTXT.getText();
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
        return kindOfVacationTXT.getText();
    }

    public String getKindOfSleepingPlaceTXT()
    {
        return kindOfSleepingPlaceTXT.getText();
    }

    public String getTheRateOfTheSleepingPlaceTXT()
    {
        return theRateOfTheSleepingPlaceTXT.getText();
    }

    public LocalDate getToDateTXT()
    {
        return toDateTXT.getValue();
    }

    public String getTodateStr(){return this.todate;}

    public String getFromdateStr(){return this.fromdate;}

    public LocalDate getFromDateTXT()
    {
        return fromDateTXT.getValue();
    }

    public String getKindOfTicketTXT()
    {
        return (String) kindOfTicketTXT.getValue();
    }

    public String getIsTheSleepingCostsIncludesTXT()
    {
        return (String) isTheSleepingCostsIncludesTXT.getValue();
    }

    public String getIsThereReturnFlightTXT()
    {
        return (String) isThereReturnFlightTXT.getValue();
    }

    public void setComments(String comment)
    {
        this.comments.setText(comment);
    }

    public void setToDate(String val){
        todate=val;
    }

    public void setfromDate(String val){
        fromdate=val;
    }

    public void activeToDate(){
        ((PublishController)this.getController()).toDatePicked();;
    }

    public void activeFromDate(){
        ((PublishController)this.getController()).fromDatePicked();
    }

    public void activeKindOfTicket()
    {
        ((PublishController)this.getController()).KindOfTicketPicked();
    }

    public void activeIsTheSleepingCostsIncludes()
    {
        ((PublishController)this.getController()).IsTheSleepingCostsIncludesOfTicketPicked();
    }

    public void activeIsThereReturnFlight()
    {
        ((PublishController)this.getController()).IsThereReturnFlightPicked();
    }









}
