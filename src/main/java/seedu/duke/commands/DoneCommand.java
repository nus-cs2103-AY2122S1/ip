package seedu.duke.commands;

import seedu.duke.exceptions.storage.DukeStorageUpdateException;
import seedu.duke.storage.Storage;
import seedu.duke.storage.TaskList;
import seedu.duke.tasks.ScheduledTask;
import seedu.duke.tasks.Task;
import seedu.duke.timetable.Timetable;

public class DoneCommand extends Command {
    private final String taskId;

    /**
     * Primary Constructor.
     * 
     * @param taskId is the task that will be updated as done in the list of tasks.
     */
    public DoneCommand(String taskId) {
        this.taskId = taskId;
    }

    /**
     * Updates the {@code Task} in the {@code TaskList} as done when this function
     * is executed.
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
        try {
            Task currTask = taskList.getTaskList().get(index);
            Task updatedTask = taskList.doneItem(index);
            if (currTask.getSymbol().equals("ST")) {
                timetable.markDone((ScheduledTask) currTask);
            }
            if (currTask.hasAfterTask()) {
                String afterTaskDescription = currTask.getAfterTask().getDescription();
                String inputToStorage = "T" + Command.DATA_STORAGE_ISDONE_FALSE + afterTaskDescription;
                taskList.addTask(currTask.getAfterTask().getTask());
                storage.updateDone(index);
                storage.appendToData(inputToStorage);
                return getReplyMessageWithAfterTask(taskList, updatedTask, afterTaskDescription);
            }
            storage.updateDone(index);

            return getReplyMessage(taskList, updatedTask);

        } catch (DukeStorageUpdateException err) {
            throw new DukeStorageUpdateException(err.toString());
        }
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

    private String getReplyMessage(TaskList taskList, Task updatedTask) {
        return Ui.printMessage(Ui.DONE_MESSAGE + "\n" + updatedTask.toString());
    }

    private String getReplyMessageWithAfterTask(TaskList taskList, Task updatedTask, String afterTaskDescription) {
        String message = getReplyMessage(taskList, updatedTask);
        message += "Added new task: " + afterTaskDescription;
        return message;
    }
}
