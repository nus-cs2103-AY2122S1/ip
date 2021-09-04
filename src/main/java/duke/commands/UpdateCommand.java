package duke.commands;

import java.time.format.DateTimeParseException;

import duke.exceptions.IllegalFormatException;
import duke.exceptions.NoSuchTaskException;
import duke.exceptions.UpdateFailException;
import duke.gui.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class UpdateCommand extends Command {
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
        case "title":
            updateTitle(task);
            break;
        case "status":
            updateStatus(task);
            break;
        case "date":
            updateDate(task);
            break;
        default:
            invalidUpdate();
            return;
        }
    }

    private void updateTitle(Task task) {
        task.setDescription(information);
    }

    private void updateStatus(Task task) throws IllegalFormatException {
        if (information.equalsIgnoreCase("true")) {
            task.markAsComplete();
        } else if (information.equalsIgnoreCase("false")) {
            task.markAsIncomplete();
        } else {
            String errorMsg = "Please key in true/false only!\n"
                    + "true - for completed\nfalse - for incomplete";
            throw new IllegalFormatException(errorMsg);
        }
    }

    private void updateDate(Task task) throws UpdateFailException, IllegalFormatException {
        String taskType = task.getTag();
        boolean isInformationDate = tag.equalsIgnoreCase("date");
        boolean isTaskTodo = taskType.equalsIgnoreCase("T");
        boolean isUnableToUpdate = isInformationDate && isTaskTodo;

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

    private void invalidUpdate() throws IllegalFormatException {
        String errorMsg = "Plase check if you have used the correct format.\n "
                + "Also, Please note that tag can only be either one of the 3:\n"
                + "\t1) title - to update task description\n"
                + "\t2) date - to update due date of task\n"
                + "\t3) status - to update completion status of the task\n";
        throw new IllegalFormatException(errorMsg);
    }
}
