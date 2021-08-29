package duke.command;

import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.Ui;

import static duke.util.Ui.DONE_MESSAGE;
import static duke.util.Ui.INVALID_NUMBER;
import static duke.util.Ui.MISSING_DONE_NUMBER_MESSAGE;

public class DoneCommand extends Command {
    protected static final String COMMAND = "done";
    private final String remainingText;

    protected DoneCommand(String remainingText) {
        this.remainingText = remainingText;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (remainingText.isEmpty()) {
            throw new DukeException(MISSING_DONE_NUMBER_MESSAGE);
        }
        try {
            int taskIndex = Integer.parseInt(remainingText);
            Ui.displayMessage(String.format(DONE_MESSAGE, taskList.markTaskAsDone(taskIndex)));
        } catch (NumberFormatException err) {
            throw new DukeException(INVALID_NUMBER);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}