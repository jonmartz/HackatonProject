package Controller;

import Model.*;

public class AcceptanceMessageController extends MessageController {

    public void payment()
    {
        AcceptanceMessage currentMessage = (AcceptanceMessage)this.getCurrentMessage();
        if(!currentMessage.hasPayed()) {
            view.getResultFromWarning("Pay with paypal? (If you choose no then you will pay with VISA)");
            view.ShowPopUp("Your payment was executed successfully!");
            Vacation vacation = currentMessage.getVacation();
            Transaction transaction = new Transaction(vacation.ID, currentMessage.getReceiver());
            CompletionMessage completionMessage1 = new CompletionMessage(currentMessage.getReceiver(), currentMessage.getSender(), transaction, vacation);
            CompletionMessage completionMessage2 = new CompletionMessage(currentMessage.getSender(), currentMessage.getReceiver(), transaction, vacation);

            this.database.addTransaction(vacation.ID, transaction.buyerID);
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
