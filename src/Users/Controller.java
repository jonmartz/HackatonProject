package Users;

public class Controller {
    private Model model;
    private View view;

    public Controller() {}

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void addUser(String username, String password, String birthdate, String firstName,
                        String lastName, String city) {
        model.addUser(username, password, birthdate, firstName, lastName, city);
    }

    public User getUser(String username) {
        return model.getUser(username);
    }
}
