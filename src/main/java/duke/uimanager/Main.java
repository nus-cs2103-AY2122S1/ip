package duke.uimanager;

import java.io.IOException;

import duke.main.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * @@author Hang Zelin
 *
 * A GUI for Duke using FXML.
 * note: The icon image is free to use. link: https://www.flaticon.com/free-icon/chat_724715
 */
public class Main extends Application {

    private final Duke duke = new Duke();

    /**
     * Start the GUI process with a specific stage.
     *
     * @param stage Stage info to start a GUI.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.getIcons().add(new Image("/images/icon.png"));
            stage.setTitle("Duke. Your best task manager!");
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
