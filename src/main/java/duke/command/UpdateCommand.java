package duke.command;

import java.time.LocalDate;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.UiPane;

public class UpdateCommand extends Command {
    /* The serial number of the task to update. */
    private final int serialNo;
    /* The description of the task to update to. */
    private final String description;
    /* The date of the task to update to. */
    private final LocalDate date;

    /**
     * Creates a UpdateCommand instance with the serial number of the task and the description
     * and date to update to.
     *
     * @param serialNo The serial number of the task to update.
     * @param description The description of the task to update to. Set to null if description is not to be updated.
     * @param date The date of the task to update to. Set to null if date is not to be updated.
     */
    public UpdateCommand(int serialNo, String description, LocalDate date) {
        this.serialNo = serialNo;
        this.description = description;
        this.date = date;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, UiPane uiPane) throws DukeException {
        taskList.updateTask(serialNo, description, date);
        storage.write(taskList.getTasks());
        uiPane.showTaskList(taskList.getTasks());
        uiPane.showMessage(
                String.format("Task %d has been updated.", serialNo)
        );
    }
}
