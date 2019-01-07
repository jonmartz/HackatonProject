package Model;

public class CompletionMessage extends Message {

//    private Payment payment; //The payment
    private Vacation vacation;//the vacation
    public Vacation offeredVacation;// vacation traded in trade completion

    /**
     * This is the constructor of the class
     *
     * @param sender      - The user that sent the message
     * @param receiver    - The user that received the message
     * @param vacation    - The vacation that the receiver requested
     */
//    public CompletionMessage(String sender, String receiver, Payment payment, Vacation vacation) {
    public CompletionMessage(String sender, String receiver, Vacation vacation, Vacation offeredVacation) {
        super(sender, receiver);
//        this.payment = payment;
        this.vacation = vacation;
        this.offeredVacation = offeredVacation;
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
     * @param vacation    - The vacation that the receiver requested
     */
    protected CompletionMessage(String sender, String receiver, String date, String time,
                                int myId, boolean hasbeenRead, Vacation vacation, Vacation offeredVacation) {
//                                int myId, boolean hasbeenRead, Payment payment, Vacation vacation) {
        super(sender, receiver, date, time, myId, hasbeenRead);
//        this.payment = payment;
        this.vacation = vacation;
        this.offeredVacation = offeredVacation;
    }


    /**
     * This function will return the kind of message
     * @return - The kind of message
     */
    @Override
    public String getKind() {
        return "Completed";
    }

    /**
     * This function will return the content of the message
     * @return - The content of the message
     */
    @Override
    public String getContent() {
        return "The payment has been completed successfully!\nThe flight to " + this.vacation.destinationCountryTXT +
                " will take place on " + this.vacation.fromDateTXT + " until the " + vacation.toDateTXT + ".\n" +
                ".\n Hoping too see you again!";
//                "The payment id is " + this.payment.getTransactionId() + ".\n Hoping too see you again!";
        // todo: check what to do with payment id. for now is commented out
    }

    /**
     * This function will return the Headline of the message
     * @return - The Headline of the message
     */
    @Override
    public String getHeadline() {
        return "The payment has been completed successfully!";
    }

    /**
     * This function will return the name of the fxml file that is related to the message
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
    public Vacation getVacation() {
        return this.vacation;
    }
}