package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.TaskList;

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    private String key;

    public FindCommand(String key) {
        this.key = key;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return tasks.findTasks(key);
    }
}
