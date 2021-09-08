package duke.command;

import static duke.util.Ui.DONE_MESSAGE;
import static duke.util.Ui.INVALID_NUMBER;
import static duke.util.Ui.MISSING_DONE_NUMBER_MESSAGE;

import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.Ui;

public class DoneCommand extends Command {
    protected static final String COMMAND = "done";
    private String remainingText;

    protected DoneCommand(String remainingText) {
        this.remainingText = remainingText;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            if (remainingText.isEmpty()) {
                throw new DukeException(MISSING_DONE_NUMBER_MESSAGE);
            }
            int taskIndex = Integer.parseInt(remainingText);
            return String.format(DONE_MESSAGE, taskList.markTaskAsDone(taskIndex));
        } catch (NumberFormatException err) {
            throw new DukeException(INVALID_NUMBER);
        }
    }
}
