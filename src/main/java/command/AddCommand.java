package command;

import duke.TaskList;
import duke.Ui;
import task.Task;

public class AddCommand extends Command {
    private final Task newTask;

    public AddCommand(Task newTask) {
        super();
        this.newTask = newTask;
    }

    /**
     * Executes the command of adding the task to the given task list.
     * 
     * @param ui Ui not used in this execution.
     * @param taskList The task list to which the task is added.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        taskList.addTask(newTask);
    }
}
