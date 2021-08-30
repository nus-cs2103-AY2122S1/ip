package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    private String key;

    public FindCommand(String key) {
        this.key = key;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return tasks.findTasks(key);
    }
}
