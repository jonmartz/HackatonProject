package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Represents a vacation object
 */
public class Vacation {
    public String AdultTickets;
    public String KidTickets;
    public String BabyTickets;
    public String ID;
    public String destinationCountryTXT;
    public String fromCountryTXT;
    public String fromCityTXT;
    public String destinationCityTXT;
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

    /**
     * Constructor
     */
    public Vacation(){ }

    /**
     * Constructor
     * @param resultSet from SQL statement, to get all fields
     */
    public Vacation(ResultSet resultSet){
        try {
            this.ID = resultSet.getString("rowid");
            this.destinationCountryTXT = resultSet.getString("semesterTextField");
            this.destinationCityTXT = resultSet.getString("destinetionCityTXT");
            this.fromCountryTXT = resultSet.getString("fromCountryTXT");
            this.fromCityTXT = resultSet.getString("fromCityTXT");
            this.AdultTickets = resultSet.getString("AdultTicketsTXT");
            this.KidTickets = resultSet.getString("KidTicketsTXT");
            this.BabyTickets = resultSet.getString("BabyTicketsTXT");
            this.flightCompanyTXT = resultSet.getString("yearTextField");
            this.baggageTXT = resultSet.getString("baggageTXT");
            this.kindOfVacationTXT = resultSet.getString("courseTextBox");
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