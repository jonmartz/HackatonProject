package View;

import Controller.VacationSearchController;
import Controller.AbstractController;
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
import java.util.ResourceBundle;

public class VacationSearchView extends AbstractView {

    @FXML
    public Button searchButton;
    public TextField countryTextBox;
    public DatePicker fromDateDatePicker;
    public DatePicker toDateDatePicker;
    public TextField adultCountTextBox;
    public TextField kidCountTextBox;
    public TextField babyCountTextBox;
    public TableView searchResultsTableView;
    public TableColumn countryColumn;
    public TableColumn fromColumn;
    public TableColumn toColumn;
    public TableColumn priceColumn;
    public TableColumn buttonColumn;
    public Text commentsText;
    public int ticketCount;

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
        countryColumn.setCellValueFactory(new PropertyValueFactory<VacationEntry, String>("country"));
        fromColumn.setCellValueFactory(new PropertyValueFactory<VacationEntry, String>("from"));
        toColumn.setCellValueFactory(new PropertyValueFactory<VacationEntry, String>("to"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<VacationEntry, String>("price"));
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

                    final Button button = new Button("Check this vacation");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            button.setOnAction(event -> {
                                VacationEntry vacationEntry = getTableView().getItems().get(getIndex());
                                System.out.println(vacationEntry.ID); //todo: transition to vac details
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
     * Ths function will assign the given controller to it self if it's the right one
     * @param controller - The given controller
     */
    public void setController(AbstractController controller) {
        if (controller instanceof VacationSearchController)
            super.setController(controller);
        else {
            super.setController(null);
        }
    }

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
     * Ths function will occur when the user pick a date in the datePicker
     */
    public void ToDatePicked()
    {
        ((VacationSearchController)this.getController()).ToDatePicked();
    }
    /**
     * Ths function will occur when the user pick a date in the datePicker
     */
    public void FromDatePicked()
    {
        ((VacationSearchController)this.getController()).FromDatePicked();
    }
    /**
     * This function will occur when the "Go Back" button is pressed
     */
    public void mainMenu()
    {
        ((VacationSearchController)this.getController()).mainMenu();
    }

    /**
     * This function will set the comment text
     * @param comment - The given comment
     */
    public void setComments(String comment) {
        this.commentsText.setText(comment);
    }

    /**
     * Checks that only alphabetic chars are set in country field
     */
    public void CheckCountryText() {
        String text = countryTextBox.getText();
        if (!text.matches("[A-Za-z]*")) {
            countryTextBox.setText(text.replaceAll("[^A-Za-z]", ""));
//            setComments("only letters allowed in \"country\"");
        }
        ((VacationSearchController)getController()).CheckEnableSearchButton();
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
    public void SearchVacation() {
        ArrayList<Vacation> vacations = getController().GetAllVacations();
        ObservableList<VacationEntry> items = FXCollections.observableArrayList();
        for (Vacation vacation : vacations){
            items.add(new VacationEntry(vacation.ID, vacation.destinationCountryTXT,
                    vacation.fromDateTXT, vacation.toDateTXT, vacation.price));
        }
        searchResultsTableView.setItems(items);
        searchResultsTableView.getSortOrder().add(priceColumn);
        searchResultsTableView.setVisible(true);
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