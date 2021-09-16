package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Creates an AddDeadlineCommand to add deadlines to the task list.
 */
public class InvalidCommand extends Command {
    private String command;

    public InvalidCommand(String command) {
        this.command = command;
    }

    /**
     * Executes the Command accordingly.
     *
     * @param storage Storage to store changes in text file.
     * @param tasks Tasks compiled in a TaskList.
     * @return A String array containing output.
     */
    public String[] execute(Storage storage, TaskList tasks) {
        throw new DukeException("invalidCommand");
    }
}
