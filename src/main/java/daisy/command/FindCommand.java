package daisy.command;

import daisy.task.Storage;
import daisy.task.TaskList;

/**
 * FindCommand class handles the 'find' command to list the tasks containing a query string.
 */
public class FindCommand extends Command {

    static final String FIND_HEADER = "Here are the matching tasks in your list:\n";
    private String query;

    /**
     * Constructs the FindCommand object.
     *
     * @param query String queried for the 'find' command.
     */
    public FindCommand (String query) {
        this.query = query;
    }

    /**
     * Finds tasks from the task list that contain the query string
     * and returns the response message.
     *
     * @param taskList The TaskList of Daisy.
     * @param storage The Storage of Daisy.
     * @return Response string.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return FIND_HEADER + taskList.checkForQuery(query);
    }
}
