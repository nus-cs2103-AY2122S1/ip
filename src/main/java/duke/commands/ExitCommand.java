package duke.commands;

import duke.DukeException;
import duke.DukeStorage;
import duke.TaskList;
import duke.Ui;

/**
 * Class that contains the exit command
 *
 */
public class ExitCommand extends Command {

    /**
     * Constructor for the exit command class
     *
     */
    public ExitCommand(){}

    /**
     * Executes the exit command
     *
     * @param taskList The list of tasks that is associated with the instance of Duke
     * @param ui The UI that is associated with the instance of Duke
     * @param storage The storage that is associated with the instance of Duke
     * @return Duke's String response
     * @throws DukeException
     */
    @Override
    public String execute(TaskList taskList, Ui ui, DukeStorage storage) throws DukeException {
        try {
            storage.writeTasks(taskList);
            return ui.byeMessage();
        } catch (DukeException error) {
            return (error.getMessage());
        }
    }
}
