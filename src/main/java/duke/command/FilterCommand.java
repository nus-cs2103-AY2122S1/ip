package duke.command;
import duke.main.Date;
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
    private boolean isExitCommand;
    /**
     * Class constructor.
     * 
     * @param dateString the date of interest.
     */
    public FilterCommand(String dateString) {
        this.date = new Date(dateString);
        isExitCommand = false;
    }
    
    /**
     * Executes a command to filter out tasks falling on the specified date.
     * @param tasks lists of tasks
     * @param ui the user interface.
     * @param storage the storage file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.findMatchingTasks(date.toString());
        String message = String.format("On %s, you have:", date.toString());
        ui.showMatchingTasks(matchingTasks, date.toString(), message);

    }
}
