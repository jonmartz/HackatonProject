//package View;
//
////import Controller.MyVacationsController;
////import Controller.RequestVacationController;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.TableCell;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.util.Callback;
//
//import java.net.URL;
//import java.util.ResourceBundle;
//
//public class MyVacationsView extends AbstractView{
//
//    @FXML
//    public TableView vacationsTable;
//    public TableColumn countryColumn;
//    public TableColumn fromColumn;
//    public TableColumn toColumn;
//    public TableColumn priceColumn;
//    public TableColumn buttonColumn;
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        MyVacationsController myVacationsController = new MyVacationsController();
//        this.setController(myVacationsController);
//        myVacationsController.setView(this);
//
//        // Set columns of search results table
//        countryColumn.setCellValueFactory(new PropertyValueFactory<VacationSearchView.VacationEntry, String>("country"));
//        fromColumn.setCellValueFactory(new PropertyValueFactory<VacationSearchView.VacationEntry, String>("from"));
//        toColumn.setCellValueFactory(new PropertyValueFactory<VacationSearchView.VacationEntry, String>("to"));
//        priceColumn.setCellValueFactory(new PropertyValueFactory<VacationSearchView.VacationEntry, String>("price"));
//        buttonColumn.setCellValueFactory(new PropertyValueFactory<>("null")); // just for setting up buttons
//        buttonColumn.setCellFactory(getButtonCallback());
//    }
//
//    /**
//     * Function used to set the button column correctly (taken from stackoverflow)
//     * @return Callback
//     */
//    private Callback<TableColumn<VacationSearchView.VacationEntry, String>, TableCell<VacationSearchView.VacationEntry, String>> getButtonCallback() {
//
//        return new Callback<TableColumn<VacationSearchView.VacationEntry, String>, TableCell<VacationSearchView.VacationEntry, String>>() {
//            @Override
//            public TableCell call(final TableColumn<VacationSearchView.VacationEntry, String> param) {
//                final TableCell<VacationSearchView.VacationEntry, String> cell = new TableCell<VacationSearchView.VacationEntry, String>() {
//
//                    final Button button = new Button("View details");
//
//                    @Override
//                    public void updateItem(String item, boolean empty) {
//                        super.updateItem(item, empty);
//                        if (empty) {
//                            setGraphic(null);
//                            setText(null);
//                        } else {
//                            button.setOnAction(event -> {
//                                VacationSearchView.VacationEntry vacationEntry = getTableView().getItems().get(getIndex());
//                                viewVacationDetails(vacationEntry.ID);
//
//                            });
//                            setGraphic(button);
//                            setText(null);
//                        }
//                    }
//                };
//                return cell;
//            }
//        };
//    }
//
//    public void viewVacationDetails(String vacationID) {
//        ((MyVacationsController)getController()).viewVacationDetails(vacationID);
//    }
//}
