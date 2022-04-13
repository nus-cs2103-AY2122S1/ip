package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    /**
     * A method to execute the ListCommand. When the method is executed,
     * it will call the given TaskList to list out all the tasks,
     * and send them to the given Ui for printing.
     *
     * @param taskList The given Duke TaskList.
     * @param ui The given Duke Ui.
     * @param storage The given Duke Storage.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.generateDukeResponse(taskList.listAllTasks());
    }
}
