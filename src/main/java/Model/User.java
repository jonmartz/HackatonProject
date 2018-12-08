package Model;

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
    public String pictureFilePath;

    /**
     * Constructor
     * @param username of user
     * @param password of user
     * @param birthdate of user
     * @param firstName of user
     * @param lastName of user
     * @param city of user
     */
    public User(String username, String password, String birthdate, String firstName,
                String lastName, String city, String pictureFilePath) {
        this.username = username;
        this.password = password;
        this.birthdate = birthdate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.pictureFilePath = pictureFilePath;
    }

    /**
     * Default constructor
     */
    public User() { }
}
