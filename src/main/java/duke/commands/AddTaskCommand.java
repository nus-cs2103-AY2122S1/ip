package duke.commands;

import duke.tasks.Task;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.CliUi;

/** Abstract class to represent command when we add something to the taskList */
public abstract class AddTaskCommand extends Command {

    private Task task;

    /**
     * AddTaskCommand constructor.
     *
     * @param task Task that this command will add to the taskList.
     */
    public AddTaskCommand(Task task) {
        this.task = task;
        assert (task != null) : "task should not be null";
    }

    @Override
    public String execute(TaskList tasks, CliUi cliUi, Storage storage) {
        tasks.addTask(task);
        assert tasks.getSize() > 0 : "taskList should not be empty after adding a task";
        String[] messages = new String[] {
            "Got it. I've added this task:",
            "    " + task.toString(),
            String.format("Now you have %d tasks in the list.", tasks.getSize())
        };
        cliUi.printOut(messages);
        storage.save(tasks);
        return String.join("\n", messages);
    }

    @Override
    public String toString() {
        return String.format("TO ADD: %s", task);
    }
}
