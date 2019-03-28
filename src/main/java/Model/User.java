package Model;

import java.util.List;

/**
 * Place holder for a specific user's details.
 */
public class User {
    public String username;
    public String password;
    public String birthdate;
    public String firstName;
    public String lastName;
    public String city;
    public String phoneNumber;
    public String description;
    public String pictureFilePath;
    public MailBox mailBox;

    public User(String username, String password, String birthdate, String firstName, String lastName, String city, String phoneNumber, String description, String pictureFilePath, MailBox mailBox) {
        this.username = username;
        this.password = password;
        this.birthdate = birthdate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.pictureFilePath = pictureFilePath;
        this.mailBox = mailBox;
    }
    public User(){}
}
