package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class WelcomeCommand extends Command {
    /**
     * The command word that identifies an ExitCommand instance.
     */
    public static final String COMMAND_WORD = "hello";

    /**
     * Returns true if the command is an exit command, return false otherwise.
     *
     * @return True if the command is an exit command, return false otherwise.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the respective command given and displays the result or error message(s).
     *
     * @param tasks TaskList that stores the list of tasks.
     * @param ui Ui instance that prints various messages.
     * @param storage Storage instance that reads and writes the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printWelcomeMessage();
    }
}
