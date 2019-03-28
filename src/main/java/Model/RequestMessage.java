package Model;

/**
 * Represents a request for vacation message
 */
public class RequestMessage extends Message {

    private String course;
    private String year;
    private String semester;
    private boolean acceptedBySeller;//True if the user accepted
    private boolean hasConfirmed; // true if owner has confirmed the payment / trade

    /**
     * This is the constructor of the class
     *
     * @param sender   - The user that sent the message
     * @param receiver - The user that received the message
     */
    public RequestMessage(String sender, String receiver, String course, String year, String semester) {
        super(sender, receiver);
        this.course = course;
        this.year = year;
        this.semester = semester;
        this.hasConfirmed = false;
    }

    /**
     * This is the constructor of the class
     *
     * @param sender      - The user that sent the message
     * @param receiver    - The user that received the message
     * @param date        - The date of creation
     * @param time        - The time of creation
     * @param myId        - The id of the message
     * @param hasbeenRead - True if the message has been read
     */
    protected RequestMessage(String sender, String receiver, String date, String time, int myId, boolean hasbeenRead,
                             String course, String year, String semester) {
        super(sender, receiver, date, time, myId, hasbeenRead);
        this.course = course;
        this.year = year;
        this.semester = semester;
        this.acceptedBySeller = false;
    }

    /**
     * This function will return the kind of message
     *
     * @return - The kind of message
     */
    @Override
    public String getKind() {
        return "Request";
    }

    /**
     * This function will return the content of the message, adapted to cash or trade requests.
     *
     * @return - The content of the message
     */
    @Override
    public String getContent() {
        return this.getSender() + " wants to be your mate in the course" + course + " in semester " +
                semester + " " + year + ".\n Would you like to be his mate?";
    }


    /**
     * This function will return the Headline of the message
     *
     * @return - The Headline of the message
     */
    @Override
    public String getHeadline() {
        return "Hey There!\n" + this.getSender() + " wants to be your mate!";

    }

    /**
     * This function will return the name of the fxml file that is related to the message
     *
     * @return - The name of the fxml file that is related to the message
     */
    @Override
    public String getViewName() {
        return "requestMessage.fxml";
    }


    /**
     * Check if the request was accepted
     *
     * @return true if accepted
     */
    public boolean isAccepted() {
        return acceptedBySeller;
    }

    /**
     * set the accepted status
     *
     * @param accepted to set
     */
    public void setAccepted(boolean accepted) {
        this.acceptedBySeller = accepted;
    }

    /**
     * Check if the payment or trade has been confirmed by the vacation owner
     *
     * @return true if confirmed
     */
    public boolean hasConfirmed() {
        return hasConfirmed;
    }

    /**
     * Set the payment or trade as confirmed by the vacation owner
     *
     * @param hasConfirmed to set
     */
    public void setConfirmed(boolean hasConfirmed) {
        this.hasConfirmed = hasConfirmed;
    }
}
