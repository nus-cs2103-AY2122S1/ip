package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 * https://se-education.org/guides/tutorials/javaFx.html
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/mafu.png")));
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(new Duke("data/tasks.txt"));
            stage.show();
            fxmlLoader.<MainWindow>getController().displayGreeting();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
