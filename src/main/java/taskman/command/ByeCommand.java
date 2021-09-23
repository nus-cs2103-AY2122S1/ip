package taskman.command;

import taskman.exception.DukeException;
import taskman.util.Storage;
import taskman.util.TaskList;


/**
 * Command that duke executes to end the the program
 */
public class ByeCommand extends Command {
    private final String exitMessage = "Bye. Hope to see you again soon!";

    /**
     * Basic Constructor
     *
     * @param storage StorageTxt object to save
     * @param taskList Tasklist to add task to
     */
    public ByeCommand(Storage storage, TaskList taskList) {
        super(storage, taskList, true);
    }

    /**
     * Executes a set of instructions to close the application
     *
     * @return String success message
     * @throws DukeException
     */
    @Override
    public String exec() throws DukeException {
        return exitMessage;
    }
}
