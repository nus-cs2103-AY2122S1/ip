package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;

public class RestoreCommand extends Command {

    public static final String COMMAND_WORD = "restore";

    public RestoreCommand() {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.restore();
        storage.save(tasks);
        ui.printMsg("This task list was restored:\n" + tasks.listTaskArr());
    }
}
