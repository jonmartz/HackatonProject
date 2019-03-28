package Model;

import javafx.scene.control.TextField;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Manages a user database using SQLite, and holds current object pointers
 * (signed in user and vacation that he is checking)
 */
public class Database {
    private Connection connection; // to database
    private User currentUser; // user that is currently signed in
    private Vacation currentVacation; // vacation that is currently being viewed
    private Message currentMessage; // message that is currently being viewed
    private int connectionStack = 0; // used to not open more than one connection

    public boolean requestButtonVisible = true; // for changing to vacation details view without the request button

    /**
     * Constructor. If the database.db doesn't exist, openConnection() creates it.
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
                    "phoneNumber string, " +
                    "description string, " +
                    "picture string)");

            //Create messages table
            statement.executeUpdate("create table if not exists messages (" +
                    "sender string, " +
                    "recipient string, " +
                    "course_id string, " +
                    "semester string, " +
                    "year string, " +
                    "hasBeenRead boolean, " +
                    "creationDate string, " +
                    "creationTime string, " +
                    "kind string, " +
                    "text string, " +
                    "confirmed string)");

            //Create course-student table
            statement.executeUpdate("create table if not exists learns (" +
                    "username string, " +
                    "course_id string, " +
                    "semester string, " +
                    "available string, " +
                    "year string) " );


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
            if (connectionStack == 0) { // if there's no connection. Else, don't open more connections
                connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            }
            connectionStack++;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the connection with the database.
     */
    private void closeConnection() {
        try {
            if (connection != null) {
                if (connectionStack == 1) {
                    // if this function wasn't called from a parent function that opened a connection, and didn't close it yet.
                    connection.close();
                }
                connectionStack--;
            }
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
     * @return current message
     */
    public Message getCurrentMessage() {
        return this.currentMessage;
    }

    /**
     * Getter
     * @param user that is currently signed in
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    /**
     * Setter. Also updates the message as "already read"
     * @param currentMessage to set
     */
    public void setCurrentMessage(Message currentMessage)
    {
        this.currentMessage = currentMessage;
        if(currentMessage!=null)
        {
            openConnection();
            Statement statement = null;
            try {
                statement = connection.createStatement();
                currentMessage.markAsRead();
                String command ="UPDATE messages" +
                        " SET hasBeenRead = true " +
                        "WHERE rowid = '"+ currentMessage.getId()+"';";
                statement.executeUpdate(command);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
        }
    }


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
     * Add payment to database. In case of TradePayment also adds the offered vacation to the entry.
     * @param payment - The given Payment
     */


    /**
     * This function will add a message to the dataBase
     * @param sender - The sender's id
     * @param receiver - The receiver's id
     * @param vacationId - The vacation id
     * @param hasBeenRead - True if the message has been read
     * @param creationDate - The creation date
     * @param creationTime - The creation time
     * @param kind - The kind of message
     * @param offeredVacationID - id of the offered vacation in case of trade request message
     */
    public void addMessage(String sender,String receiver,String vacationId,
                           boolean hasBeenRead,String creationDate,String creationTime,
                           String kind, String offeredVacationID) {

        try {
            openConnection();
            Statement statement = connection.createStatement();
            String command = "insert into messages values(" +
                    "'" + sender + "', " +
                    "'" + receiver + "', " +
                    "'" + vacationId + "', " +
                    "'" + hasBeenRead + "', " +
                    "'" + creationDate + "', " +
                    "'" + creationTime + "', " +
                    "'" + kind + "', " +
                    "'" + offeredVacationID + "', " +
                    "'false'" + ")"; // not yet confirmed
            statement.executeUpdate(command);

            // todo: maybe add this feature to avoid nulls
            // add trade data
//            if (!offeredVacationID.isEmpty()){
//                command = "insert into offeredVacations values(" +
//                        "'" + messageID + "', " +
//                        "'" + offeredVacationID + "'" + ")";
//                statement.executeUpdate(command);
//            }

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
     * @param fromCountryTXT                of vacation
     * @param fromCityTXT                   of vacation
     * @param destinetionCityTXT            of vacation
     */

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
                user.mailBox = new MailBox(this.getAllMessagesByRecieverId(user.username));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return user;
    }

    /**
     * Get all messages of a certain user (as the receiver) from database as a list of vacation objects
     * @return message list
     */
    public ArrayList<Message> getAllMessagesByRecieverId(String receiverId) {
        ArrayList<Message> messages = new ArrayList<>();
        try
        {
            openConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select rowid, * from messages where recipient='"
                    + receiverId + "'");
            while(resultSet.next()) {
                String kind = resultSet.getString("kind");
                int id = resultSet.getInt("rowid");
                String sender = resultSet.getString("sender");
                String receiver = resultSet.getString("recipient");
                String date = resultSet.getString("creationDate");
                String time = resultSet.getString("creationTime");
                String vacationId = resultSet.getString("vacationId");
                String offeredVacationID = resultSet.getString("offeredVacationID");
                boolean hasBeenRead = resultSet.getBoolean("hasBeenRead");
                Vacation vacation = getVacation(vacationId);
                Vacation offeredVacation = null;
                if (!offeredVacationID.isEmpty()) offeredVacation = getVacation(offeredVacationID);
                Message message;
                if (kind.equals("Acceptance"))
                    message = new AcceptanceMessage(sender, receiver, date, time, id, hasBeenRead, vacation, offeredVacation);
                else
                {
                    if (kind.equals("Completed")) {
                        message = new CompletionMessage(sender, receiver, date, time, id, hasBeenRead, vacation, offeredVacation);
                    }
                    else
                    {
                        message = new RequestMessage(sender, receiver, date, time, id, hasBeenRead, vacation, offeredVacation);
                    }
                }
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return messages;
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

    /**
     * Delete a message from database
     * @param id of message
     */
    public void deleteMessage(int id) {
        openConnection();
        try {
            Statement statement = connection.createStatement();
            String command = "delete from messages where " +
                    "rowid='" + id + "'";
            statement.executeUpdate(command);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    /**
     * Get the vacation id of all the vacations that are part of an acceptance or completion message
     * @return vacation IDs
     */
    public HashSet<String> getUnavailableVacationIDs() {
        HashSet<String> acceptedVacationIDs = new HashSet<>();
        try
        {
            openConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select vacationId, offeredVacationID from messages" +
                    " where kind in ('Acceptance','Completed')");
            while(resultSet.next()) {
                acceptedVacationIDs.add(resultSet.getString("vacationId"));
                String offeredVacationID = resultSet.getString("offeredVacationID");
                if (!offeredVacationID.isEmpty()) acceptedVacationIDs.add(offeredVacationID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return acceptedVacationIDs;
    }



    /**
     * Check if the message has been confirmed, to not confirm a cash payment or trade twice
     * @param messageID to check
     * @return true if confirmed, else return false
     */
    public boolean isMessageConfirmed(String messageID) {
        boolean confirmed = false;
        try {
            openConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select confirmed from messages where rowid='" + messageID + "'");
            if (rs.next()) {
                String confirmedString = rs.getString("confirmed");
                if (confirmedString.equals("true")) confirmed = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return confirmed;
    }

    /**
     * Set the message as confirmed
     * @param messageID to confirm
     */
    public void confirmMessage(String messageID) {
        try {
            openConnection();
            Statement statement = connection.createStatement();
            String command = "UPDATE messages SET confirmed='true'"
                    + " WHERE rowid='" + messageID + "';";
            statement.executeUpdate(command);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
}