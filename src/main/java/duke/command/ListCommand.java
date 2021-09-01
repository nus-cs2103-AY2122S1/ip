package duke.command;

import duke.TaskList;

/**
 * This class deals with the show all tasks command.
 */
public class ListCommand extends Command {
    private TaskList myTasks;

    /**
     * Lists all tasks
     *
     * @param myTasks all tasks currently available
     */
    public ListCommand(TaskList myTasks) {
        super();
        this.myTasks = myTasks;
    }

    /**
     * Executes instructions according to the Command type (here is getting all tasks)
     */
    @Override
    public void execute() {
        myTasks.printTaskList();
    }
}
