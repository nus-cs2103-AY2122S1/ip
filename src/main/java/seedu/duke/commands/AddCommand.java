package seedu.duke.commands;

import seedu.duke.exceptions.storage.DukeStorageSaveException;
import seedu.duke.storage.Storage;
import seedu.duke.storage.TaskList;
import seedu.duke.tasks.ScheduledTask;
import seedu.duke.tasks.Task;
import seedu.duke.timetable.Timetable;

public class AddCommand extends Command {
    private final Task task;

    /**
     * Primary Constructor.
     * 
     * @param task is the {@code Task} that will be added into the list of tasks and
     *             the database.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds {@code Task} into the {@code TaskList} when this function is executed.
     * 
     * @param taskList contains an {@code ArrayList<Task>} where all {@code Task} is
     *                 stored.
     * @param storage  the database where the tasks are being saved for progression.
     */
    @Override
    public String execute(TaskList taskList, Timetable timetable, Storage storage) {
        try {
            String timetableMessage = "";
            if (this.task.getSymbol().equals("ST")) {
                timetableMessage = "\n" + timetable.addPlanToDay((ScheduledTask) this.task);
            }
            taskList.addTask(this.task);
            storage.appendToData(getInputStorageDescription());
            return getReplyMessage(taskList) + timetableMessage;

        } catch (DukeStorageSaveException err) {
            throw new DukeStorageSaveException(err.toString());
        }
    }

    private String getInputStorageDescription() {
        String inputToStorage = this.task.getSymbol() + Command.DATA_STORAGE_ISDONE_FALSE + this.task.getDescription();
        if (!this.task.getSymbol().equals(Command.DATA_STORAGE_TASK_SYMBOL)) {
            inputToStorage += " | " + this.task.getDateTime();
        }
        return inputToStorage;
    }

    private String getReplyMessage(TaskList taskList) {
        return Ui.printMessage("Got it. I've added this task:\n" + this.task.toString() + "\n" + "Now you have "
                + taskList.getTaskList().size() + " tasks in the list.");
    }

    /**
     * Checks if the user wants to exit from the applications.
     * 
     * @return {@code false} as this command is not ready for user to exit the
     *         application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
