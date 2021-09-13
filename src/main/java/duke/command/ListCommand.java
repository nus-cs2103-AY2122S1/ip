package duke.command;

import duke.Storage;
import duke.task.TaskList;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.listTasks();
    }
}
