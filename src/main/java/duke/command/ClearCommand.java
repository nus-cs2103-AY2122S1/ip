package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";

    public ClearCommand() {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.clear();
        storage.save(tasks);
        ui.printMsg("All entries in the list of tasks have been removed. To undo, type restore");
    }
}
