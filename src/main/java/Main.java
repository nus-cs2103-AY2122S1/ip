import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import seedu.duke.Duke;

public class Main extends Application {
    private Duke duke = new Duke();

    /**
     * Starts and initialise the application in a {@code Stage}.
     * 
     * @param stage a platform to display all the components of the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add("/styles/style.css");

            Image icon = new Image("/icons/duke.jpg");

            fxmlLoader.<MainWindow>getController().setDuke(duke);

            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.setTitle("DukeSHEESH");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
