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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printMsg(tasks.findTasks(key));
    }
}
