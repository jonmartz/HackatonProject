package Controller;

import Model.*;
import javafx.scene.control.ButtonType;

import java.util.HashSet;

/**
 * Controller for the accepted message view
 */
public class AcceptanceMessageController extends MessageController {

    public void payment()
    {
        AcceptanceMessage currentMessage = (AcceptanceMessage)this.getCurrentMessage();
        Vacation vacation = currentMessage.getVacation();
        if(!currentMessage.hasPayed()){
            HashSet<String> IDs = database.getVacationIDsFromAllTransactions();
            if (IDs.contains(vacation.ID)) currentMessage.setPayed(true);
        }
        if(!currentMessage.hasPayed()) {
            if (view.getResultFromWarning("Are you sure you want to pay this vacation?\n") == ButtonType.NO){
                return;
            }
            view.getResultFromWarning("Pay with paypal? (If you choose no then you will pay with VISA)");
            view.ShowPopUp("Your payment was executed successfully!");

            Payment payment = new Payment(vacation.ID, currentMessage.getReceiver());
            CompletionMessage completionMessage1 = new CompletionMessage(currentMessage.getReceiver(), currentMessage.getSender(), payment, vacation);
            CompletionMessage completionMessage2 = new CompletionMessage(currentMessage.getSender(), currentMessage.getReceiver(), payment, vacation);

            this.database.addTransaction(payment);
            this.database.addMessage(completionMessage1.getSender(), completionMessage1.getReceiver(), vacation.ID, false, completionMessage1.getDate(), completionMessage1.getTime(), completionMessage1.getKind());
            this.database.addMessage(completionMessage2.getSender(), completionMessage2.getReceiver(), vacation.ID, false, completionMessage2.getDate(), completionMessage2.getTime(), completionMessage2.getKind());
            currentMessage.setPayed(true);
        }
        else{
            view.ShowPopUp("You already payed for this vacation!");
        }
        goBack();
    }
}
