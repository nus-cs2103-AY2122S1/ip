package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Representation of remove command of Duke.
 */
public class RemoveCommand extends Command {
    private final int indexToRemove;

    /**
     * Constructor for RemoveCommand.
     *
     * @param indexToRemove Index of TaskList to be removed.
     */
    public RemoveCommand(int indexToRemove) {
        this.indexToRemove = indexToRemove;
    }

    /**
     * Checks if this is an exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the RemoveCommand.
     *
     * @param taskList TaskList to remove command at index indexToRemove from.
     * @param ui Ui to print to users of Duke.
     * @param storage Storage to save and load TaskList of Duke.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (this.indexToRemove == -1) {
            String message = formatAndRemoveAll(taskList);

            ui.print(message);
        } else {
            String message = formatAndRemoveIndexToRemove(taskList);

            ui.print(message);
        }
    }

    /**
     * Gets the String representation of the things printed in the
     * execute method as well as execute the removal of the index.
     *
     * @param taskList TaskList to remove command at index indexToRemove from.
     * @param ui Ui to get the String representation of the text printed.
     * @param storage Storage to save and load TaskList of Duke.
     */
    @Override
    public String getExecutedString(TaskList taskList, Ui ui, Storage storage) {
        if (this.indexToRemove == -1) {
            String message = formatAndRemoveAll(taskList);

            return message;
        } else {
            String message = formatAndRemoveIndexToRemove(taskList);

            return message;
        }
    }

    /**
     * Formats and removes all the items from taskList.
     *
     * @param taskList taskList to remove all the tasks from.
     * @return Returns the formatted string to be printed to the user.
     */
    private String formatAndRemoveAll(TaskList taskList) {
        String message = "Noted. I've removed these tasks:\n";

        int counter = 0;
        int size = taskList.getSize();

        for (int i = 0; i < size; i++) {
            Task toRemove = taskList.remove(0);
            counter++;
            message += counter + "." + toRemove + "\n";
        }

        message += "Now you have 0 tasks in the list";
        return message;
    }

    /**
     * Formats and removes task at indexToRemove from taskList.
     *
     * @param taskList taskList to remove task at indexToRemove.
     * @return Returns the formatted string to be printed to the user.
     */
    private String formatAndRemoveIndexToRemove(TaskList taskList) {
        Task toRemove = taskList.remove(this.indexToRemove);
        return "Noted. I've removed this task:\n" + toRemove + "\nNow you have "
                + taskList.getSize() + " tasks in the list";
    }
}
