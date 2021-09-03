package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * The {@code ExitCommand} class extends from {@code Command}, which
 * terminates {@code Augury}.
 */
public class ExitCommand extends Command {

    public ExitCommand() { }

    /**
     * Signals to exit from {@code Augury}.
     *
     * @return {@code String} which signals to {@code Augury} to terminate.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "The readiness is all.";
    }
}
