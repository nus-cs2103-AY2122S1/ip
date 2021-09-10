package duke.command;

import duke.gui.Ui;
import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Storage;

/**
 * Command to add tasks.
 */
public class AddCommand extends Command {
    private final String input;

    /**
     * Returns new AddCommand object.
     * @param input The user input.
     */
    public AddCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command to add a task.
     * @param tasks The list of tasks.
     * @param ui The Ui object.
     * @param storage The storage object.
     * @throws DukeException If task description is invalid.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return tasks.addTask(input);
    }

    /**
     * If the command is the exit command.
     * @return False.
     */
    public boolean isExit() {
        return false;
    }
}
