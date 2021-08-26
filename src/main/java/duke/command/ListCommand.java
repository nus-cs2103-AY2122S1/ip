package duke.command;

import duke.date.Date;
import duke.exception.BadInputFormatException;
import duke.exception.InvalidDateException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

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
     * @param ui The UI object.
     * @param storage The storage utility.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() < 1) {
            ui.print("No tasks yet!");
            return;
        }
        if (date == null) {
            ui.print("Here are the tasks in your list:");
            ui.print(tasks.toStringArray());
            return;
        }
        ui.print("Here are the tasks happening on " + date + ":");
        ui.print(tasks.toStringArray(date));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
