package duke.command;

import duke.util.TaskList;

/**
 * Class that represents the Command to displays tasks from the TaskList.
 *
 * @author Benedict Chua
 */
public class ListCommand extends Command {
    private TaskList tasks;
    private String filterType;
    private String filterCondition;

    /**
     * Constructor for ListCommand.
     *
     * @param tasks TaskList containing current tasks.
     * @param filterType Type of filtering to do when listing tasks.
     * @param filterCondition Condition to filter tasks before listing.
     */
    public ListCommand(TaskList tasks, String filterType, String filterCondition) {
        assert filterType.matches("all|date|keyword") : "Invalid filterType";

        this.tasks = tasks;
        this.filterType = filterType;
        this.filterCondition = filterCondition;
    }

    /**
     * {@inheritDoc}
     *
     * This executes the list tasks command.
     */
    @Override
    public String execute() {
        return tasks.listTasks(this.filterType, this.filterCondition);
    }
}
