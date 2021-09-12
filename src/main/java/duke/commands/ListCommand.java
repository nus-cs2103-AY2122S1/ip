package duke.commands;

import duke.DukeStorage;
import duke.TaskList;
import duke.Ui;

/**
 * Class that contains the list command
 *
 */
public class ListCommand extends Command {

    /**
     * Constructor for the list command class
     *
     */
    public ListCommand(){};

    /**
     * Executes the list command
     *
     * @param taskList The list of tasks that is associated with the instance of Duke
     * @param ui The UI that is associated with the instance of Duke
     * @param storage The storage that is associated with the instance of Duke
     * @return Duke's String response
     */
    @Override
    public String execute(TaskList taskList, Ui ui, DukeStorage storage) {
        return ui.listTasks(taskList);
    }
}
