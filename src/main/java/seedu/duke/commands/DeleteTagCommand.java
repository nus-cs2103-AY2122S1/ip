package seedu.duke.commands;

import seedu.duke.storage.Storage;
import seedu.duke.storage.TaskList;
import seedu.duke.timetable.Timetable;

public class DeleteTagCommand extends Command {
    private final String taskId;

    /**
     * Primary constructor for this class.
     * 
     * @param taskId is the task id for which the tags needs to be deleted.
     */
    public DeleteTagCommand(String taskId) {
        this.taskId = taskId;
    }

    /**
     * Deletes the tags that is tied to task.
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
        int index = Integer.parseInt(this.taskId) - 1;
        taskList.deleteTags(index);
        storage.deleteTags(index);
        return "Tags deleted";
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
