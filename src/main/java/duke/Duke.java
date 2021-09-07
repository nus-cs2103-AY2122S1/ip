package duke;

import duke.command.Command;
import duke.command.CommandKeyword;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.task.TaskList;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.HashMap;


/**
 * The main program of the chatbot Duke. Consists of Tasklist, Ui and Storage instances.
 */
public class Duke {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Constructs a Duke chatbot.
     */
    public Duke(VBox dialogContainer) {
        this.ui = new Ui(dialogContainer);
        ui.greetUser();
        this.storage = new Storage();
        try {
            HashMap<String, CommandKeyword> listOfCommands = this.storage.loadCommands();
            this.ui.setListOfCommands(listOfCommands);
            this.tasks = new TaskList(this.storage.loadTasks(), this.ui);
        } catch (IOException e) {
            this.ui.showLoadingError();
        }
    }

    public boolean getResponse(String input) {
        boolean shouldExit = false;
        ui.showInput(input);
        try {
            Command command = this.ui.readCommand(input);
            shouldExit = Parser.parse(command, this.tasks, this.storage);
            if (shouldExit) {
                ui.showFarewell();
            }
        } catch (DukeException e) {
            this.ui.showError(e);
        }
        return shouldExit;
    }
}
