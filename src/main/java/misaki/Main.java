package misaki;

import java.io.IOException;

import misaki.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Misaki using FXML.
 */
public class Main extends Application {

    private Misaki misaki = new Misaki("data/misaki.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setMisaki(misaki);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
