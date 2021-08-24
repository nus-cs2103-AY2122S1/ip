package duke.command;

import duke.command.Command;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class SearchCommand extends Command {
    private String response;

    public SearchCommand(String response) {
        this.response = response;
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
        String preTime = response.substring(5);
        String actualTime = Task.dateAndTime(preTime);
        TaskList currList = tasks.tasksWithDate(actualTime);
        ui.showList(currList, currList.getSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
