package duke.commands;

import duke.exceptions.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * Represents exit command
 */
public class ByeCommand extends Command {

    /**
     * Creates bye command
     * @throws DukeException if user input is invalid
     */
    public ByeCommand() throws DukeException {
        super("");
    }

    /**
     * Executes for bye
     * @param taskList The object that holds a list of Task
     * @param ui The object responsible for updating Ui response
     * @param storage The object responsible to save/load list of task to/from hard disk
     * @return string to be printed out to user
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        //Storage
        storage.save(taskList);

        //Ui
        return ui.showBye();
    }

    /**
     * Check if command is exit command
     * @return true if is exit command
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
