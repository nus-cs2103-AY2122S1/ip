package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a command which adds a task to the task list.
 */
public class AddTaskCommand extends Command {
    private final Task taskToAdd;

    /**
     * Constructor for a command to add a task to the task list.
     *
     * @param taskToAdd The task to be added to the task list.
     */
    public AddTaskCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    @Override
    public String runAndGenerateDescription(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(taskToAdd);
        return String.format("Got it. I've added this task:\n  %s\n%s", taskToAdd.toString(), taskList.getTaskCountDesc());
    }
}
