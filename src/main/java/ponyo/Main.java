package ponyo;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ponyo.gui.MainWindow;

/**
 * A GUI for Ponyo using FXML.
 */
public class Main extends Application {

    private Ponyo ponyo = new Ponyo();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Ponyo's Todo List!");
            fxmlLoader.<MainWindow>getController().setPonyo(ponyo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
