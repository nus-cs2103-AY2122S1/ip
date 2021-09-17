package myjournal;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 *
 * @author Felissa Faustine.
 */
public class Main extends Application {

    private MyJournal myJournal = new MyJournal();

    /**
     * Starts the stage.
     *
     * @param stage The stage.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setMyJournal(myJournal);
            stage.show();
            stage.setTitle("MyJournal");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
