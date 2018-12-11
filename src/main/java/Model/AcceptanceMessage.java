package Model;

public class AcceptanceMessage extends Message {

    private Vacation vacation;//the vacation
    private boolean hasPayed;
    /**
     * This is the constructor of the class
     *
     * @param sender   - The user that sent the message
     * @param receiver - The user that received the message
     * @param vacation - The vacation that the receiver requested
     */
    public AcceptanceMessage(String sender, String receiver,Vacation vacation) {
        super(sender, receiver);
        this.vacation = vacation;
        this.hasPayed = false;
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
    protected AcceptanceMessage(String sender,String receiver, String date,String time,int myId, boolean hasbeenRead,Vacation vacation) {
        super(sender,receiver,date,time,myId,hasbeenRead);
        this.vacation =vacation;


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
    public String getContent()
    {


        return this.getSender()+" has accepted your request to buy the vacation to "+vacation.destinationCountryTXT +" from "+vacation.fromDateTXT +" to "+vacation.toDateTXT+ ".\nClick on the button below to pay the agreed amount.";
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

    public boolean hasPayed() {
        return hasPayed;
    }

    public void setPayed(boolean hasPayed) {
        this.hasPayed = hasPayed;
    }
}

