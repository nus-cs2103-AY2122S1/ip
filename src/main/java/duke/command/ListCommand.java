package duke.command;

import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.Ui;

import static duke.util.Ui.LIST_MESSAGE;
import static duke.util.Ui.NO_TASKS_IN_LIST_MESSAGE;
import static duke.util.Ui.TOO_MANY_ARGUMENTS_LIST_MESSAGE;

public class ListCommand extends Command {
    protected static final String COMMAND = "list";
    private final String remainingText;

    protected ListCommand(String remainingText) {
        this.remainingText = remainingText;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (!remainingText.isEmpty()) {
            throw new DukeException(TOO_MANY_ARGUMENTS_LIST_MESSAGE);
        }
        if (taskList.size() == 0) {
            Ui.displayMessage(NO_TASKS_IN_LIST_MESSAGE);
        } else {
            Ui.displayMessage(String.format(LIST_MESSAGE, taskList));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
