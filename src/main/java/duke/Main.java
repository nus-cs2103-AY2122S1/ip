package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Bridge between Launcher i.e. entry point and MainWindow i.e. the controller class.
 */
public class Main extends Application {
    private Duke duke = Duke.init();

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            primaryStage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setUpDuke(duke);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
