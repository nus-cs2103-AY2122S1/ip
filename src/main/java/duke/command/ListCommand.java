package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a list user command.
 * Inherits from command class.
 */
public class ListCommand extends Command {
    /**
     * Constructs ListCommand object.
     *
     * @param cmd task command.
     */
    public ListCommand(String cmd) {
        super(cmd);
    }

    /**
     * Executes command.
     * Displays all tasks in TaskList
     *
     * @param tasks list of tasks within chat bot.
     * @param ui user interface of chat bot.
     * @param storage file directory manager.
     * @return chat bot response message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks.list());
    }

    /**
     * Returns if command exits program.
     *
     * @return if command exists program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
