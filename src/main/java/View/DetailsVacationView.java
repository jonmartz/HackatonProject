package View;

import Controller.AbstractController;
import Controller.DetailsVacationController;
import Controller.UserSearchController;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailsVacationView extends AbstractView{
    @FXML
    public Text destinationCountryTXT;
    public Text NumOfTickets;
    public Text flightCompanyTXT;
    public Text baggageTXT;
    public Text kindOfVacationTXT;
    public Text kindOfSleepingPlaceTXT;
    public Text theRateOfTheSleepingPlaceTXT;
    public Text toDateTXT;
    public Text fromDateTXT;
    public Text kindOfTicketTXT;
    public Text isTheSleepingCostsIncludesTXT;
    public Text isThereReturnFlightTXT;
    public Text price;



    /**
     * Ths functrion will assign the given controller to it self if it's the right one
     * @param controller - The given controller
     */
    public void setController(AbstractController controller) {
        if (controller instanceof DetailsVacationController)
            super.setController(controller);
        else {
            super.setController(null);
        }
    }

    public void setDestinationCountryTXT(String destinationCountryTXT) {this.destinationCountryTXT.setText(destinationCountryTXT);}

    public void setNumOfTickets(String numOfTickets) {NumOfTickets.setText(numOfTickets);}

    public void setFromDateTXT(String fromDateTXT) {this.fromDateTXT.setText(fromDateTXT);}

    public void setToDateTXT(String toDateTXT) {this.toDateTXT.setText(toDateTXT);}

    public void setKindOfVacationTXT(String kindOfVacationTXT) {this.kindOfVacationTXT.setText(kindOfVacationTXT);}

    public void setKindOfTicketTXT(String kindOfTicketTXT) {this.kindOfTicketTXT.setText(kindOfTicketTXT);}

    public void setKindOfSleepingPlaceTXT(String kindOfSleepingPlaceTXT) {this.kindOfSleepingPlaceTXT.setText(kindOfSleepingPlaceTXT);}

    public void setFlightCompanyTXT(String flightCompanyTXT) {this.flightCompanyTXT.setText(flightCompanyTXT);}

    public void setIsTheSleepingCostsIncludesTXT(String isTheSleepingCostsIncludesTXT) {this.isTheSleepingCostsIncludesTXT.setText(isTheSleepingCostsIncludesTXT);}

    public void setBaggageTXT(String baggageTXT) {this.baggageTXT.setText(baggageTXT);}

    public void setTheRateOfTheSleepingPlaceTXT(String theRateOfTheSleepingPlaceTXT) {this.theRateOfTheSleepingPlaceTXT.setText(theRateOfTheSleepingPlaceTXT);}

    public void setIsThereReturnFlightTXT(String isThereReturnFlightTXT) {this.isThereReturnFlightTXT.setText(isThereReturnFlightTXT);}

    public void setPrice(String price) {this.price.setText(price);}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DetailsVacationController detailsVacationController = new DetailsVacationController();
        this.setController(detailsVacationController);
        detailsVacationController.setView(this);

    }
}
