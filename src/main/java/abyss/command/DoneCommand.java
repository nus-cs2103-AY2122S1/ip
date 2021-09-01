package abyss.command;

import abyss.Abyss;
import abyss.exception.InvalidCommandException;

/**
 * Represents a command to mark a task as done in the list of tasks.
 */
public class DoneCommand implements Command {
    private static final String DONE_REGEX = "^\\d*$";

    private int index;

    protected DoneCommand(String content) throws InvalidCommandException {
        if (!content.matches(DONE_REGEX)) {
            throw new InvalidCommandException("Command 'done' should be followed by "
                                                  + "the index of the task piece.");
        }

        if (Abyss.getNumberOfTasks() == 0) {
            throw new InvalidCommandException("The Abyss is empty.");
        }


        int i = Integer.parseInt(content);
        if (i < 1 || i > Abyss.getNumberOfTasks()) {
            throw new InvalidCommandException("Index should be positive and not more than "
                                                  + Abyss.getNumberOfTasks());
        }

        this.index = i;
    }

    public int getIndex() {
        return this.index;
    }
}
