package seedu.duke.commands;

import seedu.duke.exceptions.storage.DukeStorageSaveException;
import seedu.duke.storage.Storage;
import seedu.duke.storage.TaskList;
import seedu.duke.tasks.Task;

public class AddCommand extends Command {
    private final Task task;

    /**
     * Primary Constructor.
     * 
     * @param task is the task that will be added into the list of tasks and the
     *             database.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Helps to add the task into the list of tasks when this function is executed.
     * 
     * @param taskList the list of Tasks which is being stored.
     * @param storage  the database where the Tasks are being saved for progression.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        String inputToStorage;
        try {
            taskList.addTask(this.task);

            inputToStorage = this.task.getSymbol() + " | 0 | " + this.task.getDescription();
            if (!this.task.getSymbol().equals("T")) {
                inputToStorage += " | " + this.task.getDateTime();
            }

            storage.appendToData(inputToStorage);

            // return Ui.printMessage("Got it. I've added this task:\n" + Ui.INDENT + " " +
            // task.toString() + "\n"
            // + Ui.INDENT + "Now you have " + taskList.getTaskList().size() + " tasks in
            // the list.");

            return Ui.printMessage("Got it. I've added this task:\n" + task.toString() + "\n" + "Now you have "
                    + taskList.getTaskList().size() + " tasks in the list.");

        } catch (DukeStorageSaveException err) {
            throw new DukeStorageSaveException(err.toString());
        }
    }

    /**
     * Checks if the user wants to exit from the application.s
     * 
     * @return boolean whether the user wants to exit from the application.
     */
    @Override
    public boolean getIsExit() {
        return false;
    }
}
