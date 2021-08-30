package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to exit the Duke program.
 *
 * @author Cheong Yee Ming
 * @version Duke A-JavaDoc
 */
public class ExitCommand extends Command {
    /**
     * Constructor for a DukeCommand.
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
     */
    @Override
    public void runCommand() {
        ui.bye();
        storage.save(taskList.getList());
    }
}
