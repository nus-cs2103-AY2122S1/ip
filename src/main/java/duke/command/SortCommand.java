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

    @Override
    public String execute(TaskList tasks, Storage storage) {
        if (tasks.getSize() < 1) {
            return formatOutput("No tasks yet!");
        }
        return formatOutput("Here are your tasks ordered by date:", tasks.sort().toStringArray());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
