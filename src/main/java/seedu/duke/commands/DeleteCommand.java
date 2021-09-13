package seedu.duke.commands;

import seedu.duke.exceptions.storage.DukeStorageDeleteException;
import seedu.duke.storage.Storage;
import seedu.duke.storage.TaskList;
import seedu.duke.tasks.ScheduledTask;
import seedu.duke.tasks.Task;
import seedu.duke.timetable.Timetable;

public class DeleteCommand extends Command {
    private final String taskId;

    /**
     * Primary Constructor.
     * 
     * @param taskId is the id of the task that will be deleted.
     */
    public DeleteCommand(String taskId) {
        this.taskId = taskId;
    }

    /**
     * Deletes the {@code Task} from {@code TaskList} when this function is
     * executed.
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
            int index = Integer.parseInt(this.taskId) - 1;
            Task deletedTask = taskList.deleteItem(index);
            if (deletedTask.getSymbol().equals("ST")) {
                timetableMessage = "\n" + timetable.deletePlanFromDay((ScheduledTask) deletedTask);
            }
            storage.deleteTaskFromData(index);

            return getReplyMessage(taskList, deletedTask) + timetableMessage;
        } catch (DukeStorageDeleteException err) {
            throw new DukeStorageDeleteException(err.toString());
        }
    }

    private String getReplyMessage(TaskList taskList, Task deletedTask) {
        return Ui.printMessage("Noted. I've removed this task:\n" + deletedTask.toString() + "\n" + "Now you have "
                + taskList.getTaskList().size() + " tasks in the list.");
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
    };
}
