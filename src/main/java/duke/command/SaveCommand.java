package duke.command;
import java.io.IOException;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;


/**
 * The SaveCommand class represents the save command for writing the TaskList to a data file.
 */
public class SaveCommand extends Command {

    /**
     * Executes the save command which saves the current TaskList to the datafile.
     * @param tasks The list of Task.
     * @param ui The Ui objects that handles input from user and output to user.
     * @param storage The Storage object that handles reading/writing of data.
     * @return String The message to be displayed on successful save.
     * @throws DukeException Unused.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            storage.writeCurrentData(tasks.getTasks());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ui.displaySaveMessage();
    }
}
