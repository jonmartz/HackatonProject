package Controller;

import View.PublishVacationView;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;

public class PublishController extends AbstractController {



    public void Publish() {
        PublishVacationView view = (PublishVacationView) this.view;
        // Get ticket count
        String adults = view.adultCountTextBox.getText();
        String kids = view.kidCountTextBox.getText();
        String babies = view.babyCountTextBox.getText();
        if (adults.isEmpty()) adults = "0";
        if (kids.isEmpty()) kids = "0";
        if (babies.isEmpty()) babies = "0";

        database.addVacation(view.getDestinetionContryTXT(), adults, kids, babies,
                view.getFlightCompanyTXT(), view.getBaggageTXT(), view.getKindOfVacationTXT(),
                view.getKindOfSleepingPlaceTXT(), view.getTheRateOfTheSleepingPlaceTXT(),
                view.getTodateStr(), view.getFromdateStr(), view.getSleepingCostIncluded(),
                view.getReturnFlight(), view.priceTextField.getText(),
                database.getCurrentUser().username, view.ticketPicturePath);
        view.ShowPopUp("Vacation was published successfully!");
    }

    /**
     * Is called when the toDate is picked. If dates are illegal clears the toDate datePicker.
     */
    public void ToDatePicked() {
        DatePicked(false);
    }

    /**
     * Is called when the fromDate is picked. If dates are illegal clears the fromDate datePicker.
     */
    public void FromDatePicked() { DatePicked(true); }

    /**
     * If dates are illegal clears the [dateToClear] date and displays an error message.
     * @param clearFromDate true to clear fromDate, false to clear toDate
     */
    private void DatePicked(Boolean clearFromDate) {
        PublishVacationView view = (PublishVacationView) this.view;
        LocalDate fromDate = view.fromDateDatePicker.getValue();
        LocalDate toDate = view.toDateDatePicker.getValue();

        // Check that dates are after today
        if (clearFromDate && fromDate != null && fromDate.isBefore(LocalDate.now())){
            view.setComments("\"from\" date can't be before today!");
            view.ClearFromDatePicker();
            return;
        }
        if (!clearFromDate && toDate != null && toDate.isBefore(LocalDate.now())){
            view.setComments("\"to\" date can't be before today!");
            view.ClearToDatePicker();
            return;
        }
        // If both dates are selected
        if (fromDate != null && toDate != null) {
            if (!fromDate.isAfter(toDate)) {
                view.CheckEnablePublishButton();
            }
            else {
                // but "from" is after "to"
                view.setComments("\"from\" can't be after \"to\" date!");
                if (clearFromDate) view.ClearFromDatePicker();
                else view.ClearToDatePicker();
            }
        }
    }

    @Override
    protected void FillAllData() {

    }

    /**
     * This function is called after clicking on the profile picture field, to add a profile picture.
     */
    public void AddPicture() {
        try {
            PublishVacationView view = (PublishVacationView) this.view;
            view.ticketPicturePath = view.getFilePath("Choose profile picture");
            if (view.ticketPicturePath == null) return;
            FileInputStream inputstream = new FileInputStream(view.ticketPicturePath);
            Image image = new Image(inputstream);
            view.ticketsImageView.setImage(image);
            view.CheckEnablePublishButton();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
