package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class ListCommand  extends Command{
    public ListCommand(Storage storage, TaskList taskList, Ui ui){
        super(storage, taskList, ui);
    }
    @Override
    public boolean exec() throws DukeException {
        String[] listOutput = taskList.getList();
        ui.listMsg(listOutput, taskList);
        return true;
    }
}
