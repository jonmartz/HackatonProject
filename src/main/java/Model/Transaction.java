package Model;

/**
 * This class represents a single transaction
 */
public class Transaction {
    public String ID;
    public String vacationID;
    public String buyerID;


    /**
     * This is the constructor of the class
     * @param vacationID - The vacation id
     * @param buyerID - The buyer's id
     */
    public Transaction(String vacationID,String buyerID)
    {
        this.vacationID = vacationID;
        this.buyerID = buyerID;
        this.ID = "";

    }

    /**
     * This is the empty constructor
     */
    public Transaction()
    {

    }

    /**
     * This function will return the buyer id
     * @return - The buyer Id
     */
    public String getBuyerID() {
        return buyerID;
    }

    /**
     * This function will return the transaction id
     * @return - The transaction Id
     */
    public String getTransactionId() {
        return ID;
    }

    /**
     * This function will return the vacation id
     * @return - The vacation Id
     */
    public String getVacationID() {
        return vacationID;
    }
}