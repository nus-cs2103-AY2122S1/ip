package duke.command;

import static duke.util.Ui.INVALID_NUMBER;
import static duke.util.Ui.MISSING_DELETE_NUMBER_MESSAGE;

import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.Ui;

public class DeleteCommand extends Command {
    protected static final String COMMAND = "delete";
    private String remainingText;

    protected DeleteCommand(String remainingText) {
        this.remainingText = remainingText;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            if (remainingText.isEmpty()) {
                throw new DukeException(MISSING_DELETE_NUMBER_MESSAGE);
            }

            int taskIndex = Integer.parseInt(remainingText);
            return taskList.deleteTask(taskIndex);
        } catch (NumberFormatException err) {
            throw new DukeException(INVALID_NUMBER);
        }
    }
}
