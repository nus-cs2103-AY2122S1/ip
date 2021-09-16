package duke;

import java.util.ArrayList;

/**
 * Encapsulates a command by the user to find a task by searching for a keyword.
 */
public class FindCommand extends Command {
    private String query;

    /**
     * Constructor for a find command.
     *
     * @param query The query input by the user, i.e. the keyword that the user intends to search using.
     */
    public FindCommand(String query) {
        super();
        this.query = query;
    }

    /**
     * Executes the find command.
     *
     * @param tasks The list of tasks in the to-do-list.
     * @param ui The instance of ui that handles interactions with the user.
     * @param storage The instance of storage that handles loading tasks from and saving tasks to a text file.
     * @return A message describing the result of the execution.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (query.isBlank()) {
            throw new DukeException("OOPS!!! You cannot search using an empty keyword.");
        }

        // Get the list of matching tasks
        ArrayList<Task> matchingTasks = tasks.find(query);

        // Create the response to be displayed
        String output = "Here are the matching tasks in your list: \n";
        for (int i = 0; i < matchingTasks.size(); i++) {
            output += String.valueOf(i + 1) + ". " + matchingTasks.get(i) + "\n";
        }

        // Return a description of the execution result
        return output;
    }
}
