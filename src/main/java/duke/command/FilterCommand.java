package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskDate;
import duke.task.TaskList;
import duke.main.Ui;

/**
 * Represents a command to filter out tasks that fall on a specific date.
 *
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */
public class FilterCommand extends Command {
    private TaskDate date;
    private String dateString;
    /**
     * Class constructor.
     *
     * @param dateString the date of interest.
     */
    public FilterCommand(String dateString) throws DukeException {
        super();
        date = new TaskDate(dateString);
        this.dateString = date.toString();
    }
    /**
     * Executes a command to filter out tasks falling on the specified date.
     * @param tasks lists of tasks
     * @param ui the user interface.
     * @param storage the storage file.
     * @return message displaying all tasks that falls on the specified date.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList matchingTasks = findMatchingTasks(tasks);
        String headerMessage = generateHeaderMessage();
        return ui.showMatchingTasks(matchingTasks, headerMessage);
    }
    private TaskList findMatchingTasks(TaskList tasks) {
        return tasks.findMatchingTasks(dateString);
    }
    private String generateHeaderMessage() {
        return String.format("On %s, you have:", dateString);
    }
}
