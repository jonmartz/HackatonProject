package Model;

/**
 * Represents a completion message, which indicates a successful purchase / trade
 */
public class CompletionMessage extends Message {

    private String course;
    private String year;
    private String semester;

    /**
     * This is the constructor of the class
     *
     * @param sender   - The user that sent the message
     * @param receiver - The user that received the message
     */
    public CompletionMessage(String sender, String receiver, String course, String year, String semester) {
        super(sender, receiver);
        this.course = course;
        this.year = year;
        this.semester = semester;
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
    protected CompletionMessage(String sender, String receiver, String date, String time,
                                int myId, boolean hasbeenRead, String course, String year, String semester) {
        super(sender, receiver, date, time, myId, hasbeenRead);
        this.course = course;
        this.year = year;
        this.semester = semester;
    }

    /**
     * This function will return the kind of message
     *
     * @return - The kind of message
     */
    @Override
    public String getKind() {
        return "Completed";
    }

    /**
     * This function will return the content of the message
     *
     * @return - The content of the message
     */
    @Override
    public String getContent() {
        return "The connection has been completed successfully!\n Yoe and " + getReceiver() + "are mates now in the course "
                + course;
    }

    /**
     * This function will return the Headline of the message
     *
     * @return - The Headline of the message
     */
    @Override
    public String getHeadline() {
        return "The connection has been completed successfully!";
    }

    /**
     * This function will return the name of the fxml file that is related to the message
     *
     * @return - The name of the fxml file that is related to the message
     */
    @Override
    public String getViewName() {
        return "completionMessage.fxml";
    }

    /**
     * This function will return the vacation
     *
     * @return - The vacation
     */
//    public Vacation getVacation() {
//        return this.vacation;
//    }
}