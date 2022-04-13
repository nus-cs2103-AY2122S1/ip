package duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * A GUI for Duke using FXML.
 */
//Solution below adapted from https://se-education.org/guides/tutorials/javaFxPart4.html
public class Main extends Application {

    private final Duke duke = new Duke("./data/tasks.json");
    private final Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/revan.png")));
    private final Image dukeImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/nihilus.png")));


    @Override
    public void start(Stage stage) {
        assert stage != null : "No stage. Try again.";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.getIcons().add(dukeImage);
            stage.setTitle("Necro");
            stage.show();
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}