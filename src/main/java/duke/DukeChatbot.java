package duke;

import java.io.IOException;

import duke.command.Command;
import duke.command.CommandParser;
import duke.command.DukeInvalidCommandException;
import duke.storage.StorageHandler;
import duke.task.TaskHandler;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Represents the Duke chatbot.
 *
 * @author Jay Aljelo Saez Ting
 */
public class DukeChatbot extends Application {

    private Ui ui;
    private CommandParser commandParser;
    private StorageHandler storageHandler;
    private TaskHandler taskHandler;
    private boolean hasErrorOnSave;

    /**
     * Reads the input, and if it is a valid command, executes it.
     *
     * @param input The input text.
     */
    public void readInput(String input) {
        Command command = commandParser.getCommandInstance(input);
        try {
            command.execute(taskHandler, ui);
            if (hasErrorOnSave) {
                ui.printUnexpectedErrorMessage();
                Platform.exit();
            }
            if (command.mustExit()) {
                Platform.exit();
            }
        } catch (DukeInvalidCommandException e) {
            ui.printInvalidCommandErrorMessage(e.getMessage());
        }
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DukeChatbot.class.getResource("/view/Ui.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            ui = fxmlLoader.getController();
            ui.setDukeChatbot(this);
            commandParser = new CommandParser();
            storageHandler = StorageHandler.getInstance();
            taskHandler = new TaskHandler(storageHandler.loadTasks());
            taskHandler.addTasksListUpdateObserver(tasks -> {
                try {
                    storageHandler.saveTasks(tasks);
                } catch (IOException e) {
                    hasErrorOnSave = true;
                }
            });
            hasErrorOnSave = false;
            stage.show();
            ui.printGreeting();
        } catch (IOException e) {
            e.printStackTrace();
            if (ui != null) {
                ui.printUnexpectedErrorMessage();
            }
            Platform.exit();
        }
    }
}
