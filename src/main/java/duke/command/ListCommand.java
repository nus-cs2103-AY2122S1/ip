package duke.command;

import duke.util.TaskList;

/**
 * Class that represents the Command to displays tasks from the TaskList.
 *
 * @author Benedict Chua
 */
public class ListCommand extends Command {
    private TaskList taskList;
    private String type;
    private String filterCondition;

    public ListCommand(TaskList taskList, String type, String filterCondition) {
        this.taskList = taskList;
        this.type = type;
        this.filterCondition = filterCondition;
    }

    @Override
    public void execute() {
        taskList.printList(this.type, this.filterCondition);
    }
}