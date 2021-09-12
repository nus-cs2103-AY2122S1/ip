package duke.commands;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Archives all present tasks and removes all tasks from present task list.
 */
public class ArchiveCommand extends Command {
    public ArchiveCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.archive(tasks);
        String output = "Alright! I archived all your tasks: \n"
                + ui.tasklistToString(tasks);
        setCommandOutput(output);

        tasks.clear();
        storage.write(new TaskList());
    }
}
