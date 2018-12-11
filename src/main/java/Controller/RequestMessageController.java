package Controller;

import Model.AcceptanceMessage;
import Model.RequestMessage;
import Model.Vacation;

public class RequestMessageController extends MessageController {

    public void acceptRequest()
    {

        RequestMessage currentMessage = (RequestMessage) this.getCurrentMessage();
        if(!currentMessage.isAccepted())
        {
            Vacation vacation = currentMessage.getVacation();
            AcceptanceMessage acceptanceMessage =new AcceptanceMessage(currentMessage.getReceiver(),currentMessage.getSender(),vacation);
            this.database.addMessage(acceptanceMessage.getSender(),acceptanceMessage.getReceiver(),vacation.ID,false,currentMessage.getDate(),currentMessage.getTime(),currentMessage.getKind());
            currentMessage.setAccepted(true);

        }
        goBack();
    }
}
