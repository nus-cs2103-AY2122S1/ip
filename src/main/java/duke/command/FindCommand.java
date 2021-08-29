package duke.command;

import duke.Ui;
import duke.task.TaskList;

/**
 * The type Find command that finds a user-inputted substring in the given list of tasks.
 */
public class FindCommand extends Command {

    /** Substring to search for in description of tasks */
    private final String substring;
    /** List of tasks to search from */
    private final TaskList tasks;

    /**
     * Instantiates a new Find command.
     *
     * @param userInput the user-inputted string.
     * @param tasks     the list of tasks to search from.
     */
    public FindCommand(String userInput, TaskList tasks) {
        this.substring = userInput.split(" ", 2)[1];
        this.tasks = tasks;
    }

    @Override
    public void execute() {
        String result = "Here are the matching tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(this.substring)) {
                result += "\n" + i + "." + tasks.get(i).toString();
            }
        }
        System.out.println(Ui.tabAndFormat(result));
    }
}
