package duke.commands;

import duke.tasks.TaskList;
import duke.utils.Storage;

public class ListCommand extends Command {

    private static final String TASK_MSG = "Here are your tasks:";

    public String execute(TaskList tasks, Storage storage) {
        String message = TASK_MSG + "\n" + tasks.toString();

        return message;
    }

    @Override
    public String toString() {
        return TASK_MSG;
    }
}
