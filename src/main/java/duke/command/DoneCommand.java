package duke.command;

import duke.exception.DukeException;

import duke.taskTypes.Task;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class DoneCommand extends Command {

    private final String chosenList;

    /**
     * Basic Constructor
     *
     * @param storage Storage object to save
     * @param taskList Tasklist to add task to
     * @param ui Ui to display msg
     * @param chosenList contains the string that describes which task to be declared done
     */
    public DoneCommand(Storage storage, TaskList taskList, Ui ui, String chosenList) {
        super(storage, taskList, ui);
        this.chosenList = chosenList;
    }

    /**
     * Executes a set of instructions
     *
     * @return boolean To relay whether to continue the project
     * @throws DukeException
     */
    @Override
    public boolean exec() throws DukeException {
        Task doneTask = taskList.done(chosenList);
        ui.doneMsg(doneTask);
        storage.saveUpdate(taskList);
        return true;
    }

}
