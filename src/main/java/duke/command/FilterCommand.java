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
    private String dateString;
    /**
     * Class constructor.
     *
     * @param dateString the date of interest.
     */
    public FilterCommand(String dateString) throws DukeException {
        super();
        String[] dateComponents = dateString.split("/");
        try {
            date = new Date(dateComponents);
            dateString = date.toString();
        } catch (Exception e) {
            throw new DukeException(e);
        }
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
        String message = generateFilterCommandMessage();
        return ui.showMatchingTasks(matchingTasks, dateString, message);

    }
    private TaskList findMatchingTasks(TaskList tasks) {
        return tasks.findMatchingTasks(dateString);
    }
    private String generateFilterCommandMessage() {
        return String.format("On %s, you have:", dateString);
    }
}
