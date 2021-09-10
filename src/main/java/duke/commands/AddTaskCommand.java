package duke.commands;

import duke.exceptions.DuplicateTaskException;
import duke.tasks.Task;
import duke.utils.CliUi;
import duke.utils.Storage;
import duke.utils.TaskList;
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

    /**
     * Adds the task to the TaskList.
     *
     * @param taskList The list of tasks in Duke. Handles all task related functions.
     * @param cliUi Ui object to deal with user input/outputs.
     * @param storage Storage object to deal with saving taskList to disk.
     * @return String[] with the messages to be printed out to the Ui.
     */
    @Override
    public String execute(TaskList taskList, CliUi cliUi, Storage storage) throws DuplicateTaskException {
        taskList.addTask(task);
        assert taskList.getSize() > 0 : "taskList should not be empty after adding a task";
        String[] messages = new String[] {
            "Got it. I've added this task:",
            "    " + task.toString(),
            String.format("Now you have %d tasks in the list.", taskList.getSize())
        };

        cliUi.printOut(messages);
        storage.save(taskList);

        return String.join("\n", messages);
    }

    /**
     * Returns a String representation of the command.
     *
     * @return String representation of the command.
     */
    @Override
    public String toString() {
        return String.format("TO ADD: %s", task);
    }
}
