package duke;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;

public class UndoCommand extends Command {
    Command previousCommand;

    public UndoCommand(TaskList tasks, Command prev) {
        super(tasks);
        this.previousCommand = prev;
    }


    @Override
    public String run() throws DukeException {
        if (previousCommand == null) {
            throw new InvalidCommandException();
        }
        return previousCommand.undo();
    }

    @Override
    public String undo() {
        return "Cannot undo more!";
    }
}
