package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.Parser;
import duke.Ui;
import duke.task.Task;

public class DoneCommand extends Command {
    private static final String COMMAND_WORD = "done";

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public void run(Duke duke, Parser parser) throws DukeException {
        int index = parser.getNumber();
        if (index < 0 || index > duke.getList().size()) {
            throw new DukeException("Invalid number.");
        }
        Task task = duke.getList().setDone(index);
        Ui.doneMessage(task);
    }
}
