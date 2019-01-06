package Model;

/**
 * This class represents a Trade Payment
 * A trade transaction is a trade between two vacations as a way of payment
 */
public class TradePayment extends Payment {

    public String payWithVacationId;//The id of the vacation that the buyer used to buy the other vacation

    /**
     * This is the constructor
     * @param vacationID - The Id of the vacation that the buyer wants to buy
     * @param buyerID - The buyer ID
     * @param payWithVacationId - The id of the vacation that the buyer used to buy the other vacation
     */
    public TradePayment(String vacationID, String buyerID, String payWithVacationId)
    {
        super(vacationID,buyerID);
        this.payWithVacationId = payWithVacationId;
    }

    /**
     * This is the empty constructor
     */
    public TradePayment()
    {
        super();
    }
}
