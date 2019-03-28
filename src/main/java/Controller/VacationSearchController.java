package Controller;

import Model.Vacation;
import View.VacationSearchView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Controller for the vacation search view
 */
public class VacationSearchController extends AbstractController {

//    /**
//     * Is called when the toDate is picked. If dates are illegal clears the toDate datePicker.
//     */
//    public void ToDatePicked() {
//        DatePicked(false);
//    }

//    /**
//     * Is called when the fromDate is picked. If dates are illegal clears the fromDate datePicker.
//     */
//    public void FromDatePicked() { DatePicked(true); }

//    /**
//     * If dates are illegal clears the [dateToClear] date and displays an error message.
//     * @param clearFromDate true to clear fromDate, false to clear toDate
//     */
//    private void DatePicked(Boolean clearFromDate) {
//        VacationSearchView view = (VacationSearchView) this.view;
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
//                CheckEnableSearchButton();
//            }
//            else {
//                // but "from" is after "to"
//                view.setComments("\"from\" can't be after \"to\" date!");
//                if (clearFromDate) view.ClearFromDatePicker();
//                else view.ClearToDatePicker();
//            }
//        }
//    }

    /**
     * Checks if all the conditions to enable the search button are met, and if they do it enables it
     */
    public void CheckEnableSearchButton() {
        VacationSearchView view = (VacationSearchView) this.view;
        view.setComments("");
        if (view.YearChoiceBox.getValue() == null
                || view.SemesterChoiceBox.getValue() == null
                || view.CourseIDChoiceBox.getValue() == null) {
            view.searchButton.setDisable(true);
        } else {
            view.searchButton.setDisable(false);
        }
    }

    /**
     * Get the relevant vacations according to the fields in the view
     * @return list of relevant countries
     */
    public List<String> GetRelevantPartners() {

        VacationSearchView view = (VacationSearchView)this.view;
        String relevantCourse = view.CourseIDChoiceBox.getValue().toString();
        String relevantsemester = view.SemesterChoiceBox.getValue().toString();
        String relevantYear = view.YearChoiceBox.getValue().toString();
        return learningUsers( relevantCourse, relevantsemester, relevantYear);
    }

    @Override
    protected void FillAllData() {
        SortedSet<String> courses = new TreeSet<>(GetAllCourses());
        ((VacationSearchView)view).CourseIDChoiceBox.getItems().addAll(courses);
    }
}
