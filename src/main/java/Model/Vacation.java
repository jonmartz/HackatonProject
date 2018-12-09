package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Vacation {
    public String AdultTickets;
    public String KidTickets;
    public String BabyTickets;
    public String ID;
    public String destinationCountryTXT;
    public String flightCompanyTXT;
    public String baggageTXT;
    public String kindOfVacationTXT;
    public String kindOfSleepingPlaceTXT;
    public String theRateOfTheSleepingPlaceTXT;
    public String toDateTXT;
    public String fromDateTXT;
    public String isTheSleepingCostsIncludesTXT;
    public String isThereReturnFlightTXT;
    public String price;
    public String ownerID;
    public String ticketPicturePath;

    public Vacation(){ }
    
//    public Vacation(String destinationCountryTXT, String NumOfTickets, String flightCompanyTXT,
//                    String baggageTXT, String kindOfVacationTXT, String kindOfSleepingPlaceTXT,
//                    String theRateOfTheSleepingPlaceTXT, String toDateDatePicker, String fromDateDatePicker,
//                    String kindOfTicketTXT, String isTheSleepingCostsIncludesTXT,
//                    String isThereReturnFlightTXT, String price)
//    {
//        this.baggageTXT= baggageTXT;
//        this.destinationCountryTXT = destinationCountryTXT;
//        this.flightCompanyTXT= flightCompanyTXT;
//        this.fromDateDatePicker= fromDateDatePicker;
//        this.isThereReturnFlightTXT= isThereReturnFlightTXT;
//        this.isTheSleepingCostsIncludesTXT= isTheSleepingCostsIncludesTXT;
//        this.kindOfSleepingPlaceTXT= kindOfSleepingPlaceTXT;
//        this.kindOfTicketTXT= kindOfTicketTXT;
//        this.NumOfTickets = NumOfTickets;
//        this.toDateDatePicker= toDateDatePicker;
//        this.theRateOfTheSleepingPlaceTXT= theRateOfTheSleepingPlaceTXT;
//        this.kindOfVacationTXT= kindOfVacationTXT;
//        this.price = price;
//    }

    public Vacation(ResultSet resultSet){
        try {
            this.ID = resultSet.getString("rowid");
            this.destinationCountryTXT = resultSet.getString("destinetionContryTXT");
            this.AdultTickets = resultSet.getString("AdultTicketsTXT");
            this.KidTickets = resultSet.getString("KidTicketsTXT");
            this.BabyTickets = resultSet.getString("BabyTicketsTXT");
            this.flightCompanyTXT = resultSet.getString("flightCompanyTXT");
            this.baggageTXT = resultSet.getString("baggageTXT");
            this.kindOfVacationTXT = resultSet.getString("kindOfVacationTXT");
            this.kindOfSleepingPlaceTXT = resultSet.getString("kindOfSleepingPlaceTXT");
            this.theRateOfTheSleepingPlaceTXT = resultSet.getString("theRateOfTheSleepingPlaceTXT");
            this.toDateTXT = resultSet.getString("toDateTXT");
            this.fromDateTXT = resultSet.getString("fromDateTXT");
            this.isTheSleepingCostsIncludesTXT = resultSet.getString("isTheSleepingCostsIncludesTXT");
            this.isThereReturnFlightTXT = resultSet.getString("isThereReturnFlightTXT");
            this.price = resultSet.getString("priceTXT");
            this.ownerID = resultSet.getString("ownerIDTXT");
            this.ticketPicturePath = resultSet.getString("ticketPicture");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}