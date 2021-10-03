package seedu.duke.commands;

import seedu.duke.storage.Storage;
import seedu.duke.storage.TaskList;
import seedu.duke.timetable.Timetable;

public class TagCommand extends Command {
    private final String taskId;
    private final String tags;

    /**
     * Primary Constructor for this class.
     * 
     * @param taskId is the task id for which the tag needs to be updated.
     * @param tags   is the tags which will be added to the {@code Task}.
     */
    public TagCommand(String taskId, String tags) {
        this.taskId = taskId;
        this.tags = tags;
    }

    /**
     * Adds tags to the specified {@code Task}.
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
        taskList.addTags(index, this.tags);
        storage.updateTags(index, tags);
        return "Tags added";
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
