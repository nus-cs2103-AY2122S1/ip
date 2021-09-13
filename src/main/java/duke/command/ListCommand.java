package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

public class ListCommand extends Command {
    private static final String MESSAGE_LIST = "Here is the list of your tasks: ";

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return MESSAGE_LIST + "\n" + tasks.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
