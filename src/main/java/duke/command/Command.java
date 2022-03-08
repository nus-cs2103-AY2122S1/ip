package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskHandler;
import duke.util.Ui;

/**
 * This class encapsulates all commands that Duke can handle.
 *
 * @author Teo Sin Yee
 * @version CS2103T AY21/22 Semester 1
 */
public abstract class Command {
    public abstract String execute(TaskHandler taskHandler, Storage storage, Ui ui) throws DukeException;
}
