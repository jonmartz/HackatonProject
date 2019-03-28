package Controller;

import Model.*;
import View.RequestMessageView;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.util.List;

/**
 * Controller for the message request view
 */
public class RequestMessageController extends MessageController {

    private boolean isTrade = false;

    @Override
    protected void FillAllData() {
        // Make the check offered vacation visible or not, in case of trade or not
        RequestMessage currentMessage = (RequestMessage) this.getCurrentMessage();
//        Vacation vacation = currentMessage.getVacation();
        if (currentMessage.isAccepted() ){//|| database.getUnavailableVacationIDs().contains(vacation.ID)){
            // change the accept request button to the confirm payment button
            Button acceptRequestButton = ((RequestMessageView) view).acceptRequestButton;
            acceptRequestButton.setVisible(false);
//            Button confirmPaymentButton = ((RequestMessageView) view).confirmPaymentButton;
//            confirmPaymentButton.setVisible(true);
        }
        // make the buttons visible depending on cash payment / trade
//        if (currentMessage.offeredVacation == null){ // cash
//            ((RequestMessageView)view).offeredVacationButton.setVisible(false);
//        }else{ // trade
//            isTrade = true;
//            Button acceptRequestButton = ((RequestMessageView) view).acceptRequestButton;
//            acceptRequestButton.setVisible(false);
//            Button confirmPaymentButton = ((RequestMessageView) view).confirmPaymentButton;
//            confirmPaymentButton.setText("Confirm Trade");
//            confirmPaymentButton.setVisible(true);
//        }
    }

    /**
     * Accept a buy request from a user that want to buy the vacation with cash
     */
    public void acceptRequest()
    {
        RequestMessage currentMessage = (RequestMessage) this.getCurrentMessage();
        String course = currentMessage.getCourse();
        String year = currentMessage.getYear();
        String semester = currentMessage.getSemester();
        String text = currentMessage.getText();
        // send acceptance message
        AcceptanceMessage acceptanceMessage = new AcceptanceMessage(currentMessage.getReceiver(),
                currentMessage.getSender(), course,year,semester);
        this.database.addMessage(acceptanceMessage.getSender(), acceptanceMessage.getReceiver(), course,semester,year,
                false, acceptanceMessage.getDate(), acceptanceMessage.getTime(),
                acceptanceMessage.getKind(), text);

        currentMessage.setAccepted(true);
        view.ShowPopUp("You have accepted the request to buy your vacation. After getting the cash, confirm" +
                " the payment using the button below.");
        FillAllData(); // to change the button text to show "confirm payment"
    }

    /**
     * Confirm the request, checking either cash or trade case
     */
//    public void confirm() {
//        if (isTrade) confirmTrade();
//        else confirmPayment();
//    }

    /**
     * Activated with the confirm trade button. Asks the user to confirm trade and executes the trade.
     */
//    public void confirmTrade()
//    {
//        RequestMessage currentMessage = (RequestMessage)this.getCurrentMessage();
//        String buyerID = currentMessage.getSender();
//        String sellerID = currentMessage.getReceiver();
//        Vacation requestedVacation = currentMessage.getVacation();
//        Vacation offeredVacation = currentMessage.offeredVacation;
//        if(!currentMessage.hasConfirmed()){
//            boolean confirmed = database.isMessageConfirmed(String.valueOf(currentMessage.getId()));
//            currentMessage.setConfirmed(confirmed);
//        }
//        if(!currentMessage.hasConfirmed()) { // not yet confirmed
//            // ask user for trade confirmation
//            if (view.getResultFromWarning("Are you sure you want to execute the trade?\n") == ButtonType.NO){
//                return;
//            }
//            view.ShowPopUp("Vacation trade was executed successfully!");
//
//            // add payments to database
//            Payment payment1 = new TradePayment(requestedVacation.ID, buyerID,
//                    sellerID, offeredVacation.ID);
//            Payment payment2 = new TradePayment(offeredVacation.ID, buyerID,
//                    sellerID, requestedVacation.ID);
//            database.addPayment(payment1);
//            database.addPayment(payment2);
//
//            // send completion messages to both parties
//            CompletionMessage completionMessage1 = new CompletionMessage(buyerID, sellerID, requestedVacation, offeredVacation);
//            CompletionMessage completionMessage2 = new CompletionMessage(sellerID, buyerID, offeredVacation, requestedVacation);
//            database.addMessage(completionMessage1.getSender(), completionMessage1.getReceiver(), offeredVacation.ID,
//                    false, completionMessage1.getDate(), completionMessage1.getTime(),
//                    completionMessage1.getKind(), requestedVacation.ID);
//            database.addMessage(completionMessage2.getSender(), completionMessage2.getReceiver(), requestedVacation.ID,
//                    false, completionMessage2.getDate(), completionMessage2.getTime(),
//                    completionMessage2.getKind(), offeredVacation.ID);
//            currentMessage.setConfirmed(true);
//            database.confirmMessage(String.valueOf(currentMessage.getId()));
//
//            // change vacation's owner
//            database.setVacationOwner(requestedVacation.ID, buyerID);
//            database.setVacationOwner(offeredVacation.ID, sellerID);
//
//            User currentUser = database.getCurrentUser();
//            List<Message> messages = database.getAllMessagesByRecieverId(currentUser.username);
//            currentUser.mailBox = new MailBox(messages);
//        }
//        else{
//            view.ShowPopUp("You already confirmed the trade for this vacation!");
//        }
//        goBack();
//    }

    /**
     * Display a window with the vacation details
     */
//    public void checkOfferedVacation() {
//        RequestMessage currentMessage = (RequestMessage) this.getCurrentMessage();
//        Vacation offeredVacation = currentMessage.offeredVacation;
//        CheckUser(offeredVacation.ID, false);
//    }

    /**
     * Confirm the cash payment and change the vacation's owner
     */
//    public void confirmPayment() {
//        RequestMessage currentMessage = (RequestMessage)this.getCurrentMessage();
//        if(!currentMessage.hasConfirmed()){
//            boolean confirmed = database.isMessageConfirmed(String.valueOf(currentMessage.getId()));
//            currentMessage.setConfirmed(confirmed);
//        }
//        if(!currentMessage.hasConfirmed()) { // not yet confirmed
//            // ask user for trade confirmation
//            if (view.getResultFromWarning("Are you sure you want to confirm the payment?\n") == ButtonType.NO){
//                return;
//            }
//            view.ShowPopUp("Payment confirmed, your vacation has been transferred to the new owner.");
//
//            // add payment to database
//            Payment payment = new Payment(vacation.ID, currentMessage.getReceiver(), currentMessage.getSender());
//            this.database.addPayment(payment);
//
//            CompletionMessage completionMessage1 = new CompletionMessage(currentMessage.getReceiver(),
//                    currentMessage.getSender(), vacation, null);
//            this.database.addMessage(completionMessage1.getSender(), completionMessage1.getReceiver(), vacation.ID,
//                    false, completionMessage1.getDate(), completionMessage1.getTime(),
//                    completionMessage1.getKind(), "");
//            currentMessage.setConfirmed(true);
//            database.confirmMessage(String.valueOf(currentMessage.getId()));
//
//            // change vacation's owner
//            database.setVacationOwner(vacation.ID, currentMessage.getSender());
//        }
//        else{
//            view.ShowPopUp("You already confirmed the payment of this vacation!");
//        }
//        goBack();
//    }
}
