package duke.command;

import duke.FileController;
import duke.exceptions.UnsavedChangesException;
import duke.tasks.Task;
import duke.tasks.TaskList;

/** Command to add tasks */
public class AddTaskCommand extends Command {
    /** task to add */
    private Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    public String execute(TaskList tasks, FileController fc) throws UnsavedChangesException {
        tasks.add(task);
        assert tasks.get(tasks.size() - 1) == task;

        if (!fc.writeText(tasks.serialize())) {
            throw new UnsavedChangesException();
        }
        return String.format("Added: %s\nNow you have %d tasks", task, tasks.size());
    }
}
