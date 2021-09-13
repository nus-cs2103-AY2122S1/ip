package duke.commands;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Encapsulates the add command's operations
 */
public class AddCommand extends Command {
    /** Task to add */
    private Task t;

    /**
     * Constructor for AddCommand
     *
     * @param t the task to be added
     */
    public AddCommand(Task t) {
        this.t = t;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        boolean isDuplicate = tasks.contains(this.t);
        if (isDuplicate) {
            return ui.print(String.format("You already have this task in the list:\n %s", this.t.toString()));
        }

        storage.saveData(tasks.add(this.t));
        return ui.print(String.format(
                "Got it. I've added this task:\n %s\nNow you have %d task(s) in the list",
                this.t.toString(),
                tasks.getCount()));
    }
}
