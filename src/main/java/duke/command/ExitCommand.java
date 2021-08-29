package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents an exit user command.
 * Inherits from command class.
 */
public class ExitCommand extends Command {
    /**
     * Constructs ExitCommand object.
     *
     * @param cmd task command.
     */
    public ExitCommand(String cmd) {
        super(cmd);
    }

    /**
     * Executes command.
     * Does nothing and display exiting message.
     *
     * @param tasks list of tasks within chat bot.
     * @param ui user interface of chat bot.
     * @return chat bot response message.
     * @param storage file directory manager.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showBye();
    }

    /**
     * Returns if command exits program.
     *
     * @return if command exists program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
