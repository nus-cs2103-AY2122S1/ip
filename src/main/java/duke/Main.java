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
 * A GUI for Duke using FXML.
 * https://se-education.org/guides/tutorials/javaFx.html
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        InputStream iconStream = this.getClass().getResourceAsStream("/images/mafu.png");
        if (iconStream != null) {
            stage.getIcons().add(new Image(iconStream));
        }
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));

        AnchorPane ap = null;
        try {
            ap = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert ap != null;
        stage.setScene(new Scene(ap));

        MainWindow mw = fxmlLoader.<MainWindow>getController();
        try {
            mw.setDuke(new Duke("data/tasks.txt"));
        } catch (DukeException e) {
            mw.printMessage(e.getMessage());
        }

        stage.show();
        mw.displayGreeting();
    }
}
