
import Model.Database;
import View.ViewChanger;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Database database = new Database();//Creating the database
        ViewChanger viewChanger = new ViewChanger(primaryStage);
        viewChanger.mainMenu();//Moving to the mainMenu window.
        viewChanger.setupView(database);
        primaryStage.show();

        // test
        database.addVacation("Canada", "2", "1",
                "1","1","1","1","2/2/2",
                "1/1/1","1","1","1",
                "1");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
