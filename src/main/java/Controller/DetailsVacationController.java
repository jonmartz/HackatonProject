package Controller;

import Model.User;
import Model.Vacation;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import View.DetailsVacationView;
import javax.swing.text.View;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DetailsVacationController extends AbstractController {
    private Stage stage;
    private FXMLLoader fxmlLoader;

    public void fillFieldsWithVacationDetails() {
        //Get the current user
        Vacation currentVacation= database.getCurrentVacation();

        //Fill the fields with the vacation's data
        DetailsVacationView detailsVacationView = (DetailsVacationView) view;
        detailsVacationView.setDestinationCountryTXT(currentVacation.destinationCountryTXT);
        detailsVacationView.setFromDateTXT(currentVacation.fromDateTXT);
        detailsVacationView.setToDateTXT(currentVacation.toDateTXT);
        detailsVacationView.setNumOfTickets(currentVacation.NumOfTickets);
        detailsVacationView.setKindOfTicketTXT(currentVacation.kindOfTicketTXT);
        detailsVacationView.setKindOfVacationTXT(currentVacation.kindOfVacationTXT);
        detailsVacationView.setFlightCompanyTXT(currentVacation.flightCompanyTXT);
        detailsVacationView.setBaggageTXT(currentVacation.baggageTXT);
        detailsVacationView.setKindOfSleepingPlaceTXT(currentVacation.kindOfSleepingPlaceTXT);
        detailsVacationView.setTheRateOfTheSleepingPlaceTXT(currentVacation.theRateOfTheSleepingPlaceTXT);
        detailsVacationView.setIsThereReturnFlightTXT(currentVacation.isThereReturnFlightTXT);
        detailsVacationView.setPrice(currentVacation.price);
        detailsVacationView.setIsTheSleepingCostsIncludesTXT(currentVacation.isTheSleepingCostsIncludesTXT);
    }

    @Override
    protected void FillAllData() { fillFieldsWithVacationDetails();}
}
