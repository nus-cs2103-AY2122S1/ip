package skeltalgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.show();
            fxmlLoader.<MainWindow>getController().loadTasks();
            fxmlLoader.<MainWindow>getController().introduction();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
    Expenses
    -add expense class
    -add expense function e.g (money task 123)
    -Total expenses in TaskList
    -sum function
    -delete expense
    -sum expense
     */
}
