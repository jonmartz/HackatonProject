package Model;

/**
 * This class represents a Trade Payment
 * A trade transaction is a trade between two vacations as a way of payment
 */
public class TradePayment extends Payment {

    public String offeredVacationID; // id of offered vacation

    /**
     * This is the constructor of the class
     * @param vacationID - The vacation id
     * @param buyerID - The buyer's id
     * @param sellerID - The seller's id
     * @param offeredVacationID - id of the vacation traded, or null in case of cash payment
     */
    public TradePayment(String vacationID, String buyerID, String sellerID, String offeredVacationID)
    {
        super(vacationID, buyerID, sellerID);
        this.offeredVacationID = offeredVacationID;
    }

    /**
     * This is the empty constructor
     */
    public TradePayment()
    {
        super();
    }
}
