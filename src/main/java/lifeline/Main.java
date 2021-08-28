package lifeline;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The Main class starts the program
 */
public class Main extends Application {

    /**
     * Starts the GUI program
     *
     * @param stage Main stage which represents primary window of JavaFX application
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add("styles/lifeline.css");
            Image icon = new Image(this.getClass().getResourceAsStream("/images/Apex.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Lifeline");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
