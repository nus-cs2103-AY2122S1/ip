package duke.commands;

import java.time.format.DateTimeParseException;

import duke.exceptions.IllegalFormatException;
import duke.exceptions.NoSuchTaskException;
import duke.exceptions.UpdateFailException;
import duke.gui.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Encapsulates the information for the UpdateCommand object.
 * Format to activate the update command: "update taskIndex tag newUpdate"
 * There is a total of 3 tags:
 * 1) title, to update the description of the task
 * 2) date, to update the due date of the task if it exist
 * 3) status, to update the completion status of the task
 */
public class UpdateCommand extends Command {
    private final String TITLE_TAG = "title";
    private final String DATE_TAG = "date";
    private final String STATUS_TAG = "status";

    private final int id;
    private final String tag;
    private final String information;

    /**
     * Constructor for UpdateCommand object.
     *
     * @param id The id of the task to be updated
     * @param tag The thing that is needed to be updated
     * @param information The new information
     */
    public UpdateCommand(int id, String tag, String information) {
        this.id = id;
        this.tag = tag;
        this.information = information;
    }

    @Override
    public String executeCommand(TaskList taskList) throws NoSuchTaskException, UpdateFailException,
            IllegalFormatException {
        Task task = taskList.getTask(this.id);

        checkTag(task);

        return Ui.printUpdateMessage(taskList, this.id);
    }

    private void checkTag(Task task) throws IllegalFormatException, UpdateFailException {
        switch (tag) {
        case TITLE_TAG:
            updateTitle(task);
            break;
        case STATUS_TAG:
            updateStatus(task);
            break;
        case DATE_TAG:
            updateDate(task);
            break;
        default:
            invalidUpdate();
            return;
        }
    }

    /**
     * Updates the description of the task specified.
     *
     * @param task The task to be updated.
     */
    private void updateTitle(Task task) {
        task.setDescription(information);
    }

    /**
     * Updates the status of the task specified.
     * The word that follows after "status" in the command indicates:
     * true indicates that the user wants to update the completion status to completed.
     * false indicates that the user wants to reset the completion status. (Not completed)
     *
     * @param task The task to be updated.
     * @throws IllegalFormatException Wrong format used by user.
     */
    private void updateStatus(Task task) throws IllegalFormatException {
        final String markAsComplete = "true";
        final String markAsIncomplete = "false";

        if (information.equalsIgnoreCase(markAsComplete)) {
            task.markAsComplete();
        } else if (information.equalsIgnoreCase(markAsIncomplete)) {
            task.markAsIncomplete();
        } else {
            String errorMsg = "Please key in true/false only!\n"
                    + "true - for completed\nfalse - for incomplete";
            throw new IllegalFormatException(errorMsg);
        }
    }

    /**
     * Updates the date of the task specified, if this field exist.
     *
     * @param task The task to be updated.
     * @throws UpdateFailException User tries to update a task without a date field.
     * @throws IllegalFormatException Wrong format used by user.
     */
    private void updateDate(Task task) throws UpdateFailException, IllegalFormatException {
        String taskType = task.getTag();
        boolean isInformationDate = tag.equalsIgnoreCase("date");
        boolean isTaskTodo = taskType.equalsIgnoreCase("T");
        boolean isUnableToUpdate = isInformationDate && isTaskTodo;

        // User tries to update the date of a Todo task.
        if (isUnableToUpdate) {
            throw new UpdateFailException();
        }

        try {
            task.setDate(information);
        } catch (DateTimeParseException e) {
            String errorMsg = "Date format is wrong. It should be yyyy-mm-dd";
            throw new IllegalFormatException(errorMsg);
        }
    }

    /**
     * Throws an IllegalFormatException as user entered a tag that is unrecognised by the chat bot.
     *
     * @throws IllegalFormatException Wrong format used by user.
     */
    private void invalidUpdate() throws IllegalFormatException {
        String errorMsg = "Plase check if you have used the correct format.\n "
                + "Also, Please note that tag can only be either one of the 3:\n"
                + "\t1) title - to update task description\n"
                + "\t2) date - to update due date of task\n"
                + "\t3) status - to update completion status of the task\n";
        throw new IllegalFormatException(errorMsg);
    }
}
