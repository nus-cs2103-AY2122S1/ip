package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * Represents an exit command.
 */
public class ExitCommand extends Command {

    /**
     * Returns the result of the execution of the exit command.
     * @param tasks List of tasks the user has added.
     * @return Result of the execution of the exit command.
     */
    @Override
    public String execute(TaskList tasks) {
        return Ui.bye();
    }
}
