package duke.commands;

import duke.data.TaskList;
import duke.data.exception.InvalidIndexException;
import duke.data.exception.InvalidIndexFormatException;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Encapsulates the DoneCommand and its operations
 */
public class DoneCommand extends Command {
    /** Index of the task to mark done */
    private final int index;

    /**
     * Constructor for DoneCommand
     *
     * @param rest the user input after the command
     */
    public DoneCommand(String rest) throws InvalidIndexFormatException {
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
        storage.saveData(tasks.markDone(taskIndex));
        return ui.print(String.format("Nice! I've marked this task as done:\n %s", t.toString()));
    }
}
