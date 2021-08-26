package bruh.command;

import bruh.storage.Storage;
import bruh.task.Task;
import bruh.tasklist.TaskList;
import bruh.ui.Ui;

public class AddTaskCommand extends Command {
    private final Task taskToAdd;

    public AddTaskCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    @Override
    public String runAndGenerateDescription(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(taskToAdd);
        return String.format("Got it. I've added this task:\n  %s\n%s", taskToAdd.toString(), taskList.getTaskCountDesc());
    }
}
