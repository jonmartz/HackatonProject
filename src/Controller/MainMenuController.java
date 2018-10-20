package Controller;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController extends UserController {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void signUp() {
        userView.signUp();
        userView.setupController(userDatabase);
    }
}
