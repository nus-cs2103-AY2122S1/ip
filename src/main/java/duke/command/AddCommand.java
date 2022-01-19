package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/** A class for add task command. */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs an add command class.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.addTask(this.task);
        ui.sayAddTask(this.task, taskList.size());
        storage.store(taskList.serialize());
    }
}
