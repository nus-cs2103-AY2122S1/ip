package duke.commands;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Adds a task to the task list.
 */
public class AddCommand extends Command {
    private Task toAdd;

    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(toAdd);
        storage.write(tasks);

        String output = "Alright! I added this task: \n" + toAdd.toString() + "\n"
                + String.format("You have %d task(s) at the moment!\n", tasks.size());
        setCommandOutput(output);
    }
}
