package kayu;

import java.io.IOException;
import java.io.InputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Represents and renders the GUI for Kayu using FXML.
 */
public class Launcher extends Application {

    // Resource paths (starting from /src/main/java/resources).
    private static final String ICON_PATH = "/images/icon.png";
    private static final String FXML_PATH = "/view/MainWindow.fxml";
    
    private static final String ASSERT_FAIL_ABSENT_IMAGE = "Image path not present/valid.";
    
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
            setIconToStage(stage);
            AnchorPane anchorPane = loadMainWindow();
            showStage(stage, anchorPane);
            
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    
    private void setIconToStage(Stage stage) {
        InputStream iconImageStream = this.getClass().getResourceAsStream(ICON_PATH);
        assert (iconImageStream != null) : ASSERT_FAIL_ABSENT_IMAGE;
        
        Image icon = new Image(iconImageStream);
        stage.getIcons().add(icon);
    }
    
    private AnchorPane loadMainWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource(FXML_PATH));
        return fxmlLoader.load();
    }
    
    private void showStage(Stage stage, AnchorPane anchorPane) {
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }
}
