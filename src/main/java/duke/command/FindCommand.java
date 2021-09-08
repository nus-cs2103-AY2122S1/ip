package duke.command;

import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class FindCommand extends Command {
    protected static final String COMMAND = "find";
    private String remainingText;

    protected FindCommand(String remainingText) {
        this.remainingText = remainingText;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.findTask(remainingText);
    }
}
