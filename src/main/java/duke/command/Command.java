package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a user command.
 */
public abstract class Command {
    private final String line;

    /**
     * Constructs Command object.
     *
     * @param cmd task command.
     */
    public Command(String cmd) {
        line = cmd;
    }

    /**
     * Executes command.
     *
     * @param tasks list of tasks within chat bot.
     * @param ui user interface of chat bot.
     * @param storage file directory manager.
     * @return chat bot response message.
     * @throws DukeException If errors occur within list.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.save(tasks.getTasks());
        return "";
    }

    public String getLine() {
        return line;
    }

    /**
     * Returns if command exits program.
     */
    public abstract boolean isExit();
}
