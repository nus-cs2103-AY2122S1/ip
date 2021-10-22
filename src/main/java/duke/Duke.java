package duke;

import duke.commands.Command;
import duke.commands.CommandResult;
import duke.exceptions.DukeException;

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
        }
    }

    /**
     * Generates a response to the user.
     *
     * @param input full command the user typed in
     * @return
     */
    public String getResponse(String input) {
        Command c = Parser.parse(input);
        CommandResult result = executeCommand(c);
        return result.toString();
    }

    /**
     * Executes a command and returns a CommandResult.
     *
     * @param command type of command.
     * @return command result to get the String response.
     */
    private CommandResult executeCommand(Command command) {
        command.setData(tasks, storage);
        CommandResult result = command.execute();
        return result;
    }

    /**
     * Retrieves the standard welcome message from Ui.
     *
     * @return welcome message as String.
     */
    public String getWelcomeMessage() {
        return ui.showWelcomeMessage();
    }
}
