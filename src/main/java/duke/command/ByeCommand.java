package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents the command when the user wants to exit from the program.
 */
public class ByeCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    /**
     * Executes the command to exit the program.
     *
     * @param tasks The user's task list.
     * @param storage Storage object used to save the task list to the data file.
     * @return A String informing the user that the program has been exited.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return Ui.getGoodbyeMessage();
    }
}
