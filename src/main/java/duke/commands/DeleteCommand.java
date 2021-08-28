package duke.commands;

import duke.data.TaskList;
import duke.data.exception.InvalidIndexException;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Encapsulates the Delete command's operations
 */
public class DeleteCommand extends Command {
    /** Index of task to delete*/
    private final int index;

    /**
     * Constructor for DeleteCommand
     * @param rest the user input after the command
     */
    public DeleteCommand(String rest) {
        this.index = Integer.parseInt(rest.strip());
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        Task t = tasks.get(index - 1);
        storage.saveData(tasks.delete(index - 1));
        ui.print(String.format(
                "Noted. I've removed this task:\n %s\nNow you have %d task(s) in the list",
                t.toString(),
                tasks.getCount()));
    }
}
