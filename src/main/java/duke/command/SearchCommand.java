package duke.command;

import duke.exception.MismatchedFormException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents what the search command does.
 */
public class SearchCommand extends Command {
    private Operation type;
    private String response;
    private int splitIndex;

    /**
     * Sets up the search command.
     *
     * @param response The user input.
     * @param type The type of search command.
     * @param splitIndex The index where the command is split.
     */
    public SearchCommand(String response, Operation type, int splitIndex) {
        this.response = response;
        this.type = type;
        this.splitIndex = splitIndex;
    }

    /**
     * Shows the task match the search date.
     *
     * @param tasks The list of tasks.
     * @param ui The user interaction instance.
     * @param storage The instance to store data.
     * @throws MismatchedFormException The exceptions that happen when no match happens.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MismatchedFormException {
        TaskList resultList = new TaskList();
        String toSearch = response.substring(splitIndex);
        switch (type) {
        case DATE:
            String actualTime = Task.formatOutputDateAndTime(toSearch);
            resultList = tasks.tasksWithDateSame(actualTime);
            break;
        case FIND:
            resultList = tasks.tasksWithContent(toSearch);
            break;
        default:
            break;
        }
        return resultList.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
