package ailurus;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Ailurus using FXML.
 */
public class Main extends Application {

    private Ailurus ailurus = new Ailurus("./data/", "tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Ailurus");
            stage.getIcons().add(new Image("/images/Ailurus.png"));
            stage.setResizable(false);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setAilurus(ailurus);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        Ailurus.unloadStorage();
    }
}
