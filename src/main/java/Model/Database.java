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
    private Connection connection; // to database
    private User currentUser; // user that is currently signed in
    private Vacation currentVacation; // vacation that is currently being viewed
    private Message currentMessage; // maessage that is currently being viewed

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

            //Create messages table
            statement.executeUpdate("create table if not exists messages (" +
                    "sender string, " +
                    "recipient string, " +
                    "vacationId string, " +
                    "hasBeenRead boolean, " +
                    "creationDate string, " +
                    "creationTime string, " +
                    "kind string) ");

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

            // Trade transaction table
            statement.executeUpdate("create table if not exists tradeTransactions (" +
                    "transactionId string, " +
                    "vacationID string)");

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
            if (connection != null) {
                connection.close();
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
                        "WHERE rowid = '"+currentMessage.getId()+"';";
                statement.executeUpdate(command);
            } catch (SQLException e) {
//                e.printStackTrace();
            } finally {
                closeConnection();
            }
        }
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
     * @param picture               of user
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
     * @param transaction - The given Transaction
     */
    public void addTransaction(Transaction transaction) {

        try {
            openConnection();
            Statement statement = connection.createStatement();
            String vacationID = transaction.vacationID;
            String buyerID = transaction.buyerID;
            String command = "insert into transactions values(" +
                    "'" + vacationID + "', " +
                    "'" + buyerID + "'" + ")";
            statement.executeUpdate(command);

            if(transaction instanceof TradeTransaction)
            {
                //Transaction transactionTemp = this.getTransactionByVacationID(vacationID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

    }



    private void addTradeTransaction(String transactionID,TradeTransaction tradeTransaction)
    {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String command = "insert into tradeTransactions values(" +
                    "'" + transactionID + "', " +
                    "'" + tradeTransaction.payWithVacationId + "'" + ")";
            statement.executeUpdate(command);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    /**
     * This function will add a message to the dataBase
     * @param sender - The sender's id
     * @param receiver - The receiver's id
     * @param vacationId - The vacation id
     * @param hasBeenRead - True if the message has been read
     * @param creationDate - The creation date
     * @param creationTime - The creation time
     * @param kind - The kind of message
     */
    public void addMessage(String sender,String receiver,String vacationId,boolean hasBeenRead,String creationDate,String creationTime,String kind) {

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
                    "'" + kind + "'" + ")";
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
     * This function will get the Transaction by the vacationId
     * @param vacationID - The vacation id
     * @return - A new instance of the transaction
     */
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
                boolean hasBeenRead = resultSet.getBoolean("hasBeenRead");
                Vacation vacation = getVacation(vacationId);
                Message message;
                if (kind.equals("Acceptance"))
                    message = new AcceptanceMessage(sender, receiver, date, time, id, hasBeenRead, vacation);
                else
                {
                    if(kind.equals("Completed")) {
                        Transaction transaction = getTransactionByVacationID(vacationId);
                        message = new CompletionMessage(sender, receiver, date, time, id, hasBeenRead,transaction,vacation );
                    }
                    else
                    {
                        message = new RequestMessage(sender, receiver, date, time, id, hasBeenRead, vacation);
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
     * Get the vacation id of all the vacations that have a been accepted in a buy request
     * @return vacation IDs
     */
    public HashSet<String> getAcceptedVacationIDs() {
        HashSet<String> acceptedVacationIDs = new HashSet<>();
        try
        {
            openConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select vacationId from messages where kind='Acceptance'");
            while(resultSet.next()) {
                acceptedVacationIDs.add(resultSet.getString("vacationId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return acceptedVacationIDs;
    }

    /**
     * Get the vacation id of all the vacations that have been payed for in a transaction
     * @return vacation IDs
     */
    public HashSet<String> getVacationIDsFromAllTransactions() {
        HashSet<String> vacationIDs = new HashSet<>();
        try
        {
            openConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select vacationID from transactions");
            while(resultSet.next()) {
                vacationIDs.add(resultSet.getString("vacationID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return vacationIDs;
    }
}