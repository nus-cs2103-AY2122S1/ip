import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import seedu.duke.Duke;

public class Main extends Application {
    private Duke duke = new Duke();

    /**
     * Runs the main Duke ToDo Application
     * 
     * @param args
     * @throws FileNotFoundException when file is not found in the given location.
     * @throws IOException           when Scanner is not able to scan anything from
     *                               the given file path.
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // Duke duke = new Duke();
        // duke.start();
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
