package Model;

import java.sql.*;
import java.time.LocalDate;


/**
 * Manages a user database using SQLite, and holds a current user (user that is currently signed in) field.
 */
public class UserDatabase implements IModel {
    private Connection connection;
    private User currentUser;
    private int vacationID;

    /**
     * Constructor. If the userDatabase.db doesn't exist, creates it.
     */
    public UserDatabase() {
        vacationID=0;
        try {
            openConnection();
            Statement statement1 = connection.createStatement();
            statement1.executeUpdate("create table if not exists users (username string, password string," +
                    "birthdate string, firstName string, lastName string, city string)");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            closeConnection();
        }
        try {
            openConnection();
            Statement statement2 = connection.createStatement();
            statement2.executeUpdate("create table if not exists vacations (vacationID string, destinetionContryTXT string," +
                    "NumOfTicketsTXT string, flightCompanyTXT string, baggageTXT string, kindOfVacationTXT string, kindOfSleepingPlaceTXT string," +
                    "theRateOfTheSleepingPlaceTXT string, toDateTXT string, fromDateTXT string, kindOfTicketTXT string," +
                    "isTheSleepingCostsIncludesTXT string, isThereReturnFlightTXT string)");
    } catch (SQLException e) {
        System.err.println(e.getMessage());
    } finally {
        closeConnection();
    }
    }

    /**
     * Opens a connection with the database.
     */
    private void openConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:userDatabase.db");
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
            System.err.println(e);
        }
    }

    /**
     * Adds a user to the database.
     *
     * @param username  of user
     * @param password  of user
     * @param birthdate of user
     * @param firstName of user
     * @param lastName  of user
     * @param city      of user
     */
    public void addUser(String username, String password, String birthdate, String firstName,
                        String lastName, String city) {
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
                        "'" + city + "'" + ")";
                statement.executeUpdate(command);
            } else {
                // Add user already exists stuff
                //System.out.println("user already exists");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void addVacation(String vacationID, String destinetionContryTXT, String NumOfTicketsTXT, String flightCompanyTXT,
                            String baggageTXT, String kindOfVacationTXT, String kindOfSleepingPlaceTXT, String theRateOfTheSleepingPlaceTXT,
                            String toDate, String fromDateTXT, String kindOfTicketTXT, String isTheSleepingCostsIncludesTXT, String isThereReturnFlightTXT)
    {
        try {
            openConnection();
            Statement statement = connection.createStatement();
            String command = "insert into vacation values(" +
                    "'" + vacationID + "', " +
                    "'" + destinetionContryTXT + "', " +
                    "'" + NumOfTicketsTXT + "', " +
                    "'" + flightCompanyTXT + "', " +
                    "'" + baggageTXT + "', " +
                    "'" + kindOfVacationTXT + "', " +
                    "'" + kindOfSleepingPlaceTXT + "', " +
                    "'" + theRateOfTheSleepingPlaceTXT + "', " +
                    "'" + toDate + "', " +
                    "'" + fromDateTXT + "', " +
                    "'" + kindOfTicketTXT + "', " +
                    "'" + isTheSleepingCostsIncludesTXT + "', " +
                    "'" + isThereReturnFlightTXT + "'" + ")";
            statement.executeUpdate(command);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void addVacation(){

    }

    @Override
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    @Override
    public User getCurrentUser() {
        return this.currentUser;
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
            } else {
                //the user name isn't exists
                //System.out.println("user isn't exists");
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
        try {
            openConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from users where username='" + username + "'");
            if (rs.next()) {
                User user = new User();
                user.username = rs.getString("username");
                user.password = rs.getString("password");
                user.birthdate = rs.getString("birthdate");
                user.firstName = rs.getString("firstName");
                user.lastName = rs.getString("lastName");
                user.city = rs.getString("city");
                return user;
            } else {
                // Add user not found stuff
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }
    public Vacation getVacation(String vacationID) {
        try {
            openConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from vacation where vacationID='" + vacationID + "'");
            if (rs.next()) {
                Vacation vacation = new Vacation();
                vacation.vacationID = rs.getString("vacationID");
                vacation.destinetionContryTXT = rs.getString("destinetionContryTXT");
                vacation.NumOfTicketsTXT = rs.getString("NumOfTicketsTXT");
                vacation.flightCompanyTXT = rs.getString("flightCompanyTXT");
                vacation.baggageTXT = rs.getString("baggageTXT");
                vacation.kindOfVacationTXT = rs.getString("kindOfVacationTXT");
                vacation.kindOfSleepingPlaceTXT = rs.getString("kindOfSleepingPlaceTXT");
                vacation.theRateOfTheSleepingPlaceTXT = rs.getString("theRateOfTheSleepingPlaceTXT");
                vacation.toDateTXT = rs.getString("toDateTXT");
                vacation.fromDateTXT = rs.getString("fromDateTXT");
                vacation.kindOfTicketTXT = rs.getString("kindOfTicketTXT");
                vacation.isTheSleepingCostsIncludesTXT = rs.getString("isTheSleepingCostsIncludesTXT");
                vacation.isThereReturnFlightTXT = rs.getString("isThereReturnFlightTXT");
                return vacation;
            } else {
                // Add user not found stuff
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }

    /**
     * This function will delete a user from the database
     *
     * @param username - The username of the user
     */
    public void deleteUser(String username) {
        User selectedUser = getUser(username); // Get the user
        //If the user dose not exist
        if (selectedUser == null) {
            // TODO: 10/21/2018 Add a text that says that this user dose not exist
        } else {
            openConnection();
            try {
                Statement statement = connection.createStatement();
                String command = "delete from users where " +
                        "username='" + selectedUser.username + "'";
                statement.executeUpdate(command);

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
        }
    }
}

