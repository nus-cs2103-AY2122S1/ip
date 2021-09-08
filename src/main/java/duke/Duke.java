package duke;

import duke.commands.Command;
import duke.commands.CommandResult;
import duke.exceptions.DukeException;

import java.io.IOException;

/**
 * Runs the program Duke.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke object to start the program.
     *
     * Calls storage to load the TaskList data from the hard disk.
     *
     * @param filePath relative file path of output file.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            this.tasks = storage.load();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            this.tasks = new TaskList();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Empty constructor for JavaFX launcher
     */
    public Duke() {
    }

    /**
     * You should have your own function to generate a response to user.
     * Replace this stub with your complete method.
     */
    public String getResponse(String input) {
        Command c = Parser.parse(input);
        CommandResult result = executeCommand(c);
        return result.toString();
    }

    private CommandResult executeCommand(Command command) {
        command.setData(tasks, storage);
        CommandResult result = command.execute();
        return result;
    }


}
