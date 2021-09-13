package duke.task.command;

import java.io.IOException;

import duke.DukeConstants;
import duke.DukeException;
import duke.Undo;

public class UndoCommand extends Command {

    private Undo undo;
    /**
     * Executes the command.
     *
     * @return The output message of the command.
     * @throws DukeException Thrown when a duke exception happens.
     */
    @Override
    public String execute() throws DukeException, IOException {
        if (!DukeConstants.isUndoable) {
            return "Please input an undo-able task."
                    + "\nCommands 'delete', 'todo', 'event', 'deadline' and 'done' can be undone";
        }
        DukeConstants.isUndoable = false;
        return undo.undo();
    }
}
