package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to display all tasks.
 */
public class ListCommand extends Command {
    /**
     * Displays all tasks
     *
     * @param tasks TaskList to be manipulated if necessary.
     * @param ui Ui to display command message to.
     * @param storage Storage to interact with if necessary.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage(this.getMessage(tasks));
    }

    /**
     * Returns the message to be displayed while performing the task.
     *
     * @param tasks TaskList of current tasks.
     * @return Message to display to the user.
     */
    @Override
    public String getMessage(TaskList tasks) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Your tasks:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            stringBuilder.append(String.format("%d. %s\n",
                    i + 1,
                    tasks.getIndex(i).toString()));
        }
        return stringBuilder.toString();
    }
}
