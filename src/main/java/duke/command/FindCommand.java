package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Utils;

public class FindCommand extends Command {

    public FindCommand(String userCommand, String userArgument) {
        super(userCommand, userArgument);
    }

    public String execute(TaskList tasks, Storage storage) {
        TaskList searchedTasks = tasks.searchTasks(userArgument);
        if (searchedTasks.isEmpty()) {
            return "No matching tasks.";
        }
        return "Here are the matching tasks.\n" + Utils.showTasks(searchedTasks);
    }

}
