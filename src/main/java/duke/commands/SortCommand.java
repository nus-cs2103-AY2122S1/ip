package duke.commands;

import duke.tasks.TaskList;
import duke.utils.Storage;

public class SortCommand extends Command{
    private static final String SORT_MSG = "Here are your tasks sorted:";

    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.sort();

        String message = SORT_MSG + "\n" + tasks.toString();

        return message;
    }
}
