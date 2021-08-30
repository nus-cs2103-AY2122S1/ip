package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * The ListCommand is used when displaying all the Tasks of the TaskList.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public ListCommand() {

    }

    /**
     * Prints all the tasks in the given TaskList.
     * If there are no tasks in the TaskList, will print a message saying so.
     *
     * @param tasks the given TaskList.
     * @param ui the given Ui.
     * @param storage the given Storage.
     * @return the string for the Ui to print.
     * @throws DukeException shouldn't happen.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String msg = tasks.listTaskArr();
        if (msg.isBlank()) {
            msg = "No tasks in the list!";
        }
        return msg;
    }

}
