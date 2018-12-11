package Controller;

import Model.AcceptanceMessage;
import Model.RequestMessage;
import Model.Vacation;

public class RequestMessageController extends MessageController {

    public void acceptRequest()
    {

        // TODO: 11/12/2018 dont allow if there is already and an Acceptance request for this vacation (check in database)
        RequestMessage currentMessage = (RequestMessage) this.getCurrentMessage();
        if(!currentMessage.isAccepted())
        {
            Vacation vacation = currentMessage.getVacation();
            AcceptanceMessage acceptanceMessage =new AcceptanceMessage(currentMessage.getReceiver(),currentMessage.getSender(),vacation);
            this.database.addMessage(acceptanceMessage.getSender(),acceptanceMessage.getReceiver(),vacation.ID,false,acceptanceMessage.getDate(),acceptanceMessage.getTime(),acceptanceMessage.getKind());
            currentMessage.setAccepted(true);


        }
        goBack();
    }
}
