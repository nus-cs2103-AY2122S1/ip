package duke;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for gui.Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke("duke.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ui/MainWindow.fxml"));
            assert fxmlLoader != null;
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.show();
            fxmlLoader.<MainWindow>getController().setDukeAndMain(duke, this);
            stage.setTitle("DukeAgain");
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void stop() throws Exception {
        Platform.exit();
        System.exit(0);
        super.stop();
    }
}
