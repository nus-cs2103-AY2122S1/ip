package seedu.duke.commands;

import seedu.duke.storage.Storage;
import seedu.duke.storage.TaskList;
import seedu.duke.tasks.AfterTask;
import seedu.duke.timetable.Timetable;

public class AddChainCommand extends Command {
    private final String description;
    private final String taskId;

    public AddChainCommand(String description, String taskId) {
        this.description = description;
        this.taskId = taskId;
    }

    /**
     * Chains a {@code Task} with an {@code AfterTask}.
     * 
     * @param taskList  contains an {@code ArrayList<Task>} where all {@code Task}
     *                  is stored.
     * 
     * @param timetable it contains the entire schedule of the
     *                  {@code ScheduledTask}.
     * @param storage   the database where the Tasks are being saved for
     *                  progression.
     * @return a reply message or information from {@code Duke}.
     */
    @Override
    public String execute(TaskList taskList, Timetable timetable, Storage storage) {
        int index = Integer.parseInt(this.taskId) - 1;
        taskList.getTaskList().get(index).setAfterTask(new AfterTask(this.description));
        storage.updateAfterTask(index, description);
        return getReplyMessage();
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

    private String getReplyMessage() {
        return this.description + " task is queued, and will be available after you completed task index "
                + this.taskId;
    }

}
