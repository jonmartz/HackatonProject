package Model;

/**
 * This class represents a single transaction
 */
public class Payment {
    public String ID;
    public String vacationID;
    public String buyerID;
    public String sellerID;


    /**
     * This is the constructor of the class
     * @param vacationID - The vacation id
     * @param buyerID - The buyer's id
     * @param sellerID - The seller's id
     */
    public Payment(String vacationID, String buyerID, String sellerID)
    {
        this.vacationID = vacationID;
        this.sellerID = sellerID;
        this.buyerID = buyerID;
        this.ID = "";

    }

    /**
     * This is the empty constructor
     */
    public Payment()
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