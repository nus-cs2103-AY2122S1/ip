package winston;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Winston using FXML.
 */
public class Main extends Application {

    private Winston winston = new Winston();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add("/css/stylesheet.css");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Winston");
            fxmlLoader.<MainWindow>getController().setWinston(winston);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}