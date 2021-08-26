package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates an Exit commands that deals with exiting the program.
 *
 * @author Zhi Bin
 * @version Duke Level 9
 */
public class Exit extends DukeCommand {

    /**
     * Constructor for an Exit Command.
     *
     * @param ui      The Ui handler that handles the printing of message with respect to the command.
     * @param storage The storage handler that handles saving or loading data to local directory.
     * @param list    The TaskList handler that handles operation related to task.
     */
    public Exit(Ui ui, Storage storage, TaskList list) {
        super(ui, storage, list);
    }

    /**
     * Executes the exit command. Prints farewellMessage
     * and save the task list onto the local directory.
     */
    @Override
    public void execute() {
        ui.farewellMessage();
        storage.save(list.getList());
    }

    /**
     * Checks if the command is an Exit command.
     * Returns true.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
