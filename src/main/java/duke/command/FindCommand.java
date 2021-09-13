package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * Represents a Command to find a task from a given keyword.
 */
public class FindCommand extends Command {

    private String query;
    private String reply;

    /**
     * The constructor for a FindCommand object.
     * @param query The given search query to find a task with.
     */
    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Executes the Command to find tasks that contain the given query.
     *
     * @param tasks The given TaskList to be updated.
     * @param storage The given Storage to save changes to.
     * @return The response to the user.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        String results = "Here are the matching tasks in your list:";
        boolean hasMatches = false;
        for (int i = 0; i < tasks.getSize(); i++) {
            String taskDesc = tasks.getTask(i).getDescription();

            if (!taskDesc.contains(query)) {
                continue;
            }

            results += System.lineSeparator() + " " + (i + 1) + "." + tasks.getTask(i);
            hasMatches = true;
        }

        if (hasMatches) {
            reply = results;
            return reply;
        } else {
            reply = "No matches found.";
            return reply;
        }
    }
}
