package duke;

import java.util.ArrayList;

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
        TaskList taskList = new TaskList(new ArrayList<Task>());
        taskList.add(taskToBeAdded);
        ui.addReminder(taskList);
        return ui.addTask(taskToBeAdded, tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AddCommand) {
            return true;
        } else {
            return false;
        }
    }

}
