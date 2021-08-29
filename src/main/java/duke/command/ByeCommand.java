package duke.command;

import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

import static duke.util.Ui.EXIT_MESSAGE;

public class ByeCommand extends Command {
    protected static final String COMMAND = "bye";

    protected ByeCommand() {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Ui.displayMessage(EXIT_MESSAGE);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
