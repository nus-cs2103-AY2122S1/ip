package duke.command;

import duke.data.TaskList;
import duke.storage.Storage;

/**
 * This class abstracts the exit command that the user wants to execute.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    /**
     * Execute the command to exit Duke.
     *
     * @param tasks   The TaskList of the Duke instance.
     * @param storage The storage handler of the Duke instance.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        System.exit(0);
        return "Bye! Hope to see you again soon!";
    }
}
