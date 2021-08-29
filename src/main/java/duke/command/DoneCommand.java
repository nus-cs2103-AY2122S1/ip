package duke.command;

import duke.task.TaskList;

import duke.util.Ui;
import duke.util.Storage;

import java.io.IOException;

/**
 * A class that represents the command when the user types in 'done'.
 */
public class DoneCommand extends Command {

    /**
     * Creates a DoneCommand, which relies on user inputs to function.
     *
     * @param input A string containing the user's input.
     */
    public DoneCommand(String input) {
        super(input);
    }

    /**
     * Marks a specified task as done and saves the new task list to the save file.
     *
     * @param tasks   The current list of tasks from the user.
     * @param ui      An object that handles all UI related functionality. (e.g. printing)
     * @param storage An object that handles all save/load related functionality.
     * @return A task list with the specified task marked as done.
     * @throws IOException If an error occurs during the save operation.
     */
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        TaskList newTasks = tasks.markTask(input, ui);
        storage.saveTasksToFile(newTasks);
        return newTasks;
    }
}
