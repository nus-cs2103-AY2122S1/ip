package nyx.command;

import nyx.Storage;
import nyx.task.TaskList;

/**
 * Represents a command to list all tasks currently stored.
 */
public class ListCommand extends Command {
    /**
     * Constructs a ListCommand object.
     */
    public ListCommand() {
        super("");
    }

    /**
     * Perform operations needed to list all tasks.
     *
     * @param taskList TaskList object containing all the tasks.
     * @param storage Storage object to deal with hard disk related operations.
     * @return String representation of the message for the user.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return taskList.toString();
    }
}
