package Command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import tasks.ToDo;

public class CreateNewToDoCommand extends Command {

    public CreateNewToDoCommand(String message) {
        super(message);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(new ToDo(super.getExtraInput()));
    }
}
