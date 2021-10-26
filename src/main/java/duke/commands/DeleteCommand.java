package duke.commands;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Deletes a task to the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index > tasks.size() - 1) {
            throw DukeException.invalidIndex();
        } else {
            String output = "This item will be removed:\n"
                    + tasks.get(index).toString() + "\n"
                    + String.format("You have %d task(s) at the moment!\n", tasks.size() - 1);

            tasks.remove(index);
            storage.write(tasks);

            setCommandOutput(output);
        }
    }
}
