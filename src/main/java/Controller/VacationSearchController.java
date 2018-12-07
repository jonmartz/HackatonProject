package Controller;

import Model.Vacation;
import View.AbstractView;
import View.VacationSearchView;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class VacationSearchController extends AbstractController {

    /**
     * Is called when the toDate is picked. If dates are illegal clears the toDate datePicker.
     */
    public void ToDatePicked() {
        DatePicked(false);
    }

    /**
     * Is called when the fromDate is picked. If dates are illegal clears the fromDate datePicker.
     */
    public void FromDatePicked() {
        DatePicked(true);
    }

    /**
     * If dates are illegal clears the [dateToClear] date and displays an error message.
     *
     * @param clearFromDate true to clear fromDate, false to clear toDate
     */
    public void DatePicked(Boolean clearFromDate) {
        VacationSearchView view = (VacationSearchView) this.view;
        LocalDate fromDate = view.fromDateDatePicker.getValue();
        LocalDate toDate = view.toDateDatePicker.getValue();

        // If both dates are selected
        if (fromDate != null && toDate != null) {
            if (!fromDate.isAfter(toDate)) {
                CheckEnableSearchButton();
            }
            else {
                // but "from" is after "to"
                view.setComments("\"from\" can't be after \"to\" date!");
                if (clearFromDate) view.ClearFromDatePicker();
                else view.ClearToDatePicker();
            }
        }
    }

    /**
     * Transitions to the main menu.
     */
    public void mainMenu() {
        viewChanger.mainMenu();
        viewChanger.setupView(database);
    }

    /**
     * This function will set the right view for this class
     */
    @Override
    public void setView(AbstractView abstractView) {

        if (abstractView instanceof VacationSearchView)
            super.setView(abstractView);
        else {
            super.setView(null);
        }
    }

    /**
     * Checks if all the conditions to enable the search button are met, and if they do it enables it
     */
    public void CheckEnableSearchButton() {
        VacationSearchView view = (VacationSearchView) this.view;
        view.setComments("");
        if (view.countryTextBox.getText().isEmpty()
                || view.fromDateDatePicker.toString().isEmpty()
                || view.toDateDatePicker.toString().isEmpty()
                || view.ticketCount == 0) {
            view.searchButton.setDisable(true);
        } else {
            view.searchButton.setDisable(false);
        }
    }

    public ArrayList<Vacation> GetRelevantVacations(String relevantCountry,
                                                    LocalDate relevantFromDate, LocalDate relevantToDate) {

        ArrayList<Vacation> vacations = GetAllVacations();
        ArrayList<Vacation> relevantVacations = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Vacation vacation : vacations){
            String country = vacation.destinationCountryTXT.toLowerCase();
            LocalDate fromDate = LocalDate.parse(vacation.fromDateTXT, formatter);
            LocalDate toDate = LocalDate.parse(vacation.toDateTXT, formatter);
            if (country.equals(relevantCountry.toLowerCase())
                    && !(toDate.isBefore(relevantFromDate) || fromDate.isAfter(relevantToDate)))
                relevantVacations.add(vacation);
        }
        return relevantVacations;
    }
}
