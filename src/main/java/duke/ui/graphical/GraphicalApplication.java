package duke.ui.graphical;

import java.io.IOException;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Initializes the main graphical application for Duke using FXML.
 */
public class GraphicalApplication extends Application {
    private Duke duke;

    @Override
    public void start(Stage stage) {
        try {
            duke = new Duke("duke.txt");

            FXMLLoader fxmlLoader = new FXMLLoader(GraphicalApplication.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
