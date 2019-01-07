package Model;

public class RequestMessage extends Message {

    private Vacation vacation;//the vacation
    public Vacation offeredVacation;// vacation offered in trade request
    private boolean acceptedBySeller;//True if the user accepted
    private boolean hasConfirmed;
    /**
     * This is the constructor of the class
     *
     * @param sender   - The user that sent the message
     * @param receiver - The user that received the message
     * @param vacation - The vacation that the sender requested
     */
    public RequestMessage(String sender, String receiver,Vacation vacation, Vacation offeredVacation) {
        super(sender, receiver);
        this.vacation = vacation;
        this.offeredVacation = offeredVacation;
        this.hasConfirmed = false;
    }

    /**
     * This is the constructor of the class
     * @param sender - The user that sent the message
     * @param receiver - The user that received the message
     * @param date - The date of creation
     * @param time - The time of creation
     * @param myId - The id of the message
     * @param hasbeenRead - True if the message has been read
     * @param vacation - The vacation that the receiver requested
     * @param offeredVacation - The vacation that the receiver offered for trade
     */
    protected RequestMessage(String sender,String receiver, String date,String time,int myId, boolean hasbeenRead,
                             Vacation vacation, Vacation offeredVacation) {
        super(sender,receiver,date,time,myId,hasbeenRead);
        this.vacation =vacation;
        this.offeredVacation = offeredVacation;
        this.acceptedBySeller = false;
    }

    /**
     * This function will return the kind of message
     * @return - The kind of message
     */
    @Override
    public String getKind()
    {
        return "Request";
    }

    /**
     * This function will return the content of the message, adapted to cash or trade requests.
     * @return - The content of the message
     */
    @Override
    public String getContent()
    {
        if (offeredVacation == null) { // cash
            return this.getSender() + " wants to buy your vacation to " + vacation.destinationCountryTXT + " from " +
                    vacation.fromDateTXT + " to " + vacation.toDateTXT + "," +
                    "\nAnd pay by cash. Would you like to sell the vacation to him? ";
        }
        else{ // trade
            return this.getSender() + " wants to trade your vacation to " + vacation.destinationCountryTXT + " from " +
                    vacation.fromDateTXT + " to " + vacation.toDateTXT + "" +
                    "\nFor one of his vacations. Would you like to trade the vacation with him? ";
        }
    }


    /**
     * This function will return the Headline of the message
     * @return - The Headline of the message
     */
    @Override
    public String getHeadline() {
        if (offeredVacation == null) { // cash
            return "Hey There!\n" + this.getSender() + " wants to buy your vacation!";
        }else{ // trade
            return "Hey There!\n" + this.getSender() + " wants to trade your vacation!";
        }
    }

    /**
     * This function will return the name of the fxml file that is related to the message
     * @return - The name of the fxml file that is related to the message
     */
    @Override
    public String getViewName()
    {
        return "requestMessage.fxml";
    }
    /**
     * This function will return the vacation
     * @return - The vacation
     */
    public Vacation getVacation(){return this.vacation;}

    public boolean isAccepted() {
        return acceptedBySeller;
    }

    public void setAccepted(boolean accepted) {
        this.acceptedBySeller = accepted;
    }

    public boolean hasConfirmed() {
        return hasConfirmed;
    }

    public void setConfirmed(boolean hasConfirmed) {
        this.hasConfirmed = hasConfirmed;
    }
}
