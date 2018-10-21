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

    public void updateUser(String username, String filedToUpdate ,String valueToChange){///Maybe Also need to check if the password is the correct password
        try {
            openConnection();
            Statement statement = connection.createStatement();
            if(getUser(username) != null){
                String command = "UPDATE users SET " + filedToUpdate + " = '" + valueToChange + "' WHERE username = '" + username + "';";
                statement.executeUpdate(command);
            }
            else{
                //the user name isn't exists
                //System.out.println("user isn't exists");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    /**
     * This function will delete a user from the database
     * @param username - The username of the user
     * @param password - The password of the user
     */
    public void deleteUser(String username, String password)
    {
        User selectedUser=getUser(username); // Get the user

        //If the user dose not exist
        if(selectedUser==null)
        {
            // TODO: 10/21/2018 Add a text that says that this user dose not exist

        }
        else{//If the user does exist

            if(!selectedUser.password.equals(password)) //If the user gave an incorrect password
            {
                // TODO: 10/21/2018 Add a text that says that the password is incorrect
            }
            else
            {
                openConnection();
                try {
                    Statement statement = connection.createStatement();
                    String command = "delete from users where" +
                            "username='" + selectedUser.username + "'";
                    statement.executeUpdate(command);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                finally {
                    closeConnection();
                }

            }
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
