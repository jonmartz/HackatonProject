package Model;

import java.time.LocalDate;

/**
 * Interface for a model object
 */
public interface IModel {
    public User getUser(String username);
    public void addUser(String username, String password, String birthdate, String firstName,
                        String lastName, String city);
    public void setCurrentUser(User user);
    public User getCurrentUser();
    public void updateUser(String username, String field ,String newValue);
    public void deleteUser(String username);
    public void addVacation(String vacationID, String destinetionContryTXT, String NumOfTicketsTXT, String flightCompanyTXT,
                            String baggageTXT, String kindOfVacationTXT, String kindOfSleepingPlaceTXT, String theRateOfTheSleepingPlaceTXT,
                            String toDateTXT, String fromDateTXT, String kindOfTicketTXT, String isTheSleepingCostsIncludesTXT, String isThereReturnFlightTXT);
    public Vacation getVacation(String vacationID);
}
