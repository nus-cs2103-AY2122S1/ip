package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Class the implements command. This class is responsible for displaying the
 * current list of existing tasks.
 */
public class ListCommand implements Command {

    /**
     * Method to execute the given command.
     *
     * @param t The TaskList loaded.
     * @param ui The Ui object running the program.
     * @param storage The Storage object handling the loading and saving.
     * @throws DukeException Thrown in the event of input format errors.
     */
    @Override
    public String execute(TaskList t, Ui ui, Storage storage) {
        String listString = " These are your list of things to do:";
        if (t.getSize() == 0) {
            listString = "\n Your list is empty!";
        } else {
            for (int i = 0; i < t.getSize(); i++) {
                int count = i + 1;
                listString += "\n " + count + ". " + t.get(i);
            }
        }
        return ui.textFrame(listString);
    }

    /**
     * Method to determine if the program should continue running.
     *
     * @return Always returns true.
     */
    @Override
    public boolean isRunning() {
        return true;
    }
}
