package duke.command;

import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class FindCommand extends Command {
    protected static final String COMMAND = "find";
    private final String remainingText;

    protected FindCommand(String remainingText) {
        this.remainingText = remainingText;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Ui.displayMessage(taskList.findTask(remainingText));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
