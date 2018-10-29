package View;

import Model.IModel;

public interface IUserView {
    void setupController(IModel userDatabase);
    void signUp();
    void settings();
    void fillFieldsWithUserDetails();
    void mainMenu();
    void searchUser();
}
