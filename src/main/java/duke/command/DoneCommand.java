package Duke.Command;

import Duke.Duke;
import Duke.DukeException;
import Duke.Parser;
import Duke.Task.Task;

public class DoneCommand extends Command {
    private static final String COMMAND_WORD = "done";
    private static final String DONE_MESSAGE = "Nice! I've marked this task as done:\n";

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
        Duke.formatAndPrint(DONE_MESSAGE + task);
    }
}
