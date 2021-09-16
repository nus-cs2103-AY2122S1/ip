package duke.command;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Utils;

public class FindCommand extends Command {

    /**
     * Constructor for a FindCommand
     * @param userCommand The command the user gives
     * @param userArgument The argument (rest of the String after the command)
     */
    public FindCommand(String userCommand, String userArgument) {
        super(userCommand, userArgument);
    }

    /**
     * Finds the tasks that match the user's input
     * @return A list of tasks that match the user's input
     */
    public String execute(TaskList tasks, Storage storage) {

        assert userArgument != null;

        TaskList searchedTasks = tasks.searchTasks(userArgument);
        if (searchedTasks.isEmpty()) {
            return "No matching tasks.";
        }
        return "Here are the matching tasks.\n" + Utils.showTasks(searchedTasks);
    }

}
