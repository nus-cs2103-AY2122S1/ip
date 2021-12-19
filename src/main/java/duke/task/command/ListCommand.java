package duke.task.command;

import duke.DukeException;
import duke.TaskList;

/**
 * The command to list all tasks.
 */
public class ListCommand extends Command {

    private TaskList taskList;

    /**
     * Instantiates a new object for the class.
     *
     * @param taskList The list of tasks.
     */
    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns an output message after executing the list command.
     *
     * @return The output message of the command.
     * @throws DukeException Thrown when a duke exception happens.
     */
    @Override
    public String execute() throws DukeException {
        return taskList.printList();
    }
}
