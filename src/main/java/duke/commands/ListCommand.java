package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * The {@code ListCommand} class extends from {@code Command}, which
 * lists all {@code Task}s in the {@code TaskList}.
 */
public class ListCommand extends Command {

    public ListCommand() { }

    /**
     * Returns the list of {@code Tasks} in {@code Augury}.
     *
     * @return {@code String} of {@code Tasks} in {@code Augury}.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.toString();
    }
}
