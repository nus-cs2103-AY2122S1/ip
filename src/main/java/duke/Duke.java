package duke;

import javaFx.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A chat bot that is capable of recording tasks.
 * It records 3 types of tasks:
 * Todos: Tasks without any date attached.
 * Deadlines: Tasks that need to be done before a specific date.
 * Events: Tasks that start at a specific time and ends at a specific time.
 */
public class Duke extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            primaryStage.setScene(scene);
            Ui dukeUi = new Ui();
            dukeUi.startDuke();
            fxmlLoader.<MainWindow>getController().setDuke(dukeUi);
            fxmlLoader.<MainWindow>getController().displayStartMessage();
            primaryStage.show();
            primaryStage.setTitle("Duke");
        } catch (IOException e) {
            System.out.println("Error message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
