package Model;

import Users.User;

import java.sql.*;

public class UserDatabase implements IModel{
    private Connection connection;

    public UserDatabase() {
        try {
            openConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("create table if not exists users (username string, password string," +
                    "birthdate string, firstName string, lastName string, city string)");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            closeConnection();
        }
    }

    private void openConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        try
        {
            if(connection != null)
                connection.close();
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }

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
            }
            else {
                // Add user already exists stuff
                //System.out.println("user already exists");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public User getUser(String username) {
        try {
            openConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from users where username='"+username+"'");
            if (rs.next()) {
                User user = new User();
                user.username = rs.getString("username");
                user.password = rs.getString("password");
                user.birthDate = rs.getString("birthDate");
                user.firstName = rs.getString("firstName");
                user.lastName = rs.getString("lastName");
                user.city = rs.getString("city");
                return user;
            }
            else {
                // Add user not found stuff
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }
}
