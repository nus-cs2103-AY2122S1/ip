package duke.command;

import duke.storage.Storage;
import duke.task.DeadLine;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Contains the executables when the user uses the 'deadline' command.
 *
 * @author Benjamin Lui
 */
public class DeadLineCommand extends Command {
    private String task;
    // Add 3 to index to avoid "by "
    private final int indexToAvoidBy = 3;

    /**
     * Constructor for the DeadLine Command.
     * @param task the task that is to be a DeadLine
     */
    public DeadLineCommand(String task) {
        this.task = task;
    }

    /**
     * Executes the deadline command. If there is no task specified, nothing is
     * added to the list.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (task.length() == 0) {
            return ui.showError("OOPS!!! The description of a deadline cannot be empty.\n");

        }
        char[] data = task.toCharArray();
        String taskWithDeadLine = "";
        String by = "";
        int index = 0;
        while (index < data.length) {
            if (data[index] == '/') {
                break;
            } else {
                taskWithDeadLine += data[index];
            }
            index++;
        }
        index += indexToAvoidBy;
        while (index < data.length) {
            by += data[index];
            index++;
        }
        DeadLine deadLine = new DeadLine(taskWithDeadLine, by);
        taskList.add(deadLine);
        storage.save(taskList);
        return ui.addMessage() + ui.showTask(deadLine) + ui.showListLength(taskList);
    }
}
