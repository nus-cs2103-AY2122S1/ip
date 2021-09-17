package duke.commands;

import duke.utils.TaskList;

/**
 * Encapsulates a command to find tasks matching a search query.
 */
public class Find extends Command {
    private String query;

    /**
     * Creates a command to find tasks matching the user's search
     * query.
     *
     * @param query The user's search query.
     */
    public Find(String query) {
        this.query = query;
    }

    @Override
    public TaskList execute(TaskList taskList) {
        return taskList.search(query);
    }
}
