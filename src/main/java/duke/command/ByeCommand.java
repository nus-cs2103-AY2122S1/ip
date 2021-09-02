package duke.command;

import duke.exception.DukeException;

import duke.util.Storage;
import duke.util.TaskList;


/**
 * Command that contains details when user inputs Bye
 */
public class ByeCommand extends Command{
    /**
     * Basic Constructor
     *
     * @param storage Storage object to save
     * @param taskList Tasklist to add task to
     */
    public ByeCommand(Storage storage, TaskList taskList){
        super(storage, taskList, true);
    }

    @Override
    public String exec() throws DukeException {
        return "Bye. Hope to see you again soon!";
    }
}
