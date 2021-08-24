package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;

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
