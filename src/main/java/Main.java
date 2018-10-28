package main.java;

import main.java.Model.IModel;
import main.java.Model.UserDatabase;
import main.java.View.UserView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        IModel userDatabase = new UserDatabase();
        UserView userView = new UserView(primaryStage);
        userView.mainMenu();
        userView.setupController(userDatabase);

        // Test
        /*
        controller.addUser("Jonmartz","1234","1/1/1", "Jon","Martz","Haifa");
        System.out.println(controller.getUser("Jonmartz").city);
        */

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
