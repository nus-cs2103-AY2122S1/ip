package duke.Command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the user command to output all the tasks in the TaskList.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command. Each task in the TaskList is printed in order.
     *
     * @param taskList A TaskList object that contains an arraylist of Task objects.
     * @param ui A Ui object that deals with interactions with the user.
     * @param storage A Storage object that loads and saves tasks in the file.
     */
    @Override
    public void runCommand(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.printTasks(taskList);
    }

    /**
     * Indicates if the command ends the program after executing.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
