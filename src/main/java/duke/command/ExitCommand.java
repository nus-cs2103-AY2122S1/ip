package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to exit the Duke program.
 *
 * @author Cheong Yee Ming
 * @version Duke Level-10
 */
public class ExitCommand extends Command {

    /**
     * Constructor for a ExitCommand.
     *
     * @param taskList Handles all operations regarding tasks.
     * @param storage  Save and load data from local directory.
     * @param ui       Prints message with respect to user input.
     */
    public ExitCommand(TaskList taskList, Storage storage, Ui ui) {
        super(taskList, storage, ui);
    }

    /**
     * Executes runCommand.
     * Prints a message to say good bye to user
     * and update local data file.
     *
     * @return String representation of farewell message
     */
    @Override
    public String runCommand() {
        storage.save(taskList.getList());
        return ui.guiBye();
    }
}
