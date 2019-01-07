package Controller;

import Model.*;
import View.AcceptanceMessageView;
import javafx.scene.control.ButtonType;

/**
 * Controller for the accepted message view
 */
public class AcceptanceMessageController extends MessageController {

    @Override
    protected void FillAllData() {
        // Make the check offered vacation visible or not, in case of trade or not
//        AcceptanceMessage currentMessage = (AcceptanceMessage) this.getCurrentMessage();
//        if (currentMessage.offeredVacation == null){
//            ((AcceptanceMessageView)view).confirmTradeButton.setVisible(false);
//        }
    }

//    /**
//     * Activated with the confirm trade button. Asks the user to confirm trade and executes the trade.
//     */
//    public void confirmTrade()
//    {
//        AcceptanceMessage currentMessage = (AcceptanceMessage)this.getCurrentMessage();
//        String buyerID = currentMessage.getReceiver();
//        String sellerID = currentMessage.getSender();
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
//        }
//        else{
//            view.ShowPopUp("You already confirmed the trade for this vacation!");
//        }
//        goBack();
//    }
}
