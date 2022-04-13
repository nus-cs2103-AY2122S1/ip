package duke;

import java.io.IOException;

import duke.commands.Command;
import duke.parser.UnableToParseException;
import duke.parser.cli.CliParser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * This class is the main driver of the Duke app.
 */
public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    Duke(Stage stage) {
        try {
            this.ui = new Ui(stage, this);
            ui.print("Initializing Duke...");
            this.storage = new Storage();
            this.tasks = this.storage.load();
        } catch (IOException | UnableToParseException e) {
            e.printStackTrace();
            Platform.exit();
        }
        ui.printAllTasks(this.tasks);
        ui.printGreeting();
    }

    /**
     * Handles the user input.
     *
     * @param rawInput The raw input that the user entered into Duke.
     */
    public void handleInput(String rawInput) {
        assert rawInput != null : "input string should not be null";
        try {
            Command c = CliParser.parseCommand(rawInput.trim());
            c.execute(tasks, ui, storage);
            if (c.isExit()) {
                Platform.exit();
            }
        } catch (DukeException e) {
            ui.printErr(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Platform.exit();
        }
    }
}
