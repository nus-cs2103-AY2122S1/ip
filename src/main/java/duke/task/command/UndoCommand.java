package duke.task.command;

import java.io.IOException;

import duke.DukeException;
import duke.Undo;

public class UndoCommand extends Command {

    private Undo undo;

    public UndoCommand() {
        undo = new Undo();
    }

    /**
     * Executes the command.
     *
     * @return The output message of the command.
     * @throws DukeException Thrown when a duke exception happens.
     */
    @Override
    public String execute() throws DukeException, IOException {
        return undo.undo();
    }
}
