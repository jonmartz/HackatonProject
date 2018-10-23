package View;

import Model.IModel;

public interface IUserView {
    public void setupController(IModel userDatabase);
    public void signUp();
    public void settings();
    public void fillFieldsWithUserDetails();
    public void mainMenu();
    public void searchUser();
}
