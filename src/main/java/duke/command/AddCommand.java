package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Command that adds tasks.
 */
public class AddCommand extends Command {
    private Task taskToBeAdded;

    public AddCommand(Task taskToBeAdded) {
        this.taskToBeAdded = taskToBeAdded;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(taskToBeAdded);
        storage.addTaskToFile(taskToBeAdded);
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.add(taskToBeAdded);
        ui.addReminder(taskList);
        return ui.addTask(taskToBeAdded, tasks.size());
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof AddCommand;
    }
}
