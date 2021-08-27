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

    public ListCommand(TaskList taskList, String filterType, String filterCondition) {
        this.tasks = taskList;
        this.filterType = filterType;
        this.filterCondition = filterCondition;
    }

    @Override
    public void execute() {
        tasks.printList(this.filterType, this.filterCondition);
    }
}