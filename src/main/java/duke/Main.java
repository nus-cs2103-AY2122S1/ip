package duke;

import java.io.IOException;
import java.io.InputStream;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

/**
 * A GUI implementation for Duke using FXML.
 *
 * @author Erwin Quek
 * @version CS2103 AY21/22 Sem 1
 */
public class Main extends Application {

    private Duke duke = new Duke("data/duke.txt");

    @Override
    public void start(Stage stage) {
        InputStream appIcon = this.getClass().getResourceAsStream("/images/Elon_Musk.png");
        if (appIcon != null) {
            stage.getIcons().add(new Image(appIcon));
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Duke");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}