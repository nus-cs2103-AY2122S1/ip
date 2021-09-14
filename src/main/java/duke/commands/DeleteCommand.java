package duke.commands;

import duke.exceptions.OutOfTaskListRangeException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.utils.Storage;

public class DeleteCommand extends Command {

    private static final String DELETE_MSG = "The following have been deleted:";

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws OutOfTaskListRangeException {
        try {
            Task task = tasks.delete(this.index);

            String message = DELETE_MSG + "\n" + task.toString() + "\n" + tasks.getTaskCountString();

            return message;
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfTaskListRangeException(
                    "Index out of task list range!\n" +
                    "Task list only has " + tasks.getSize() + " tasks!");
        }


    }
}
