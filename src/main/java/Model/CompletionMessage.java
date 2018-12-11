package Model;

public class CompletionMessage extends Message {

    private Transaction transaction; //The transaction
    private Vacation vacation;//the vacation

    /**
     * This is the constructor of the class
     *
     * @param sender      - The user that sent the message
     * @param receiver    - The user that received the message
     * @param transaction - The transaction that has been made
     * @param vacation    - The vacation that the receiver requested
     */
    public CompletionMessage(String sender, String receiver, Transaction transaction, Vacation vacation) {
        super(sender, receiver);
        this.transaction = transaction;
        this.vacation = vacation;
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
     * @param transaction - The transaction that has been made
     * @param vacation    - The vacation that the receiver requested
     */
    protected CompletionMessage(String sender, String receiver, String date, String time, int myId, boolean hasbeenRead, Transaction transaction, Vacation vacation) {
        super(sender, receiver, date, time, myId, hasbeenRead);
        this.transaction = transaction;
        this.vacation = vacation;

    }

    @Override
    /**
     * This function will return the kind of message
     * @return - The kind of message
     */
    public String getKind() {
        return "Completed";
    }

    @Override
    /**
     * This function will return the content of the message
     * @return - The content of the message
     */
    public String getContent() {
        return "The payment has been completed successfully!\nThe flight to " + this.vacation.destinationCountryTXT + " will take place on " + this.vacation.fromDateTXT + " until the " + vacation.toDateTXT + ".\n" + "The transaction id is " + this.transaction.getTransactionId() + ".\n Hoping too see you again!";
    }


    @Override
    /**
     * This function will return the Headline of the message
     * @return - The Headline of the message
     */
    public String getHeadline() {
        return "The payment has been completed successfully!";
    }

    @Override
    /**
     * This function will return the name of the fxml file that is related to the message
     * @return - The name of the fxml file that is related to the message
     */
    public String getViewName() {
        return "completionMessage.fxml";
    }

    /**
     * This function will return the vacation
     *
     * @return - The vacation
     */
    public Vacation getVacation() {
        return this.vacation;
    }
}