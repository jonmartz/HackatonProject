package Model;

public class AcceptanceMessage extends Message {

    private Vacation vacation;//the vacation
    public Vacation offeredVacation;// vacation accepted in trade acceptance
    private boolean hasConfirmed;
    /**
     * This is the constructor of the class
     *
     * @param sender   - The user that sent the message
     * @param receiver - The user that received the message
     * @param vacation - The vacation that the receiver requested
     */
    public AcceptanceMessage(String sender, String receiver,Vacation vacation, Vacation offeredVacation) {
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
     */
    protected AcceptanceMessage(String sender,String receiver, String date,String time,int myId, boolean hasbeenRead,
                                Vacation vacation, Vacation offeredVacation) {
        super(sender,receiver,date,time,myId,hasbeenRead);
        this.vacation =vacation;
        this.offeredVacation = offeredVacation;
    }

    /**
     * This function will return the kind of message
     * @return - The kind of message
     */
    @Override
    public String getKind()
    {
        return "Acceptance";
    }

    /**
     * This function will return the content of the message
     * @return - The content of the message
     */
    @Override
    public String getContent() {
//        if (offeredVacation == null) { // cash
            return this.getSender() + " has accepted your request to buy the vacation to " + vacation.destinationCountryTXT
                    + " from " + vacation.fromDateTXT + " to " + vacation.toDateTXT + ".\nPlease send the cash to " +
                    this.getSender() + " and wait for confirmation.";
//        }else{ // trade
//            return this.getSender() + " has accepted your request to trade the vacation to " + vacation.destinationCountryTXT
//                    + " from " + vacation.fromDateTXT + " to " + vacation.toDateTXT + ".\nPress the button below to confirm the trade.";
//        }
    }


    /**
     * This function will return the Headline of the message
     * @return - The Headline of the message
     */
    @Override
    public String getHeadline() {
        return "Hey There!\n" +this.getSender() +" has accepted your request!";
    }

    /**
     * This function will return the name of the fxml file that is related to the message
     * @return - The name of the fxml file that is related to the message
     */
    @Override
    public String getViewName()
    {
        return "acceptanceMessage.fxml";
    }

    /**
     * This function will return the vacation
     * @return - The vacation
     */
    public Vacation getVacation(){return this.vacation;}

    /**
     * Check if the message has been confirmed
     * @return confirmed or not
     */
    public boolean hasConfirmed() {
        return hasConfirmed;
    }

    /**
     * Set confirmed
     * @param hasConfirmed to set
     */
    public void setConfirmed(boolean hasConfirmed) {
        this.hasConfirmed = hasConfirmed;
    }
}

