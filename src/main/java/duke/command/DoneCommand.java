package duke.command;

import duke.data.TaskHandler;
import duke.storage.Storage;
import duke.data.exception.DukeException;

/**
 * Class that encapsulates the "Done" Command.
 *
 * @author Won Ye Ji
 */
public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "DONE";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": marks selected task as done in the list";

    /**
     * Constructor for DoneCommand.
     *
     * @param th Task Handler that handles the operation.
     * @param str Storage that holds the task information.
     */
    public DoneCommand(TaskHandler th, Storage str) {
        super(th, str);
    }

    /**
     * Execute the "Done" Command.
     *
     * @param cmd Command string to be executed.
     */
    @Override
    public void execute(String cmd) {
        try {
            taskHandler.markTaskAsDone(Integer.parseInt(cmd.substring(5)));
            storage.updateFile(taskHandler.formatTaskToSave());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
