package duke.command;

import duke.storage.Storage;
//
import duke.task.Task;
import duke.task.TaskList;
//
import duke.ui.Ui;

/**
 * Represents what the search command does.
 */
public class SearchCommand extends Command {
    private Operation type;
    private String response;

    /**
     * Sets up the search command.
     *
     * @param response The user input.
     * @param type The type of search command.
     */
    public SearchCommand(String response, Operation type) {
        this.response = response;
        this.type = type;
    }

    /**
     * Shows the task match the search date.
     *
     * @param tasks The list of tasks.
     * @param ui The user interaction instance.
     * @param storage The instance to store data.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList currList = new TaskList();
        switch (type) {
        case DATE:
            String preTime = response.substring(5);
            String actualTime = Task.formatOutputDateAndTime(preTime);
            currList = tasks.tasksWithDate(actualTime);
            break;
        case FIND:
            String content = response.substring(5);
            currList = tasks.tasksWithContent(content);
            break;
        default:
            break;
        }
        return ui.showList(currList, currList.getSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
