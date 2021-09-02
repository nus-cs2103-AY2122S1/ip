package duke.command;

import duke.main.Date;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * Represents a duke.command to filter out tasks that fall on a specific date.
 *
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */
public class FilterCommand extends Command {
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private final Date DATE;

    /**
     * Class constructor.
     *
     * @param dateString the date of interest.
     */
    public FilterCommand(String dateString) {
        super();
        this.DATE = new Date(dateString);
    }

    /**
     * Executes a duke.command to filter out tasks falling on the specified date.
     *
     * @param tasks   lists of tasks
     * @param ui      the user interface.
     * @param storage the storage file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.findMatchingTasks(DATE.toString());
        String message = String.format("On %s, you have:", DATE.toString());
        ui.showMatchingTasks(matchingTasks, message);

    }
}
