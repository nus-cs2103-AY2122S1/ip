package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.Parser;
import duke.task.Task;
import duke.ui.Ui;

public class DoneCommand extends Command {
    private static final String COMMAND_WORD = "done";

    /**
     * Returns the command word for a done command.
     *
     * @return "done" representing a done command.
     */
    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    /**
     * Marks the task specified by the user as done.
     *
     * @param duke   Duke instance that the command is called from.
     * @param parser Parser with the user's input
     * @return Output to be displayed in GUI.
     * @throws DukeException If input is invalid.
     */
    @Override
    public String run(Duke duke, Parser parser) throws DukeException {
        int index = parser.getNumber();
        if (index < 0 || index > duke.getList().size()) {
            throw new DukeException("Invalid number.");
        }
        Task task = duke.getList().setDone(index);
        return Ui.doneMessage(task);
    }
}
