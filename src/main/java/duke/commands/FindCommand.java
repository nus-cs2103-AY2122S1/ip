package duke.commands;

import duke.exceptions.InvalidActionException;
import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * The {@code FindCommand} class extends from {@code Command}, which
 * finds {@code Task}s from the {@code TaskList} that match a user query.
 */
public class FindCommand extends Command {

    private final String[] args;

    /**
     * Initializes a {@code FindCommand} with user-supplied {@code String[] arguments}.
     *
     * @param arguments User-supplied search query (expected format: first index of array contains a
     *                  search query {@code String} ["shopping"])
     */
    public FindCommand(String[] arguments) {
        this.args = arguments;
    }

    /**
     * Executes the {@code FindCommand}, which searches the {@code TaskList}
     * for {@code Task}s that match the user-provided query.
     *
     * @param tasks {@code TaskList} that the Command operates on
     * @return {@code String} containing results of search.
     * @throws InvalidActionException if no search query is provided.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws InvalidActionException {
        String query = this.args[0];
        if (query.equals("find")) {
            // bug: for (valid) command `find find`, this exception is thrown.
            throw new InvalidActionException("Please enter the search string you want to find.");
        }
        return tasks.findAndAnnounce(query);
    }
}
