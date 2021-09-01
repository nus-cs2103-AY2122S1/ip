package duke.command;

import duke.exception.DukeException;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Command that contains details when user inputs Bye
 */
public class ByeCommand extends Command{
    /**
     * Basic Constructor
     *
     * @param storage Storage object to save
     * @param taskList Tasklist to add task to
     * @param ui Ui to display msg
     */
    public ByeCommand(Storage storage, TaskList taskList,Ui ui){
        super(storage, taskList, ui);
    }

    @Override
    public boolean exec() throws DukeException {
        ui.goodbye();
        return false;
    }
}
