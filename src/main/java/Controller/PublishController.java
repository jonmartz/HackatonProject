package Controller;

import Model.Vacation;
import Model.UserDatabase;
import View.AbstractView;
import View.PublishVacationView;

import java.time.LocalDate;

public class PublishController extends UserController {

    /**
     * The constructor
     */
    public PublishController()
    {
        PublishVacationView publishVacationView = (PublishVacationView) view;
        Vacation vacation = userDatabase.getVacation(publishVacationView.getvacationIDText());
        if (vacation == null) {
            userDatabase.addVacation(publishVacationView.getvacationIDText(), publishVacationView.getDestinetionContryTXT(), publishVacationView.getNumOfTicketsTXT(),
                    publishVacationView.getFlightCompanyTXT(), publishVacationView.getBaggageTXT(), publishVacationView.getKindOfVacationTXT(), publishVacationView.getKindOfSleepingPlaceTXT(),
                    publishVacationView.getTheRateOfTheSleepingPlaceTXT(), publishVacationView.getToDateTXT(), publishVacationView.getFromDateTXT(),
                    publishVacationView.getKindOfTicketTXT(), publishVacationView.getIsTheSleepingCostsIncludesTXT(), publishVacationView.getIsThereReturnFlightTXT() );
            userView.mainMenu();
            userView.setupView(userDatabase);
        }
        else {
            publishVacationView.setComments("Vacation already exists!");
        }
    }

    public void birthdatePicked() {
        PublishVacationView publishVacationView = (PublishVacationView) view;
        LocalDate dateOfVacation = publishVacationView.getToDateTXT();
        if (isLegalDateForVacation(dateOfVacation.toString())) {
            publishVacationView.setToDate(.toString());
            signUpView.KeyReleased();
        } else {
            signUpView.setComments("Age must be over 18 years");
            signUpView.clearBirthdayPicker();
        }
    }

    /**
     * This function will set the right view for this class
     */
    public void setView(AbstractView abstractView) {

        if(abstractView instanceof PublishVacationView)
            super.setView(abstractView);
        else
        {
            super.setView(null);
        }
    }
}
