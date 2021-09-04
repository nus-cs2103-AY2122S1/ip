package seedu.duke.commands;

import seedu.duke.storage.Storage;
import seedu.duke.storage.TaskList;

public class ListCommand extends Command {

    /**
     * Prints the list of {@code Task} that is on hand from {@code TaskList}.
     * 
     * @param taskList contains an {@code ArrayList<Task>} where all {@code Task} is
     *                 stored.
     * @param storage  the database where the Tasks are being saved for progression.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return Ui.printList(taskList.getTaskList(), Ui.NO_TASK_MESSAGE, Ui.LIST_MESSAGE);
    }

    /**
     * Checks if the user wants to exit from the application.
     * 
     * @return {@code false} as this command is not ready for user to exit the
     *         application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
