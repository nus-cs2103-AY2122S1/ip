import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import storage.Storage;
import ui.StartPage;

/**
 * Main class of the application.
 * Responsible for starting up the application and show Start Page.
 *
 * @author Kan Jitpakdi
 * @author GitHub: kanjitp
 * @version 0.03
 * @since 0.02
 */
public class Main extends Application {
    /**
     * Start method overridden from the Application of JavaFX.
     * Create save location if the save location does not exist and start the app.
     *
     * @param stage stage for the app to distribute its system
     */
    @Override
    public void start(Stage stage) {
        try {
            if (!Storage.haveSaveLocation()) {
                Storage.createSaveLocation();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setTitle("Alice");
        stage.setResizable(false);
        setUpStage(stage);
        stage.show();
    }

    private void setUpStage(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/StartPage.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<StartPage>getController().fetchSaveFiles();
            // Add the scene to the stage
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
