package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a find / query user command.
 * Inherits from command class.
 */
public class FindCommand extends Command {
    /**
     * Constructs FindCommand object.
     *
     * @param cmd task search word to find.
     */
    public FindCommand(String cmd) {
        super(cmd);
    }

    /**
     * Executes command.
     * Finds all task which has search term.
     *
     * @param tasks list of tasks within chat bot.
     * @param ui user interface of chat bot.
     * @return chat bot response message.
     * @param storage file directory manager.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showFind(tasks.find(getLine()));
    }

    /**
     * Returns if command Finds program.
     *
     * @return if command exists program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
