package Users;

public class User {
    public String username;
    public String password;
    public String birthDate;
    public String firstName;
    public String lastName;
    public String city;

    public User() {
    }

    public User(String username, String password, String birthDate, String firstName, String lastName, String city) {
        this.username = username;
        this.password = password;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
    }
}
