package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Represents an ExitCommand that will exit the program when executed.
 *
 * @author ruiquan
 */
public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand.
     */
    public ExitCommand() {
        super(true);
    }

    /**
     * Executes the ExitCommand and exit the program.
     *
     * @param tasks The collection of tasks.
     * @param storage The storage manager that deals with loading from and
     *               saving into a file.
     * @return The message representing the response.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        String message = "Goodbye, hope to see you again soon!";
        return message;
    }
}
