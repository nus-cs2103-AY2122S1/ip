package duke.ui.graphical;

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
    @Override
    public void start(Stage stage) {
        try {
            Duke duke = new Duke("duke.txt");

            FXMLLoader fxmlLoader = new FXMLLoader(GraphicalApplication.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add("css/styles.css");
            stage.setScene(scene);
            stage.setTitle("Duke");
            stage.setResizable(false);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
