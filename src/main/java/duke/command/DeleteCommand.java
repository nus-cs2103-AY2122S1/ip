package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.Parser;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {
    private static final String COMMAND_WORD = "delete";

    /**
     * Returns the command word for a delete command.
     *
     * @return "delete" representing a delete command.
     */
    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    /**
     * Deletes the task specified by the user.
     *
     * @param duke   Duke instance that the command is called from.
     * @param parser Parser with the user's input
     * @throws DukeException If input is invalid.
     */
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
