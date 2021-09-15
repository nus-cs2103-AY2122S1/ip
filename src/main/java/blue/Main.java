package blue;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Blue using FXML.
 * This code is reused from SE-EDU JavaFX tutorial,
 * available at: https://se-education.org/guides/tutorials/javaFx.html
 */
public class Main extends Application {
    private static final String DEFAULT_FILEPATH = "data/tasks.txt";
    private static final String MAIN_WINDOW_NAME = "/view/MainWindow.fxml";
    private final Blue blue = new Blue(DEFAULT_FILEPATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(MAIN_WINDOW_NAME));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBlue(blue);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
