package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.Parser;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {
    private static final String COMMAND_WORD = "delete";

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public void run(Duke duke, Parser parser) throws DukeException {
        // Retrieve value inputted by user and subtract 1 to get the index in the array.
        int index = parser.getNumber();
        // Error handling: negative number or number more than list length.
        if (index < 0 || index >= duke.getList().size()) {
            throw new DukeException("Invalid number.");
        }
        Task task = duke.getList().remove(index);
        Ui.deleteMessage(task, duke.getList().size());
    }
}
