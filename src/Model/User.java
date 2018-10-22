package Model;

public class User {
    public String username;
    public String password;
    public String birthdate;
    public String firstName;
    public String lastName;
    public String city;

    public User() {
    }

    public User(String username, String password, String birthdate, String firstName, String lastName, String city) {
        this.username = username;
        this.password = password;
        this.birthdate = birthdate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
    }
}
