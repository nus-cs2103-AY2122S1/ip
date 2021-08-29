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
     * Method that executes the exit command
     *
     * @param taskList The list of tasks that is associated with the instance of Duke
     * @param ui The UI that is associated with the instance of Duke
     * @param storage The storage that is associated with the instance of Duke
     * @throws DukeException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, DukeStorage storage) throws DukeException {
        try {
            ui.byeMessage();
            storage.writeTasks(taskList);
        } catch (DukeException error) {
            System.out.println(error.getMessage());
        }
    }

    /**
     * Method that returns boolean depending on if Duke is to be exited
     *
     * @return boolean that returns true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}