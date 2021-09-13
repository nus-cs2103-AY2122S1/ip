package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MISSING_TASK_NUMBER_MESSAGE =
            "Please specify the task number for the task you want to delete.";
    public static final String INVALID_TASK_NUMBER_MESSAGE =
            "This is not a valid task number.";

    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            Task removedTask = tasks.deleteTask(taskNumber);
            storage.save(tasks);
            return Ui.getDeleteMessage(removedTask, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            return INVALID_TASK_NUMBER_MESSAGE;
        }
    }
}
