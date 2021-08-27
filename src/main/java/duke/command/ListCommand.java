package duke.command;

import duke.date.Date;
import duke.exception.BadInputFormatException;
import duke.exception.InvalidDateException;
import duke.util.Storage;
import duke.util.TaskList;

/** Represents the "list" command. */
public class ListCommand extends Command {
    /** The Date object. */
    private Date date;

    /** Default ListCommand constructor. */
    public ListCommand() {
        super();
    }

    /**
     * ListCommand constructor.
     *
     * @param date The Date object.
     */
    private ListCommand(Date date) {
        this.date = date;
    }

    /**
     * ListCommand factory method.
     *
     * @param content The content of the user's input.
     * @return A ListCommand object.
     * @throws BadInputFormatException If the content input contains more than the "list" keyword and date.
     * @throws InvalidDateException If the date in the content input is not in the yyyy-MM-dd format.
     */
    public static ListCommand of(String content) throws BadInputFormatException, InvalidDateException {
        if (content.trim().length() < 1) {
            return new ListCommand();
        }
        return new ListCommand(Date.of(content));
    }

    /**
     * Prints the user's Tasks.
     *
     * @param tasks The list of tasks in the program.
     * @param storage The storage utility.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        if (tasks.size() < 1) {
            return outputFormatter("No tasks yet!");
        }
        if (date == null) {
            return outputFormatter("Here are the tasks in your list:", tasks.toStringArray());
        }
        return outputFormatter("Here are the tasks happening on " + date + ":", tasks.toStringArray(date));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
