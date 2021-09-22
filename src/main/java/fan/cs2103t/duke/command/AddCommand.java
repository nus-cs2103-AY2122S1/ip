package fan.cs2103t.duke.command;

import static fan.cs2103t.duke.commons.Messages.MESSAGE_SUCCESSFULLY_ADDED_FORMAT;

import fan.cs2103t.duke.task.Task;
import fan.cs2103t.duke.task.TaskList;
import fan.cs2103t.duke.ui.Ui;

/**
 * Represents a Duke's command which adds tasks to Duke's task list upon execution.
 * <p>
 * This is a subclass of the <code>Command</code> class.
 */
public class AddCommand extends Command {

    private final Task task;

    /**
     * Constructs an add command with the specified task to be added to Duke's task list.
     *
     * @param task the task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes this command. Adds the task to the specified task list.
     * Displays a status message to the user through the specified UI if the task is successfully added,
     * or exits the current program immediately if the process fails.
     * Returns the status message as a string.
     *
     * @param taskList the task list for the task to be added into.
     * @param ui the UI for the message to be displayed through.
     * @return a message to indicate the status of execution.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        String output = null;
        if (taskList.addTask(task)) {
            output = String.format(MESSAGE_SUCCESSFULLY_ADDED_FORMAT,
                    task.getDescriptionWithStatus(), taskList.getNumOfTasks());
            return output;
        } else {
            System.exit(1);
        }
        return output;
    }

}
