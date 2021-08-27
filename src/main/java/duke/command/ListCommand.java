package duke.command;

import duke.exception.DukeException;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;


public class ListCommand  extends Command{

    /**
     * Basic Constructor
     *
     * @param storage Storage object to save
     * @param taskList Tasklist to add task to
     * @param ui Ui to display msg
     */
    public ListCommand(Storage storage, TaskList taskList, Ui ui){
        super(storage, taskList, ui);
    }

    /**
     * Executes a set of instructions
     *
     * @return boolean To relay whether to continue the project
     * @throws DukeException
     */
    @Override
    public boolean exec() throws DukeException {
        String[] listOutput = taskList.getList();
        ui.listMsg(listOutput, taskList);
        return true;
    }
}
