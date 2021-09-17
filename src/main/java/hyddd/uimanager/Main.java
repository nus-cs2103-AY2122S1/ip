package hyddd.uimanager;

import java.io.IOException;

import hyddd.main.Hyddd;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @@author Hang Zelin
 *
 * A GUI for hyddd using FXML.
 * note: The icon image is free to use. Retrieved from Zhihu.
 */
public class Main extends Application {
    private final Hyddd hyddd = new Hyddd();

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
            stage.getIcons().add(new Image("/images/icon.jpg"));
            stage.setTitle("Hyddd: Your best task manager!");
            fxmlLoader.<MainWindow>getController().setHyddd(hyddd);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
