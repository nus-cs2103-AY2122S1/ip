package duke.commands;

import duke.Augury;
import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * The {@code HelpCommand} class extends from {@code Command}, which
 * returns a help message for the user.
 */
public class HelpCommand extends Command {

    public HelpCommand() { }

    /**
     * Returns a help message for the user.
     *
     * @return {@code String} help message for the user.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return Augury.HELP_MESSAGE;
    }
}
