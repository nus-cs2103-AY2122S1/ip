package duke.command;

import duke.exceptions.DukeException;
import duke.taskTypes.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class DoneCommand extends Command{

    private final String chosenList;

    public DoneCommand(Storage storage, TaskList taskList, Ui ui, String chosenList) {
        super(storage, taskList, ui);
        this.chosenList = chosenList;
    }

    @Override
    public boolean exec() throws DukeException {
        Task doneTask = taskList.done(chosenList);
        ui.doneMsg(doneTask);
        storage.saveUpdate(taskList);
        return true;
    }

}
