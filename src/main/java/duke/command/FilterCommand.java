package duke.command;

import duke.main.Date;
import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * Represents a command to filter out tasks that fall on a specific date.
 *
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */
public class FilterCommand extends Command {
    private Date date;
    /**
     * Class constructor.
     *
     * @param dateOfInterest the date of interest.
     */
    public FilterCommand(String dateOfInterest) throws DukeException {
        super();
        assert dateOfInterest != null : "date cannot be empty";
        String[] dateComponents = dateOfInterest.split("/");
        date = new Date(dateComponents);
        assert date != null : "date cannot be null";
    }
    /**
     * Executes a command to filter out tasks falling on the specified date.
     * @param tasks lists of tasks
     * @param ui the user interface.
     * @param storage the storage file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = findMatchingTasks(tasks);
        assert matchingTasks != null : "matching task list cannot be null";
        String message = generateFilterCommandMessage();
        return ui.showMatchingTasks(matchingTasks, message);
    }
    private TaskList findMatchingTasks(TaskList tasks) {
        return tasks.findMatchingTasks(date.toString());
    }
    private String generateFilterCommandMessage() {
        return String.format("On %s, you have:", date.toString());
    }
}
