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
    private int type;
    private String response;

    /**
     * Sets up the search command.
     *
     * @param response The user input.
     * @param type The type of search command.
     */
    public SearchCommand(String response, int type) {
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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList currList = new TaskList();
        switch (type) {
        case 1:
            String preTime = response.substring(5);
            String actualTime = Task.dateAndTime(preTime);
            currList = tasks.tasksWithDate(actualTime);
            break;
        case 2:
            String content = response.substring(5);
            currList = tasks.tasksWithContent(content);
            break;
        default:
            break;
        }
        ui.showList(currList, currList.getSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
