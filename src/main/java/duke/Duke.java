package duke;

import java.io.IOException;

import duke.command.Command;
import duke.javafx.MainWindow;
import duke.task.TaskList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The type Duke that is the main of the program.
 */
public class Duke extends Application {

    /** storage to handle save file (loading and saving) */
    private final Storage storage;
    /** storage to store tasks */
    private final TaskList tasks;

    /**
     * Instantiates a new Duke.
     */
    public Duke() {
        this.tasks = new TaskList();
        // Initialise Storage with the tasks storage and the filepath to the save file
        this.storage = new Storage(this.tasks, "./duke.txt");
        // Loading save file from the filepath
        try {
            storage.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            Duke duke = new Duke();
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets response from the user.
     *
     * @param input String input from the user.
     * @return Duke's reply message to the user input.
     */
    public String getResponse(String input) {
        String output = "";
        try {
            Command userCommand = new Parser(input, tasks).checkOperation();
            output = userCommand.execute();
            storage.save();
        } catch (DukeException | IllegalArgumentException e) {
            return e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
}
