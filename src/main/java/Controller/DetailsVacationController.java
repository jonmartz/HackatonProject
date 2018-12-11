package Controller;

import Model.RequestMessage;
import Model.Vacation;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import View.DetailsVacationView;

public class DetailsVacationController extends AbstractController {
    private Stage stage;
    private FXMLLoader fxmlLoader;

    public void fillFieldsWithVacationDetails() {
        //Get the current user
        Vacation vacation= database.getCurrentVacation();

        //Fill the fields with the vacation's data
        DetailsVacationView view = (DetailsVacationView) this.view;
        view.setDestinationCountryTXT(vacation.destinationCountryTXT);
        view.setFromDateTXT(vacation.fromDateTXT);
        view.setToDateTXT(vacation.toDateTXT);
        view.setKindOfVacationTXT(vacation.kindOfVacationTXT);
        view.setFlightCompanyTXT(vacation.flightCompanyTXT);
        view.setBaggageTXT(vacation.baggageTXT);
        view.setKindOfSleepingPlaceTXT(vacation.kindOfSleepingPlaceTXT);
        view.setTheRateOfTheSleepingPlaceTXT(vacation.theRateOfTheSleepingPlaceTXT);
        view.setIsThereReturnFlightTXT(vacation.isThereReturnFlightTXT);
        view.setPrice(vacation.price);
        view.setIsTheSleepingCostsIncludesTXT(vacation.isTheSleepingCostsIncludesTXT);
        view.adultTicketsText.setText(vacation.AdultTickets);
        view.kidTicketsText.setText(vacation.KidTickets);
        view.babyTicketsText.setText(vacation.BabyTickets);
        view.ownerText.setText(vacation.ownerID);
        Image image = view.getImage(vacation.ticketPicturePath);
        if (image != null) view.ticketsImageView.setImage(image);
    }

    @Override
    protected void FillAllData() { fillFieldsWithVacationDetails();}

    public void BuyVacation() {
        Vacation vacation = database.getCurrentVacation();
        RequestMessage requestMessage =new RequestMessage(this.getCurrentUser().username,vacation.ownerID,vacation);
        this.database.addMessage(requestMessage.getSender(),requestMessage.getReceiver(),vacation.ID,false,requestMessage.getDate(),requestMessage.getTime(),requestMessage.getKind());
        viewChanger.closeSecondaryStage();
    }
}
