package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Utils;

public class ListCommand extends Command {

    public ListCommand(String userCommand, String userArgument) {
        super(userCommand, userArgument);
    }

    public String execute(TaskList tasks, Storage storage) {
        if (tasks.isEmpty()) {
            return "Currently no tasks!";
        }
        return "Here are your tasks:\n" + Utils.showTasks(tasks);
    }
}
