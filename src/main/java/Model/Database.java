package Model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;


/**
 * Manages a user database using SQLite, and holds current object pointers
 * (signed in user and vacation that he is checking)
 */
public class Database {
    private Connection connection;
    private User currentUser;
    private Vacation currentVacation;

    /**
     * Constructor. If the database.db doesn't exist, creates it.
     */
    public Database() {
        try {
            openConnection();
            Statement statement = connection.createStatement();

            // Create user table
            statement.executeUpdate("create table if not exists users (" +
                    "username string, " +
                    "password string, " +
                    "birthdate string, " +
                    "firstName string, " +
                    "lastName string, " +
                    "city string, " +
                    "picture string)");

            // Create vacations table
            statement.executeUpdate("create table if not exists vacations (" +
                    "destinetionContryTXT string, " +
                    "AdultTicketsTXT string, " +
                    "KidTicketsTXT string, " +
                    "BabyTicketsTXT string, " +
                    "flightCompanyTXT string, " +
                    "baggageTXT string, " +
                    "kindOfVacationTXT string, " +
                    "kindOfSleepingPlaceTXT string," +
                    "theRateOfTheSleepingPlaceTXT string, " +
                    "toDateTXT string, " +
                    "fromDateTXT string, " +
                    "isTheSleepingCostsIncludesTXT string, " +
                    "isThereReturnFlightTXT string, " +
                    "priceTXT string string, " +
                    "ownerIDTXT string, " +
                    "ticketPicture string)");

            // transaction table
            statement.executeUpdate("create table if not exists transactions (" +
                    "vacationID string, " +
                    "buyerID string)");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    /**
     * Opens a connection with the database.
     */
    private void openConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the connection with the database.
     */
    private void closeConnection() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Setter
     * @return user that is currently signed in
     */
    public User getCurrentUser() {
        return this.currentUser;
    }

    /**
     * Getter
     * @param user that is currently signed in
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    /**
     * Getter
     * @return vacation that is currently being viewed
     */
    public Vacation getCurrentVacation() {
        return currentVacation;
    }

    /**
     * Setter
     * @param currentVacation that is currently being viewed
     */
    public void setCurrentVacation(Vacation currentVacation) {
        this.currentVacation = currentVacation;
    }

    /**
     * Adds a user to the database.
     *  @param username             of user
     * @param password              of user
     * @param birthdate             of user
     * @param firstName             of user
     * @param lastName              of user
     * @param city                  of user
     * @param picture       of user
     */
    public void addUser(String username, String password, String birthdate, String firstName,
                        String lastName, String city, String picture) {
        try {
            openConnection();
            Statement statement = connection.createStatement();
            if (getUser(username) == null) {
                String command = "insert into users values(" +
                        "'" + username + "', " +
                        "'" + password + "', " +
                        "'" + birthdate + "', " +
                        "'" + firstName + "', " +
                        "'" + lastName + "', " +
                        "'" + city + "', " +
                        "'" + picture + "'" + ")";
                statement.executeUpdate(command);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    /**
     * Add transaction to database
     * @param vacationID of vacation purchased
     * @param buyerID    of user that purchased
     */
    public void addTransaction(String vacationID, String buyerID) {
        try {
            openConnection();
            Statement statement = connection.createStatement();
                String command = "insert into transactions values(" +
                        "'" + vacationID + "', " +
                        "'" + buyerID + "'" + ")";
                statement.executeUpdate(command);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    /**
     * Add a vacation to database.
     * @param countryTXT          of vacation
     * @param adultTicketsTXT               of vacation
     * @param kidTicketsTXT                 of vacation
     * @param babyTicketsTXT                of vacation
     * @param flightCompanyTXT              of vacation
     * @param baggageTXT                    of vacation
     * @param kindOfVacationTXT             of vacation
     * @param kindOfSleepingPlaceTXT        of vacation
     * @param theRateOfTheSleepingPlaceTXT  of vacation
     * @param toDate                        of vacation
     * @param fromDateTXT                   of vacation
     * @param isTheSleepingCostsIncludesTXT of vacation
     * @param isThereReturnFlightTXT        of vacation
     * @param priceTXT                      of vacation
     * @param ownerIDTXT                    of vacation
     */
    public void addVacation(String countryTXT, String adultTicketsTXT, String kidTicketsTXT, String babyTicketsTXT,
                            String flightCompanyTXT, String baggageTXT, String kindOfVacationTXT,
                            String kindOfSleepingPlaceTXT, String theRateOfTheSleepingPlaceTXT,
                            String toDate, String fromDateTXT, String isTheSleepingCostsIncludesTXT,
                            String isThereReturnFlightTXT, String priceTXT, String ownerIDTXT, String ticketPicturePath)
    {
        try {
            openConnection();
            Statement statement = connection.createStatement();
            String command = "insert into vacations values(" +
                    "'" + countryTXT + "', " +
                    "'" + adultTicketsTXT + "', " +
                    "'" + kidTicketsTXT + "', " +
                    "'" + babyTicketsTXT + "', " +
                    "'" + flightCompanyTXT + "', " +
                    "'" + baggageTXT + "', " +
                    "'" + kindOfVacationTXT + "', " +
                    "'" + kindOfSleepingPlaceTXT + "', " +
                    "'" + theRateOfTheSleepingPlaceTXT + "', " +
                    "'" + toDate + "', " +
                    "'" + fromDateTXT + "', " +
                    "'" + isTheSleepingCostsIncludesTXT + "', " +
                    "'" + isThereReturnFlightTXT + "', " +
                    "'" + priceTXT + "', " +
                    "'" + ownerIDTXT + "', " +
                    "'" + ticketPicturePath + "'" + ")";
            statement.executeUpdate(command);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    /**
     * Updates one field of a user
     *
     * @param username of user
     * @param field    to update
     * @param newValue to set on field
     */
    public void updateUser(String username, String field, String newValue) {
        try {
            openConnection();
            Statement statement = connection.createStatement();
            if (getUser(username) != null) {
                String command = "UPDATE users SET " + field + " = '" + newValue
                        + "' WHERE username = '" + username + "';";
                statement.executeUpdate(command);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    /**
     * Returns a user object from the data in the database.
     *
     * @param username of user
     * @return user, or null if user doesn't exist.
     */
    public User getUser(String username) {
        User user = null;
        try {
            openConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from users where username='" + username + "'");
            if (rs.next()) {
                user = new User();
                user.username = rs.getString("username");
                user.password = rs.getString("password");
                user.birthdate = rs.getString("birthdate");
                user.firstName = rs.getString("firstName");
                user.lastName = rs.getString("lastName");
                user.city = rs.getString("city");
                user.pictureFilePath = rs.getString("picture");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return user;
    }

    public Transaction getTransactionByVacationID(String vacationID) {
        Transaction transaction = null;
        try {
            openConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select rowid, * from transactions where vacationID='"
                    + vacationID + "'");
            if (resultSet.next()) {
                transaction = new Transaction();
                transaction.ID = resultSet.getString("rowid");
                transaction.vacationID = resultSet.getString("vacationID");
                transaction.buyerID = resultSet.getString("buyerID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return transaction;
    }
    /**
     * Get a single vacation object with the data from database
     * @param vacationID of vacation
     * @return vacation object
     */
    public Vacation getVacation(String vacationID) {
        Vacation vacation = null;
        try {
            openConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select rowid, * from vacations where rowid='" + vacationID + "'");
            if (resultSet.next()) vacation = new Vacation(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return vacation;
    }

    /**
     * Get all vacations from database as a list of vacation objects
     * @return vacation list
     */
    public ArrayList<Vacation> getAllVacations() {
        ArrayList<Vacation> vacations = null;
        try {
            openConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select rowid, * from vacations");
            vacations = new ArrayList<>();
            while (resultSet.next()) vacations.add(new Vacation(resultSet));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return vacations;
    }

    /**
     * Get all countries in database
     * @return country list
     */
    public HashSet<String> getAllCountries() {
        HashSet<String> countries = null;
        try {
            openConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select destinetionContryTXT from vacations");
            countries = new HashSet<>();
            while (resultSet.next()) countries.add(resultSet.getString(1).toUpperCase());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return countries;
    }

    /**
     * This function will delete a user from the database
     *
     * @param username - The username of the user
     */
    public void deleteUser(String username) {
        User user = getUser(username); // Get the user
        if (user != null) {
            openConnection();
            try {
                Statement statement = connection.createStatement();
                String command = "delete from users where " +
                        "username='" + user.username + "'";
                statement.executeUpdate(command);

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
        }
    }
}