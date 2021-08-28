package duke.command;

import duke.data.TaskHandler;
import duke.data.exception.DukeException;

import duke.storage.Storage;
import duke.ui.Ui;


/**
 * Class that encapsulates the "Find" Command.
 *
 * @author Won Ye Ji
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "FIND";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + "Finds tasks with matching keyword.";

    /**
     * Constructor for FindCommand.
     *
     * @param th Task Handler that handles the operation.
     * @param str Storage that holds the task information.
     */
    public FindCommand(TaskHandler th, Storage str) {
        super(th, str);
    }

    /**
     * Executes the "Find" Command.
     *
     * @param cmd Command string to be executed.
     */
    @Override
    public void execute(String cmd) {
        try {
            if (cmd.length() < 6) {
                throw new DukeException(Ui.emptyDescription("find"));
            } else {
                String keyword = cmd.substring(6);
                taskHandler.findTasks(keyword);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}

