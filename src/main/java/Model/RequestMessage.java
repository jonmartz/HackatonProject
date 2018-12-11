package Model;

public class RequestMessage extends Message {

    private Vacation vacation;//the vacation
    private boolean accepted;//True if the user accepted
    /**
     * This is the constructor of the class
     *
     * @param sender   - The user that sent the message
     * @param receiver - The user that received the message
     * @param vacation - The vacation that the sender requested
     */
    public RequestMessage(String sender, String receiver,Vacation vacation) {
        super(sender, receiver);
        this.vacation = vacation;
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
    protected RequestMessage(String sender,String receiver, String date,String time,int myId, boolean hasbeenRead,Vacation vacation) {
        super(sender,receiver,date,time,myId,hasbeenRead);
        this.vacation =vacation;
        this.accepted = false;
    }
    @Override
    /**
     * This function will return the kind of message
     * @return - The kind of message
     */
    public String getKind()
    {
        return "Request";
    }

    @Override
    /**
     * This function will return the content of the message
     * @return - The content of the message
     */
    public String getContent()
    {

        return this.getSender()+" wants to buy your vacation to "+vacation.destinationCountryTXT +" from "+vacation.fromDateTXT +" to "+vacation.toDateTXT+ ".\nWould you like to sell the vacation to him? ";
    }


    @Override
    /**
     * This function will return the Headline of the message
     * @return - The Headline of the message
     */
    public String getHeadline() {
        return "Hey There!\n" +this.getSender() +" wants to buy your vacation!";
    }

    @Override
    /**
     * This function will return the name of the fxml file that is related to the message
     * @return - The name of the fxml file that is related to the message
     */
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
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
