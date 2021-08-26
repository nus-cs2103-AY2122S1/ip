package duke.command;

import duke.TaskList;
import duke.DukeException;
import duke.Storage;
import duke.Ui;

public class DisplayCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public DisplayCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.display();
    }
}