package duke;

import java.io.IOException;

import duke.logic.Duke;
import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Bridge between Launcher i.e. entry point and MainWindow i.e. the controller class.
 */
public class Main extends Application {
    private static final String WINDOW_TITLE = "DukeBot";
    private final Duke duke = Duke.init();

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(Main.class.getResource("/view/styles.css").toExternalForm());
            primaryStage.setScene(scene);
            setWindowTitle(primaryStage);
            fxmlLoader.<MainWindow>getController().setUpDuke(duke);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setWindowTitle(Stage primaryStage) {
        primaryStage.setTitle(WINDOW_TITLE);
    }
}
