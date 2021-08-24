package Duke.Command;

import Duke.Duke;
import Duke.DukeException;
import Duke.Parser;
import Duke.Task.Task;

public class DeleteCommand extends Command {
    private static final String COMMAND_WORD = "delete";
    public static final String REMOVE_MESSAGE = "Noted. I've removed this task:\n%s\nNow you have %d %s in your list.";

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
        Duke.formatAndPrint(String.format(REMOVE_MESSAGE,
                task,
                duke.getList().size(),
                duke.getList().size() == 1 ? "task" : "tasks"));
    }
}
