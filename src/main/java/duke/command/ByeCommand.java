package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * Contains the executables when the user uses the 'bye' command.
 *
 * @author Benjamin Lui
 */
public class ByeCommand extends Command {
    /**
     * Executes the bye command.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        storage.save(taskList);
        return ui.showBye();

    }
}
