package duke;

import duke.command.Command;
import duke.command.MalformedCommandException;
import duke.command.UnsupportedCommandException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.storage.StorageException;
import duke.task.TaskList;
import duke.ui.Ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke() {
        this.ui = new Ui();
        this.tasks = new TaskList();
    }

    /**
     * Attempts to load tasks stored in disk.
     *
     * @return Welcome message if tasks were successfully loaded
     * otherwise returns an error message.
     */
    public String initialise() {
        try {
            Storage.loadTasks(tasks);
        } catch (StorageException e) {
            return ui.showErrorMessage(e);
        }
        return ui.showWelcomeMessage();
    }

    /**
     * Stores tasks onto a file on disk and returns error message if storage of tasks fails.
     */
    public void saveTasks() {
        try {
            Storage.saveTasksToFile(tasks, Storage.FILE_NAME);
        } catch (StorageException e) {
            ui.showErrorMessage(e);
        }
    }

    /**
     * Executes command based on user input.
     *
     * @param userInput String input provided by user.
     * @return String response from executing user command.
     */
    public String getResponse(String userInput) {
        try {
            Command userCommand = Parser.parse(userInput);
            return userCommand.execute(tasks, ui);
        } catch (UnsupportedCommandException | MalformedCommandException e) {
            return ui.showErrorMessage(e);
        }
    }
}
