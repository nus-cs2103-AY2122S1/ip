package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Command that deletes tasks.
 */
public class DeleteCommand extends Command {
    private int deletedTaskIndex; // the index in the list

    public DeleteCommand(int deletedTaskIndex) {
        this.deletedTaskIndex = deletedTaskIndex;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task deletedTask = tasks.delete(deletedTaskIndex);
        storage.convertTaskListToFile(tasks);
        return ui.deleteTask(deletedTask, tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DeleteCommand) {
            @SuppressWarnings("have checked obj is MarkAsDoneCommand, can safely parse")
            DeleteCommand temp = (DeleteCommand) obj;
            return temp.deletedTaskIndex == this.deletedTaskIndex;
        } else {
            return false;
        }
    }
}
