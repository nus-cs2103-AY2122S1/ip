package duke.command;

import java.io.IOException;

import duke.result.Result;
import duke.result.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * A class that represents a valid command that can be executed according to the user's input.
 */
public abstract class Command {
    protected final String input;

    /**
     * Creates a command that does not rely on user inputs to function.
     */
    public Command() {
        this.input = "";
    }

    /**
     * Creates a command that relies on user inputs to function.
     *
     * @param input A string containing the user's input.
     */
    public Command(String input) {
        this.input = input;
    }

    /**
     * Executes the function associated with the command.
     *
     * @param taskList   The current list of tasks from the user.
     * @param ui      An object that handles all UI related functionality. (e.g. printing)
     * @param storage An object that handles all save/load related functionality.
     * @return A Result object containing task and alias data, as well as an output message.
     * @throws IOException If an error occurs during a save/load operation.
     */
    public abstract Result execute(TaskList taskList, Ui ui, Storage storage) throws IOException;

    /**
     * Returns true if the command terminates Duke, false otherwise.
     *
     * @return False by default, unless the command is a terminating command (e.g. ByeCommand)
     */
    public boolean isTerminated() {
        return false;
    }
}
