package Controller;

import Model.Vacation;
import View.AbstractView;
import View.VacationSearchView;

import java.awt.*;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;

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
    public void FromDatePicked() { DatePicked(true); }

    /**
     * If dates are illegal clears the [dateToClear] date and displays an error message.
     * @param clearFromDate true to clear fromDate, false to clear toDate
     */
    private void DatePicked(Boolean clearFromDate) {
        VacationSearchView view = (VacationSearchView) this.view;
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
     * Checks if all the conditions to enable the search button are met, and if they do it enables it
     */
    public void CheckEnableSearchButton() {
        VacationSearchView view = (VacationSearchView) this.view;
        view.setComments("");
        if (view.countryChoiceBox.getValue() == null
                || view.countryChoiceBox.getValue().toString().isEmpty()
                || view.fromDateDatePicker.toString().isEmpty()
                || view.toDateDatePicker.toString().isEmpty()
                || view.ticketCount == 0) {
            view.searchButton.setDisable(true);
        } else {
            view.searchButton.setDisable(false);
        }
    }

    /**
     * Get the relevant vacations according to the fields in the view
     * @return list of relevant countries
     */
    public ArrayList<Vacation> GetRelevantVacations() {

        VacationSearchView view = (VacationSearchView)this.view;
        String relevantCountry = view.countryChoiceBox.getValue().toString();
        LocalDate relevantFromDate = view.fromDateDatePicker.getValue();
        LocalDate relevantToDate = view.toDateDatePicker.getValue();

        // get ticket amount that is needed
        int adultsNeeded = 0;
        int kidsNeeded = 0;
        int babiesNeeded = 0;
        try { adultsNeeded = Integer.parseInt(view.adultCountTextBox.getText()); } catch (Exception ignored) {}
        try { kidsNeeded = Integer.parseInt(view.kidCountTextBox.getText()); } catch (Exception ignored) {}
        try { babiesNeeded = Integer.parseInt(view.babyCountTextBox.getText()); } catch (Exception ignored) {}

        ArrayList<Vacation> vacations = GetAllVacations();
        ArrayList<Vacation> relevantVacations = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        boolean foundAtLeastOneDate = false;
        boolean foundAtLeastOneTickets = false;

        for (Vacation vacation : vacations){
            boolean foundDate = false;
            boolean foundTickets = false;
            String country = vacation.destinationCountryTXT.toLowerCase();
            LocalDate fromDate = LocalDate.parse(vacation.fromDateTXT, formatter);
            LocalDate toDate = LocalDate.parse(vacation.toDateTXT, formatter);

            // Check that dates are in range
            if (country.equals(relevantCountry.toLowerCase())
                    && !(toDate.isBefore(relevantFromDate) || fromDate.isAfter(relevantToDate))) {
                foundDate = true;
                foundAtLeastOneDate = true;
            }

            // get tickets available
            int adultsAvailable = 0;
            int kidsAvailable = 0;
            int babiesAvailable = 0;
            try { adultsAvailable = Integer.parseInt(vacation.AdultTickets); } catch (Exception ignored) {}
            try { kidsAvailable = Integer.parseInt(vacation.KidTickets); } catch (Exception ignored) {}
            try { babiesAvailable = Integer.parseInt(vacation.BabyTickets); } catch (Exception ignored) {}

            if (adultsAvailable >= adultsNeeded && kidsAvailable >= kidsNeeded && babiesAvailable >= babiesNeeded){
                foundTickets = true;
                foundAtLeastOneTickets = true;
            }
            if (foundDate && foundTickets) relevantVacations.add(vacation);
        }
        // Check what was the problem (if)
        if (!foundAtLeastOneDate) view.setComments("No vacations in date range!");
        else if (!foundAtLeastOneTickets) view.setComments("No vacations with enough tickets!");

        return relevantVacations;
    }

    @Override
    protected void FillAllData() {
        SortedSet<String> countries = new TreeSet<>(GetAllCountries());
        ((VacationSearchView)view).countryChoiceBox.getItems().addAll(countries);
    }
}
