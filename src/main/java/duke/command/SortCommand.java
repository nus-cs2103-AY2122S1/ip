package duke.command;

import duke.exception.BadInputFormatException;
import duke.util.Storage;
import duke.util.TaskList;

public class SortCommand extends Command {
    /**
     * Sort command factory method.
     *
     * @param content The user's input content.
     * @return A SortCommand object.
     * @throws BadInputFormatException If the user's input contains more than a singular "sort" keyword.
     */
    public static SortCommand of(String content) throws BadInputFormatException {
        if (content.trim().length() > 1) { // Guard clause
            throw new BadInputFormatException();
        }
        return new SortCommand();
    }

    /**
     * Prints a list of Tasks sorted by date.
     *
     * @param tasks The list of tasks in the program.
     * @param storage The storage utility.
     * @return The response of the command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        if (tasks.getSize() < 1) {
            return formatOutput("No tasks yet!");
        }
        return formatOutput("Here are your tasks ordered by date:", tasks.sort().toStringArray());
    }

    /**
     * Tests if a command is exit.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
