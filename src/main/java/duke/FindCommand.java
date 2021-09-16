package duke;

import java.util.ArrayList;

/**
 * Encapsulates a command to find tasks that match a given string.
 */
public class FindCommand implements Command {
    private String queryString;

    /**
     * Creates the command to find matching tasks.
     *
     * @param query the string to be matched with.
     */
    public FindCommand(String query) {
        this.queryString = query;
    }

    /**
     * Carries out the action that this command represents.
     *
     * @param tasks the current list of tasks.
     * @param ui user interface interacts with the user.
     * @param storage custodian of reading and writing save files.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> filteredTasks = tasks.findMatches(queryString);
        String response = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < filteredTasks.size(); i++) {
            response += (i + 1) + "." + filteredTasks.get(i) + "\n";
        }
        return response;

    }

    /**
     * Identifies if this command is an exit command.
     *
     * @return whether this command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
