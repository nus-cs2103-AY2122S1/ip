package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * Represents a Command to terminate the program.
 */
public class ExitCommand extends Command {

    private String reply;

    /**
     * The constructor for an ExitCommand object.
     */
    public ExitCommand() {

    }

    /**
     * Executes the Command to terminate the program.
     *
     * @param tasks The given TaskList.
     * @param storage The given Storage to save changes to.
     * @return The response to the user.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        reply = "Bye!";
        return reply;
    }
}
