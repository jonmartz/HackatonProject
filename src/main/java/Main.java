
import Model.IModel;
import Model.UserDatabase;
import View.UserView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        IModel userDatabase = new UserDatabase();//Creating the database
        UserView userView = new UserView(primaryStage);
        userView.mainMenu();//Moving to the mainMenu window.
        userView.setupView(userDatabase);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
