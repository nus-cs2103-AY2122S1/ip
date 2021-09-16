package duke.commands;

import duke.exceptions.UserInputError;
import duke.tasks.TaskList;
import duke.util.Ui;

/**
 * Command class that represents each user command.
 */
public abstract class Command {

    /**
     * Run the user's command and update the database accordingly.
     * Throws a UserInputError if run fails.
     *
     * @param tasks Current taskList.
     * @param ui User interface of Duke.
     * @return String output result of user's command.
     * @throws UserInputError Throws error message when input is of wrong format and unrecognised by Duke.
     */
    public abstract String execute(TaskList tasks, Ui ui) throws UserInputError;
}
