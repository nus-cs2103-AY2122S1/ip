package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";
    public static final String EXIT_MSG = "Bye. Hope to see you again soon!";

    public ExitCommand() {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printMsg(EXIT_MSG);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
