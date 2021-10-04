package seedu.duke.commands;

import seedu.duke.exceptions.storage.DukeStorageSaveException;
import seedu.duke.storage.Storage;
import seedu.duke.storage.TaskList;
import seedu.duke.tasks.PeriodTask;
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
     * @param taskList  contains an {@code ArrayList<Task>} where all {@code Task}
     *                  is stored.
     * @param timetable it contains the entire schedule of the
     *                  {@code ScheduledTask}.
     * @param storage   the database where the Tasks are being saved for
     *                  progression.
     * @return a reply message or information from {@code Duke}.
     */
    @Override
    public String execute(TaskList taskList, Timetable timetable, Storage storage) {
        try {
            String timetableMessage = "";
            if (this.task.getSymbol().equals("ST")) {
                if (timetable.isClash((ScheduledTask) this.task)) {
                    return ("\n" + timetable.addPlanToDay((ScheduledTask) this.task));
                }
                timetableMessage = "\n" + timetable.addPlanToDay((ScheduledTask) this.task);
            }
            taskList.addTask(this.task);
            storage.appendToData(getInputStorageDescription());
            return getReplyMessage(taskList) + timetableMessage;

        } catch (DukeStorageSaveException err) {
            throw new DukeStorageSaveException(err.toString());
        }
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

    private String getInputStorageDescription() {
        String inputToStorage = this.task.getSymbol() + Command.DATA_STORAGE_ISDONE_FALSE + this.task.getDescription();
        switch (this.task.getSymbol()) {
        case "T":
            break;

        case "ST":
            ScheduledTask scheduledTask = (ScheduledTask) this.task;
            inputToStorage += " | " + scheduledTask.getDate() + " at " + scheduledTask.getTimeFrom() + " to "
                    + scheduledTask.getTimeTo();
            break;

        case "PT":
            PeriodTask periodTask = (PeriodTask) this.task;
            inputToStorage += " | " + periodTask.getFrom() + " and " + periodTask.getTo();
            break;

        default:
            inputToStorage += " | " + this.task.getDateTime();
            break;
        }
        return inputToStorage;
    }

    private String getReplyMessage(TaskList taskList) {
        return Ui.printMessage("Got it. I've added this task:\n" + this.task.toString() + "\n" + "Now you have "
                + taskList.getTaskList().size() + " tasks in the list.");
    }
}
