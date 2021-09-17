package poseidon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import poseidon.ui.MainWindow;

/**
 * Represents a GUI for {@code Poseidon} using JavaFX and FXML.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class Main extends Application {

    // Icon for application window.
    private static final Image BOT_ICON = new Image(Main.class.getResourceAsStream("/images/BotIcon.png"));
    private Poseidon poseidon = new Poseidon();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.getIcons().add(BOT_ICON);
            fxmlLoader.<MainWindow>getController().setPoseidon(poseidon);
            stage.setTitle("Poseidon");
            stage.show();
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
