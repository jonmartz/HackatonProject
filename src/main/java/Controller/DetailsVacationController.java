//package Controller;
//
//import Model.Message;
//import Model.RequestMessage;
//import Model.Vacation;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.control.ButtonType;
//import javafx.scene.image.Image;
//import javafx.stage.Stage;
//import View.DetailsVacationView;
//
//import java.util.ArrayList;
//
///**
// * Controller for the vacation details view
// */
//public class DetailsVacationController extends AbstractController {
//
//    /**
//     * Fill all the vacation details in view
//     */
//    public void fillFieldsWithVacationDetails() {
//        //Get the current vacation
//        Vacation vacation= database.getCurrentVacation();
//
//        //Fill the fields with the vacation's data
//        DetailsVacationView view = (DetailsVacationView) this.view;
//        view.setDestinationCountryTXT(vacation.destinationCountryTXT);
//        view.setFromDateTXT(vacation.fromDateTXT);
//        view.setToDateTXT(vacation.toDateTXT);
//        view.setKindOfVacationTXT(vacation.kindOfVacationTXT);
//        view.setFlightCompanyTXT(vacation.flightCompanyTXT);
//        view.setBaggageTXT(vacation.baggageTXT);
//        view.setKindOfSleepingPlaceTXT(vacation.kindOfSleepingPlaceTXT);
//        view.setTheRateOfTheSleepingPlaceTXT(vacation.theRateOfTheSleepingPlaceTXT);
//        view.setIsThereReturnFlightTXT(vacation.isThereReturnFlightTXT);
//        view.setPrice(vacation.price);
//        view.setIsTheSleepingCostsIncludesTXT(vacation.isTheSleepingCostsIncludesTXT);
//        view.adultTicketsText.setText(vacation.AdultTickets);
//        view.kidTicketsText.setText(vacation.KidTickets);
//        view.babyTicketsText.setText(vacation.BabyTickets);
//        view.ownerText.setText(vacation.ownerID);
//        view.fromCountryTXT.setText(vacation.fromCountryTXT);
//        view.fromCityTXT.setText(vacation.fromCityTXT);
//        view.destinationCityTXT.setText(vacation.destinationCityTXT);
//        Image image = view.getImage(vacation.ticketPicturePath);
//        if (image != null) view.ticketsImageView.setImage(image);
//
//        // in case of checking from a request message
//        if (!database.requestButtonVisible){
//            view.requestButton.setVisible(false);
//            database.requestButtonVisible = true;
//        }
//    }
//
//    @Override
//    protected void FillAllData() { fillFieldsWithVacationDetails();}
//
//    /**
//     * Activated by the buyVacation button
//     */
//    public void RequestVacation() {
//        // check if user is signed in
//        if (getCurrentUser() == null){
//            if (view.getResultFromWarning("You must sign in to buy a vacation.\nGo to sign in screen? (this window will stay open)") == ButtonType.YES)
//                signIn();
//            return;
//        }
//
//        Vacation currentVacation = database.getCurrentVacation();
//        if(!currentVacation.ownerID.equals(getCurrentUser().username)) {
//
//            // Check if user already sent a buy request for this vacation
//            boolean alreadySent = false;
//            ArrayList<Message> messages = database.getAllMessagesByRecieverId(currentVacation.ownerID);
//            for (Message message : messages){
//                if (message instanceof RequestMessage){
//                    RequestMessage requestMessage = (RequestMessage)message;
//                    if (requestMessage.getVacation().ID.equals(currentVacation.ID)
//                            && requestMessage.getSender().equals(getCurrentUser().username)){
//                        view.ShowPopUp("You already sent a buy request for this vacation!");
//                        alreadySent = true;
//                        viewChanger.closeSecondaryStage();
//                        break;
//                    }
//                }
//            }
//            if (!alreadySent) { // if this user has no yet sent a request for this vacation
//                viewChanger.closeSecondaryStage();
//                RequestVacationScreen(currentVacation);
//            }
//        }
//        else {
//            view.ShowPopUp("You are the owner of this vacation!");
//            viewChanger.closeSecondaryStage();
//        }
//    }
//}
