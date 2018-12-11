package Controller;

import Model.AcceptanceMessage;
import Model.RequestMessage;
import Model.Vacation;

/**
 * Controller for the message request view
 */
public class RequestMessageController extends MessageController {

    /**
     * Accept a buy request from a user that want to buy the vacation
     */
    public void acceptRequest()
    {
        RequestMessage currentMessage = (RequestMessage) this.getCurrentMessage();
        Vacation vacation = currentMessage.getVacation();
        if(!currentMessage.isAccepted() && !database.getAcceptedVacationIDs().contains(vacation.ID))
        {
            AcceptanceMessage acceptanceMessage =new AcceptanceMessage(currentMessage.getReceiver(),currentMessage.getSender(),vacation);
            this.database.addMessage(acceptanceMessage.getSender(),acceptanceMessage.getReceiver(),vacation.ID,false,acceptanceMessage.getDate(),acceptanceMessage.getTime(),acceptanceMessage.getKind());
            currentMessage.setAccepted(true);
            view.ShowPopUp("You have accepted the request to buy your vacation");
        }
        else view.ShowPopUp("You already accepted a request for this vacation!");
        goBack();
    }
}
