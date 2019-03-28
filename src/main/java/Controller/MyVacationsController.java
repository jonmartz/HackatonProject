//package Controller;
//
//import Model.Vacation;
//import View.MyVacationsView;
//import View.VacationSearchView;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.scene.control.TableView;
//
//import java.util.ArrayList;
//
///**
// * Controller for the vacation details view
// */
//public class MyVacationsController extends AbstractController {
//
//    @Override
//    protected void FillAllData() {
//        ArrayList<Vacation> vacations = GetAllVacationsOfCurrentUser();
//        ObservableList<VacationSearchView.VacationEntry> items = FXCollections.observableArrayList();
//        for (Vacation vacation : vacations){
//            items.add(new VacationSearchView.VacationEntry(vacation.ID, vacation.destinationCountryTXT,
//                    vacation.fromDateTXT, vacation.toDateTXT, vacation.price));
//        }
//        MyVacationsView myVacationsView = (MyVacationsView) this.view;
//        TableView vacationsTable = myVacationsView.vacationsTable;
//        vacationsTable.setItems(items);
//        vacationsTable.getSortOrder().add(myVacationsView.priceColumn);
//        vacationsTable.setVisible(true);
//    }
//
//    /**
//     * Display a window with the vacation details
//     */
//    public void viewVacationDetails(String vacationID) {
//        CheckUser(vacationID, false);
//    }
//}
