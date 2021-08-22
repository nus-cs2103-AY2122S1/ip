package util.commands;

import util.tasks.*;
import util.ui.*;
import util.commons.*;
/**
 * Command representing the addition of a
 * task to the task list.
 *
 */
public class AddCommand implements Command {
    private final TaskList tasks;
    private final Task task;

    //should use the same Ui -- or can use a different one?
    private final Ui ui;

    public AddCommand(TaskList tasks, Task task) {
        this.tasks = tasks;
        this.task = task;
        this.ui = new Ui();
    }

    @Override
    public void execute() {
        if (TaskList.isAdded(task, tasks)) {
            this.ui.print(String.format(Messages.TASK_ALREADY_ADDED, task.toString()));
        } else {
            tasks.add(task);
            ui.printTaskAdded(task, tasks.size());
        }
    }
}
