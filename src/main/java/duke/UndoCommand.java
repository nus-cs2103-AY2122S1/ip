package duke;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;

import java.util.ArrayList;

public class UndoCommand extends Command {
    ArrayList<Command> commandHistory;
    Command currentCommand;

    public UndoCommand(TaskList tasks, ArrayList<Command> commandHistory) {
        super(tasks);
        this.commandHistory = commandHistory;
    }

    @Override
    public String run() throws DukeException {
        if (commandHistory == null) {
            throw new InvalidCommandException();
        }

        ArrayList<Command> updatedCommandHistory = commandHistory;
        for (int i = commandHistory.size() - 1; i > 0; i--) {
            Command command = commandHistory.get(i);
            updatedCommandHistory.remove(command);
            if (command instanceof UndoableCommand) {
                currentCommand = command;
                break;
            }
        }
        if (currentCommand == null) {
            return "Nothing to undo!";
        } else {
            UndoableCommand cur = (UndoableCommand) currentCommand;
            //safe to typecast since currentCommand is only instantiated if it is undoable
            commandHistory = updatedCommandHistory;
            return cur.undo();
        }
    }
}
