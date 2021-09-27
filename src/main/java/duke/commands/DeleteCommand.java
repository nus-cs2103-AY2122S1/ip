package duke.commands;

import duke.data.TaskList;
import duke.data.exception.InvalidIndexException;
import duke.data.exception.InvalidIndexFormatException;
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
     *
     * @param rest the user input after the command
     */
    public DeleteCommand(String rest) throws InvalidIndexFormatException {
        try {
            this.index = Integer.parseInt(rest.strip());
        } catch (NumberFormatException e) {
            throw new InvalidIndexFormatException();
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        int taskIndex = index - 1; //User input indexed from 1
        Task t = tasks.get(taskIndex);
        storage.saveData(tasks.delete(taskIndex));
        return ui.print(String.format(
                "Noted. I've removed this task:\n %s\nNow you have %d task(s) in the list",
                t.toString(),
                tasks.getCount()));
    }
}
