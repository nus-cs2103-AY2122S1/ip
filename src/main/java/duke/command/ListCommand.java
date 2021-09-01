package duke.command;

import duke.exception.DukeException;

import duke.util.Storage;
import duke.util.TaskList;



public class ListCommand  extends Command{

    /**
     * Basic Constructor
     *
     * @param storage Storage object to save
     * @param taskList Tasklist to add task to
     * @param ui Ui to display msg
     */
    public ListCommand(Storage storage, TaskList taskList){
        super(storage, taskList, false);
    }

    /**
     * Executes a set of instructions
     *
     * @return boolean To relay whether to continue the project
     * @throws DukeException
     */
    @Override
    public String exec() throws DukeException {
        return taskList.getList();
    }
}
