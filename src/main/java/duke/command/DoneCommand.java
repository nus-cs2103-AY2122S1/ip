package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";

    public static final String MISSING_TASK_NUMBER_MESSAGE =
            "Please specify the task number for the task you want to complete.";
    public static final String INVALID_TASK_NUMBER_MESSAGE =
            "This is not a valid task number.";

    private int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            Task completedTask = tasks.markDone(taskNumber);
            storage.save(tasks);
            return Ui.getDoneMessage(completedTask);
        } catch (IndexOutOfBoundsException e) {
            return INVALID_TASK_NUMBER_MESSAGE;
        }
    }
}
