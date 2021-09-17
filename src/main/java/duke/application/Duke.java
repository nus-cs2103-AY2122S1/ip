package duke.application;

import java.io.IOException;

import duke.io.Parser;
import duke.task.TaskList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Duke extends Application {
    private final TaskList taskList = new TaskList();
    private final Parser parser = new Parser(taskList);

    /**
     * Loads the MainWindow from fxml, and initialises it, then sets it as the scene to be shown.
     *
     * @param stage Provided primary stage.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setParser(parser);
            stage.setTitle("Duke");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
