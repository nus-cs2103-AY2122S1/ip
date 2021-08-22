package duke.command;

import duke.util.TaskList;

/**
 * Class that represents the Command to displays tasks from the TaskList.
 *
 * @author Benedict Chua
 */
public class ListCommand extends Command {
    private TaskList taskList;
    private String dateString;

    public ListCommand(TaskList taskList, String dateString) {
        this.taskList = taskList;
        this.dateString = dateString;
    }

    @Override
    public void execute() {
        taskList.printList(dateString);
    }
}