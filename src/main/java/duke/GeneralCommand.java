package duke;

import java.io.IOException;

/**
 * All commands should implement this interface.
 */
public interface GeneralCommand {
    /**
     * Executes a general command.
     *
     * @return Returns String to be printed in GUI.
     * @throws IOException If an input or output operation is failed or interpreted.
     * @throws DeleteException If Delete command is incomplete.
     * @throws UndoException If Undo command is incomplete.
     * @throws CloneNotSupportedException If Cloning fails.
     */
    String execute() throws IOException, DeleteException, UndoException, CloneNotSupportedException, DukeException;
}
