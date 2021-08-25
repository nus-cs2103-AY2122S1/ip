package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class AddTaskCommand extends Command {
    private Task taskToAdd;

    public AddTaskCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    @Override
    public String runAndGenerateDescription(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(taskToAdd);
        return String.format("Got it. I've added this task:\n  %s\n%s", taskToAdd.toString(), taskList.getTaskCountDesc());
    }
}
