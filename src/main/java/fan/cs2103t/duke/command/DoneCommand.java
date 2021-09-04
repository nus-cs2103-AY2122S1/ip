package fan.cs2103t.duke.command;

import static fan.cs2103t.duke.commons.Messages.MESSAGE_SUCCESSFULLY_DONE_FORMAT;
import static fan.cs2103t.duke.commons.Messages.MESSAGE_TASK_ALREADY_DONE;
import static fan.cs2103t.duke.commons.Messages.MESSAGE_TASK_NOT_FOUND;

import fan.cs2103t.duke.task.Task;
import fan.cs2103t.duke.task.TaskList;
import fan.cs2103t.duke.ui.Ui;

/**
 * Represents a Duke's command which changes the completion status of tasks to "done".
 * <p>
 * This is a subclass of the <code>Command</code> class.
 */
public class DoneCommand extends Command {

    private final int taskIndex;

    /**
     * Constructs a done command with the specified index of the task to be marked as done.
     *
     * @param taskIndex the index of the task to be marked as done.
     */
    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes this command. Changes the completion status of the task with the index to "done".
     * Displays a message to the user through the specified UI if the status of the task is successfully changed,
     * or the task has already been marked as done, or the task with the index does not exist.
     * Returns the status message as a string.
     *
     * @param taskList the task list that the task to be marked as done is in.
     * @param ui the UI for the message to be displayed through.
     * @return a message to indicate the status of execution.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        String output;
        try {
            Task t = taskList.getTasks().get(taskIndex - 1);
            if (t.getIsDone()) {
                output = MESSAGE_TASK_ALREADY_DONE;
                ui.displayText(output);
            } else {
                t.markAsDone();
                output = String.format(MESSAGE_SUCCESSFULLY_DONE_FORMAT, t.getDescriptionWithStatus());
                ui.displayText(output);
            }
        } catch (IndexOutOfBoundsException ex) {
            output = MESSAGE_TASK_NOT_FOUND;
            ui.displayText(output);
        }
        return output;
    }

}
