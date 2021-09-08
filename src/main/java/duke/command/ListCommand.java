package duke.command;

import duke.data.TaskList;

/**
 * Class that encapsulates the "List" Command.
 *
 * @author Wang Hong Yong
 */
public class ListCommand extends Command {

    /**
     * Constructor for ListCommand.
     *
     * @param tasklist Task Handler that handles the operation.
     */
    public ListCommand(TaskList tasklist) {
        super(tasklist);
    }

    /**
     * Executes the "List" Command.
     * @return string that represents details of listing all the tasks.
     */
    @Override
    public String execute() {
        return super.taskList.listTasks();
    }
}
