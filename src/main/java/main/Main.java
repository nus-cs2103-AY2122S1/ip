package main;

import java.io.IOException;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ui.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    /**
     * Starts the GUI application.
     *
     * @param stage javaFX parameter to run GUI application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Fake (Telegram) App 2021");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/telegramIcon.png")));
            stage.setScene(scene);

            String css = getClass().getResource("/view/styles.scss").toExternalForm();
            scene.getStylesheets().add(css);

            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
