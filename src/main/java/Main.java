import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import storage.Storage;
import ui.ChatPage;
import ui.StartPage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    /**
     * Start method overridden from the Application of JavaFX
     *
     * @param stage stage for the app to distribute its system
     */
    @Override
    public void start(Stage stage) {



        stage.setTitle("Alice");
        stage.setResizable(false);

        try {
            if (!Storage.haveSaveLocation()) {
                Storage.createSaveLocation();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/StartPage.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<StartPage>getController().fetchSaveFiles();
            // Add the scene to the stage
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}