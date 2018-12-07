package Controller;

import Model.Vacation;
import Model.Database;
import View.AbstractView;
import View.PublishVacationView;

import java.time.LocalDate;

public class PublishController extends AbstractController {

    public void Publish() {
        PublishVacationView view = (PublishVacationView) this.view;
        database.addVacation(view.getDestinetionContryTXT(), view.getNumOfTicketsTXT(),
                view.getFlightCompanyTXT(), view.getBaggageTXT(), view.getKindOfVacationTXT(), view.getKindOfSleepingPlaceTXT(),
                view.getTheRateOfTheSleepingPlaceTXT(), view.getTodateStr(), view.getFromdateStr(),
                view.KindOfTicketSTR, view.isTheSleepingCostsIncludesSTR, view.isThereReturnFlightSTR,"100");
        view.setComments("Vacation published successfully!");
    }

    public void toDatePicked() {
        PublishVacationView publishVacationView = (PublishVacationView) view;
        LocalDate dateOfVacation = publishVacationView.getToDateTXT();
        if (isLegalDateForVacation(dateOfVacation)) {
            publishVacationView.setToDate(dateOfVacation.toString());
            publishVacationView.KeyReleased();
        } else {
            publishVacationView.setComments("the date of the vacation already passed");
            publishVacationView.cleartoDateTXT();
        }
    }

    public void fromDatePicked() {
        PublishVacationView publishVacationView = (PublishVacationView) view;
        LocalDate dateOfVacation = publishVacationView.getFromDateTXT();
        if (isLegalDateForVacation(dateOfVacation)) {
            publishVacationView.setfromDate(dateOfVacation.toString());
            publishVacationView.KeyReleased();
        } else {
            publishVacationView.setComments("the date of the vacation already passed");
            publishVacationView.clearfromDateTXT();
        }
    }

    public boolean isLegalDateForVacation(LocalDate datePicked){
        if(datePicked.isEqual(LocalDate.now()))
            return true;
        if (datePicked != null)
            return datePicked.isAfter(LocalDate.now());
        return false;
    }

    public void KindOfTicketPicked() {
        PublishVacationView publishVacationView = (PublishVacationView) view;
        String dateOfVacation = publishVacationView.getKindOfTicketTXT();
        publishVacationView.KindOfTicketSTR=dateOfVacation;
        publishVacationView.KeyReleased();
    }

    public void IsTheSleepingCostsIncludesOfTicketPicked() {
        PublishVacationView publishVacationView = (PublishVacationView) view;
        String dateOfVacation = publishVacationView.getIsTheSleepingCostsIncludesTXT();
        publishVacationView.isTheSleepingCostsIncludesSTR=dateOfVacation;
        publishVacationView.KeyReleased();
    }

    public void IsThereReturnFlightPicked() {
        PublishVacationView publishVacationView = (PublishVacationView) view;
        String dateOfVacation = publishVacationView.getIsThereReturnFlightTXT();
        publishVacationView.isThereReturnFlightSTR=dateOfVacation;
        publishVacationView.KeyReleased();
    }

    @Override
    protected void FillAllData() {

    }
}
