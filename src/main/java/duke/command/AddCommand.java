package duke.command;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Task;
import duke.ui.UiPane;

public class AddCommand extends Command {
    /* The task to add when executing */
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, UiPane uiPane) {
        taskList.add(task);
        storage.write(taskList.getTasks());
        uiPane.showTaskList(taskList.getTasks());
        uiPane.showMessage(
                String.format(
                        "You have added the task: %s. You now have %d tasks.",
                        task.getDescription(),
                        taskList.getTaskCount()
                )
        );
    }

    public Task getTask() {
        return task;
    }
}
