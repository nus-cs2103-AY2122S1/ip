package duke.command;

import java.time.LocalDate;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * FilterCommand filters out task in task list by date.
 *
 * @author Chng Zi Hao
 */
public class FilterCommand extends Command {
    private LocalDate filterDate;

    /**
     * Constructor for FilterCommand.
     *
     * @param filterDate Specific date to be checked against.
     */
    public FilterCommand(LocalDate filterDate) {
        this.filterDate = filterDate;
    }

    /**
     * Filters out task with the specific date.
     *
     * @param taskList   TaskList of Duke.
     * @param ui      The user interface.
     * @param storage Storage for Duke.
     * @return Message to be shown to user.
     * @throws DukeException If date is invalid.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String message = taskList.filterByDate(filterDate);
        ui.formatPrint(message);
        return message;
    }
}
