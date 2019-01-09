
import Model.Database;
import View.ViewChanger;
import javafx.application.Application;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Database database = new Database();//Creating the database
        ViewChanger viewChanger = new ViewChanger(primaryStage);
        viewChanger.signIn();
        viewChanger.setupView(database);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
