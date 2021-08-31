package kayu;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Kayu using FXML.
 */
public class Launcher extends Application {

    // Resource paths (starting from /src/main/java/resources).
    private static final String ICON_PATH = "/images/icon.png";
    private static final String FXML_PATH = "/view/MainWindow.fxml";
    
    /**
     * Starts the Kayu UI window.
     * {@inheritDoc}
     *
     * @param stage {@inheritDoc}
     */
    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Kayu");
            stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(ICON_PATH))));
            
            FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource(FXML_PATH));
            AnchorPane ap = fxmlLoader.load();
            
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
