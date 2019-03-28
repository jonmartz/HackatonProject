package Controller;

import View.PublishVacationView;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;

/**
 * Controller for the vacation publish view
 */
public class PublishController extends AbstractController {

    /**
     * Publish vacation
     */
    public void Publish() {
        PublishVacationView view = (PublishVacationView) this.view;
        // Get ticket count
        database.addLearn(database.getCurrentUser().username,view.courseTextBox.getValue()+"",view.semesterTextField.getText(),"T",view.yearTextField.getText());
        view.ShowPopUp("Course published successfully!");
    }

    /**
     * Is called when the toDate is picked. If dates are illegal clears the toDate datePicker.
//     */
//    public void ToDatePicked() {
//        DatePicked(false);
//    }
//
//    /**
//     * Is called when the fromDate is picked. If dates are illegal clears the fromDate datePicker.
//     */
//    public void FromDatePicked() { DatePicked(true); }

    /**
//     * If dates are illegal clears the [dateToClear] date and displays an error message.
//     * @param clearFromDate true to clear fromDate, false to clear toDate
     */
//    private void DatePicked(Boolean clearFromDate) {
//        PublishVacationView view = (PublishVacationView) this.view;
//        LocalDate fromDate = view.fromDateDatePicker.getValue();
//        LocalDate toDate = view.toDateDatePicker.getValue();
//
//        // Check that dates are after today
//        if (clearFromDate && fromDate != null && fromDate.isBefore(LocalDate.now())){
//            view.setComments("\"from\" date can't be before today!");
//            view.ClearFromDatePicker();
//            return;
//        }
//        if (!clearFromDate && toDate != null && toDate.isBefore(LocalDate.now())){
//            view.setComments("\"to\" date can't be before today!");
//            view.ClearToDatePicker();
//            return;
//        }
//        // If both dates are selected
//        if (fromDate != null && toDate != null) {
//            if (!fromDate.isAfter(toDate)) {
//                view.CheckEnablePublishButton();
//            }
//            else {
//                // but "from" is after "to"
//                view.setComments("\"from\" can't be after \"to\" date!");
//                if (clearFromDate) view.ClearFromDatePicker();
//                else view.ClearToDatePicker();
//            }
//        }
//    }

    @Override
    protected void FillAllData() {

    }

    /**
     * This function is called after clicking on the profile picture field, to add a profile picture.
     */
//    public void AddPicture() {
//        try {
//            PublishVacationView view = (PublishVacationView) this.view;
//            String ticketPicturePath = view.getFilePath("Choose profile picture");
//            if (ticketPicturePath == null) return;
//            view.ticketPicturePath = ticketPicturePath;
//            FileInputStream inputstream = new FileInputStream(view.ticketPicturePath);
//            Image image = new Image(inputstream);
//            view.ticketsImageView.setImage(image);
//            view.CheckEnablePublishButton();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
}
