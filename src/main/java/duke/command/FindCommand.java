package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

public class FindCommand extends Command {

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
     * Finds tasks from the tasklist that contain the query string
     * and returns the response message.
     *
     * @param taskList The TaskList of Duke.
     * @param storage The Storage of Duke.
     * @return Response string.
     * @throws DukeException  If error occur during execution.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        String message = "Here are the matching tasks in your list:\n";
        return message + taskList.checkForQuery(query);
    }
}
