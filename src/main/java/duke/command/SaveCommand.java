package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

import java.io.IOException;

/**
 * The SaveCommand class represents the save command for writing the TaskList to a data file.
 */
public class SaveCommand extends Command {

    /**
     * Method which executes the saving of Task in the TaskList to a data file.
     *
     * @param tasks The list of Task.
     * @param ui The Ui objects that handles input from user and output to user.
     * @param storage The Storage object that handles reading/writing of data.
     * @throws DukeException On input error during writing to file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            storage.writeCurrentData(tasks.getTasks());
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
        ui.displaySaveMessage();
    }
}
