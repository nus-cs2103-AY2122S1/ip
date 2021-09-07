package duke.command;

import java.time.LocalDate;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.UiPane;

public class UpdateCommand extends Command {
    private final int serialNo;
    private final String description;
    private final LocalDate date;

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
