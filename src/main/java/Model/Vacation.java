package Model;

public class Vacation {
    public String vacationID;
    public String destinetionContryTXT;
    public String NumOfTicketsTXT;
    public String flightCompanyTXT;
    public String baggageTXT;
    public String kindOfVacationTXT;
    public String kindOfSleepingPlaceTXT;
    public String theRateOfTheSleepingPlaceTXT;
    public String toDateTXT;
    public String fromDateTXT;
    public String kindOfTicketTXT;
    public String isTheSleepingCostsIncludesTXT;
    public String isThereReturnFlightTXT;

    public Vacation(String destinetionContryTXT, String NumOfTicketsTXT, String flightCompanyTXT,
                    String baggageTXT, String kindOfVacationTXT, String kindOfSleepingPlaceTXT, String theRateOfTheSleepingPlaceTXT,
                    String toDateTXT, String fromDateTXT, String kindOfTicketTXT, String isTheSleepingCostsIncludesTXT, String isThereReturnFlightTXT)
    {
        this.baggageTXT= baggageTXT;
        this.destinetionContryTXT= destinetionContryTXT;
        this.flightCompanyTXT= flightCompanyTXT;
        this.fromDateTXT= fromDateTXT;
        this.isThereReturnFlightTXT= isThereReturnFlightTXT;
        this.isTheSleepingCostsIncludesTXT= isTheSleepingCostsIncludesTXT;
        this.kindOfSleepingPlaceTXT= kindOfSleepingPlaceTXT;
        this.kindOfTicketTXT= kindOfTicketTXT;
        this.NumOfTicketsTXT= NumOfTicketsTXT;
        this.toDateTXT= toDateTXT;
        this.theRateOfTheSleepingPlaceTXT= theRateOfTheSleepingPlaceTXT;
        this.kindOfVacationTXT= kindOfVacationTXT;
    }

    public Vacation(){

    }
}
