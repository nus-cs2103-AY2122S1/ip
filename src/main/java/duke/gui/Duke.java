package duke.gui;

import duke.TaskList;
import duke.command.Command;
import duke.command.Response;
import duke.command.Storage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */
public class Duke extends Application {
    public static Command command;


    @Override
    public void start(Stage stage) {
        try {
            initialize();
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initialize() {
        TaskList taskList = new TaskList();
        Response response = new Response(taskList);
        Storage storage = new Storage("duke.txt", taskList);
        command = new Command(taskList, response, storage);
        command.loadSavedTasks();
    }
}
