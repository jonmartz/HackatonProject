package Model;

public class AcceptanceMessage extends Message {

    private String course;
    private String year;
    private String semester;
    private boolean hasConfirmed;
    /**
     * This is the constructor of the class
     *
     * @param sender   - The user that sent the message
     * @param receiver - The user that received the message
     */
    public AcceptanceMessage(String sender, String receiver,String course, String year, String semester) {
        super(sender, receiver);
        this.course = course;
        this.year = year;
        this.semester = semester;
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
     */
    protected AcceptanceMessage(String sender,String receiver, String date,String time,int myId, boolean hasbeenRead,
                                String course, String year, String semester) {
        super(sender,receiver,date,time,myId,hasbeenRead);
        this.course = course;
        this.year = year;
        this.semester = semester;
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
        return "The connection has been completed successfully!\n Yoe and " + getReceiver() + "are mates now in the course "
                + course;
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

//    /**
//     * This function will return the vacation
//     * @return - The vacation
//     */
//    public Vacation getVacation(){return this.vacation;}

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

