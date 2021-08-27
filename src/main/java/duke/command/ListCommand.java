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
        this.tasks = tasks;
        this.filterType = filterType;
        this.filterCondition = filterCondition;
    }

    @Override
    public void execute() {
        tasks.printList(this.filterType, this.filterCondition);
    }
}
