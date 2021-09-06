package duke.command;

import duke.task.TaskList;

/**
 * The type Find command that finds a user-inputted substring in the given list of tasks.
 */
public class FindCommand extends Command {

    /** Substring to search for in description of tasks. */
    private final String substring;
    /** List of tasks to search from. */
    private final TaskList tasks;

    /**
     * Instantiates a new Find command.
     *
     * @param substring the user-inputted substring to find.
     * @param tasks     the list of tasks to search from.
     */
    public FindCommand(String substring, TaskList tasks) {
        this.substring = substring;
        this.tasks = tasks;
    }

    @Override
    public String execute() {
        String result = "Here are the matching tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(this.substring)) {
                result += "\n" + i + "." + tasks.get(i).toString();
            }
        }
        return result;
    }
}
