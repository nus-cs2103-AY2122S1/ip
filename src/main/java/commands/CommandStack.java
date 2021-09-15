package commands;

import java.util.Stack;

/**
 * A stack that stores commands that extends UndoableCommand. This allows us
 * to maintain a history of commands so that we can undo them.
 */
public class CommandStack {

    /** The stack storing the commands */
    private final Stack<UndoableCommand> pastCommands = new Stack<>();

    /**
     * Adds a new command on the top of the command stack.
     *
     * @param command The UndoableCommand that is to be added.
     */
    public void addCommand(UndoableCommand command) {
        this.pastCommands.push(command);
    }

    /**
     * Returns the last command by popping it from the stack.
     *
     * @return the last command or null if the stack is empty.
     */
    public UndoableCommand getLastCommand() {
        if (this.pastCommands.empty()) {
            return null;
        }
        return this.pastCommands.pop();
    }
}
