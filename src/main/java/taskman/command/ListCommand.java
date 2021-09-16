package taskman.command;

import taskman.exception.DukeException;
import taskman.util.Storage;
import taskman.util.TaskList;


/**
 * Command that list out all the task based on input order
 */
public class ListCommand  extends Command{

    /**
     * Basic Constructor
     *
     * @param storage StorageTxt object to save
     * @param taskList Tasklist to add task to
     */
    public ListCommand(Storage storage, TaskList taskList){
        super(storage, taskList, false);
    }

    /**
     * Executes a set of instructions to get a list of all the task in the list
     *
     * @return String list of all the task
     * @throws DukeException
     */
    @Override
    public String exec() throws DukeException {
        return taskList.getList();
    }
}
