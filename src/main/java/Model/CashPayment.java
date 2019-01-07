package Model;

/**
 * This class represents a cash Payment
 */
public class CashPayment extends Payment {

    public String amount; // amount of money to pay

    /**
     * This is the constructor
     * @param vacationID - The Id of the vacation that the buyer wants to buy
     * @param buyerID - The buyer ID
     * @param amount - to pay
     */
    public CashPayment(String vacationID, String buyerID, String sellerID, String amount)
    {
        super(vacationID,buyerID, sellerID);
        this.amount = amount;
    }

    /**
     * This is the empty constructor
     */
    public CashPayment()
    {
        super();
    }
}
