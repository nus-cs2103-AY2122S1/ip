package duke.command;

import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Represents the find command.
 */
public class FindCommand extends Command {
    protected static final String COMMAND = "find";
    private String remainingText;

    protected FindCommand(String remainingText) {
        this.remainingText = remainingText;
    }

    /**
     * Executes the command.
     *
     * @param taskList The taskList keeping track of the tasks.
     * @param ui The Ui used for the user interface.
     * @param storage The storage object taking care of writing and reading the text file.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.findTask(remainingText);
    }
}
