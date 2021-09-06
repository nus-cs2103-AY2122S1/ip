package duke.commands;

import duke.exceptions.UserInputError;
import duke.tasks.TaskList;
import duke.util.Ui;

/**
 * Command class that represents each user command.
 */
public abstract class Command {
    /**
     * Executes the user's command.
     * @param tasks Current tasklist.
     * @param ui User interface of Duke.
     * @return String output result of user's command.
     * @throws UserInputError
     */
    public abstract String execute(TaskList tasks, Ui ui) throws UserInputError;
}
