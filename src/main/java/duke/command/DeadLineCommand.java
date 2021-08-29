package duke.command;

import duke.storage.Storage;
import duke.task.DeadLine;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Contains the executables when the user uses the 'deadline' command.
 */
public class DeadLineCommand extends Command {
    private String task;

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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (task.length() == 0) {
            ui.showError("OOPS!!! The description of a deadline cannot be empty.\n");
            return;
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
        // Add 3 to index to avoid "by "
        index += 3;
        while (index < data.length) {
            by += data[index];
            index++;
        }
        DeadLine deadLine = new DeadLine(taskWithDeadLine, by);
        taskList.add(deadLine);
        ui.addMessage();
        ui.showTask(deadLine);
        ui.showListLength(taskList);
        storage.save(taskList);
    }
}
