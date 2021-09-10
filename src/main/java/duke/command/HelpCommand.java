package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class HelpCommand extends DukeCommand {
    /**
     * Constructor for a DukeCommand.
     *
     * @param ui      The Ui handler that handles the printing of message with respect to the command.
     * @param storage The storage handler that handles saving or loading data to local directory.
     * @param list    The TaskList handler that handles operation related to task.
     */
    public HelpCommand(Ui ui, Storage storage, TaskList list) {
        super(ui, storage, list);
    }

    /**
     * Executes the command.
     *
     * @return A message to be displayed on the GUI.
     * @throws DukeException When an error occurred.
     */
    @Override
    public String execute() throws DukeException {
        return ui.showHelpMessage();
    }
}
