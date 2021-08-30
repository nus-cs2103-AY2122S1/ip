package duke.commands;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Marks a task as complete in the list.
 */
public class CompleteCommand extends Command {
    private int index;

    public CompleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index > tasks.size() - 1) {
            throw DukeException.invalidIndex();
        } else {
            tasks.get(index).markAsDone();
            storage.write(tasks);

            String output = "Awesome! I marked this as done:\n"
                    + tasks.get(index).toString() + "\n";
            setCommandOutput(output);
        }
    }
}
