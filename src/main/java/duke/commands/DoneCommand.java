package duke.commands;

import duke.exceptions.OutOfTaskListRangeException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.utils.Storage;

public class DoneCommand extends Command {

    private static final String DONE_MSG = "Well done.";

    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws OutOfTaskListRangeException {
        try {
            Task task = tasks.get(this.index);

            task.markAsDone();

            String message = DONE_MSG + "\n" + task.toString() + "\n" + tasks.getTaskCountString();

            return message;
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfTaskListRangeException(
                    "Index out of task list range!\n" +
                    "Task list only has " + tasks.getSize() + " tasks!");
        }

    }
}
