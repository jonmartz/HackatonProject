package View;

import Controller.VacationSearchController;
import Model.Vacation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class VacationSearchView extends AbstractView {

    @FXML
    public Button searchButton;
    public DatePicker fromDateDatePicker;
    public DatePicker toDateDatePicker;
    public TextField adultCountTextBox;
    public TextField kidCountTextBox;
    public TextField babyCountTextBox;
    public TableView searchResultsTableView;
    public TableColumn userColumn;
    public TableColumn priceColumn;
    public TableColumn buttonColumn;
    public Text commentsText;
    public int ticketCount;
    public ChoiceBox YearChoiceBox;
    public ChoiceBox SemesterChoiceBox;
    public ChoiceBox CourseIDChoiceBox;

    /**
     * This function will initialize an instance of this class
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        VacationSearchController controller = new VacationSearchController();
        this.setController(controller);
        controller.setView(this);
        commentsText.setText("");

        // Set columns of search results table
        userColumn.setCellValueFactory(new PropertyValueFactory<VacationEntry, String>("country"));
        buttonColumn.setCellValueFactory(new PropertyValueFactory<>("null")); // just for setting up buttons
        buttonColumn.setCellFactory(getButtonCallback());

    }

    /**
     * Function used to set the button column correctly (taken from stackoverflow)
     * @return Callback
     */
    private Callback<TableColumn<VacationEntry, String>, TableCell<VacationEntry, String>> getButtonCallback() {

        return new Callback<TableColumn<VacationEntry, String>, TableCell<VacationEntry, String>>() {
            @Override
            public TableCell call(final TableColumn<VacationEntry, String> param) {
                final TableCell<VacationEntry, String> cell = new TableCell<VacationEntry, String>() {

                    final Button button = new Button("View this partner");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            button.setOnAction(event -> {
                                VacationEntry vacationEntry = getTableView().getItems().get(getIndex());
                                getController().CheckUser(vacationEntry.ID);

                            });
                            setGraphic(button);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
    }

    /**
     * Go to vacation details
     * @param vacationID id of vacation to check
     */
//    private void CheckUser(String vacationID) { this.getController().CheckUser(vacationID, true); }

    /**
     * Clear the to date picker text
     */
    public void ClearToDatePicker() {
        this.toDateDatePicker.getEditor().clear();
    }
    /**
     * This function will clear the text in the datePicker field
     */
    public void ClearFromDatePicker() {
        this.fromDateDatePicker.getEditor().clear();
    }

    /**
     * This function will set the comment text
     * @param comment - The given comment
     */
    public void setComments(String comment) {
        this.commentsText.setText(comment);
    }

    /**
     * Checks that only numbers are set in ticket number fields
     */
    public void CheckTicketCountText() {

        String adults = adultCountTextBox.getText();
        String kids = kidCountTextBox.getText();
        String babies = babyCountTextBox.getText();

        // Replace chars if theyre not numbers
        if (!adults.matches("\\d*") || !kids.matches("\\d*") || !babies.matches("\\d*")) {
            adultCountTextBox.setText(adults.replaceAll("[^\\d]", ""));
            kidCountTextBox.setText(kids.replaceAll("[^\\d]", ""));
            babyCountTextBox.setText(babies.replaceAll("[^\\d]", ""));
//            setComments("only numbers allowed in \"country\"");
        }

        adults = adultCountTextBox.getText();
        kids = kidCountTextBox.getText();
        babies = babyCountTextBox.getText();

        ticketCount = 0;
        try {
            if (!adults.isEmpty()) ticketCount += Integer.parseInt(adultCountTextBox.getText());
            if (!kids.isEmpty()) ticketCount += Integer.parseInt(kidCountTextBox.getText());
            if (!babies.isEmpty()) ticketCount += Integer.parseInt(babyCountTextBox.getText());
            ((VacationSearchController)getController()).CheckEnableSearchButton();

        } catch (NumberFormatException e) {
            setComments("Woah, too many tickets!!!");
        }
    }

    /**
     * This function will occur when the "VacationSearch" button is pressed
     */
    public void SearchVacation() { GetVacations(false);}

    /**
     * Is called by the show all vacations button
     */
    public void showAllVacations() { GetVacations(true);}

    /**
     * Will get all relevant vacations from the controller and add them to the table view.
     * @param getAll true to get all vacations, false to get only vacations relevant
     *               according to the search parameters
     */
    public void GetVacations(boolean getAll) {
        VacationSearchController controller = (VacationSearchController) getController();
        List<String> partners = controller.GetRelevantPartners();
        ObservableList<VacationEntry> items = FXCollections.observableArrayList();
        for (String partner : partners){
            items.add(new VacationEntry(partner, null,
                    null,null, null));
        }
        searchResultsTableView.setItems(items);
        searchResultsTableView.setVisible(true);
    }

    /**
     * Is called when a country is picked from the country choiceBox
     */
    public void CountryPicked() {
        ((VacationSearchController)getController()).CheckEnableSearchButton();
    }


    /**
     * The purpose of this class is only to make the TableView
     * for displaying the vacation search results.
     */
    public static class VacationEntry {

        // string holders
        public String ID;
        private final String Country; // of destination
        private final String From; // date
        private final String To; // date
        private final String Price;

        /**
         * Constructor
         * @param ID                    of entry
         * @param destinationCountryTXT of entry
         * @param fromDateTXT           of entry
         * @param toDateTXT             of entry
         * @param priceTXT              of entry
         */
        public VacationEntry(String ID, String destinationCountryTXT, String fromDateTXT,
                             String toDateTXT, String priceTXT) {
            this.ID = ID;
            this.Country = destinationCountryTXT;
            this.From = fromDateTXT;
            this.To = toDateTXT;
            this.Price = priceTXT;
        }

        /**
         * getter
         * @return string
         */
        public String getCountry() {
            return Country;
        }

        /**
         * getter
         * @return string
         */
        public String getFrom() {
            return From;
        }

        /**
         * getter
         * @return string
         */
        public String getPrice() {
            return Price;
        }

        /**
         * getter
         * @return string
         */
        public String getTo() {
            return To;
        }
    }
}