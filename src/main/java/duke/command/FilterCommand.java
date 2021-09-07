<<<<<<< HEAD
package duke.command;

=======
<<<<<<<< HEAD:src/main/java/duke/command/FilterCommand.java
package duke.command;
>>>>>>> branch-A-Level-10
import duke.main.Date;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
<<<<<<< HEAD

/**
 * Represents a duke.command to filter out tasks that fall on a specific date.
 *
=======
========
package IP.duke.command;
import IP.duke.main.Date;
import IP.duke.main.DukeException;
import IP.duke.main.Storage;
import IP.duke.main.TaskList;
import IP.duke.main.Ui;
import IP.duke.task.Task;

import java.util.ArrayList;
>>>>>>>> branch-A-Level-10:src/main/java/IP/duke/command/FilterCommand.java

/**
 * Represents a command to filter out tasks that fall on a specific date.
 * 
>>>>>>> branch-A-Level-10
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */
public class FilterCommand extends Command {
<<<<<<< HEAD
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
=======
    private Date date;
    private boolean isExitCommand;
    /**
     * Class constructor.
     * 
     * @param dateString the date of interest.
     */
    public FilterCommand(String dateString) throws DukeException {
        String[] dateComponents = dateString.split("/");
        try {
            this.date = new Date(dateComponents);
        } catch (Exception e) {
            throw new DukeException(e);
        }
        isExitCommand = false;
    }
    
    /**
     * Executes a command to filter out tasks falling on the specified date.
     * @param tasks lists of tasks
     * @param ui the user interface.
     * @param storage the storage file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.findMatchingTasks(date.toString());
        String message = String.format("On %s, you have:", date.toString());
        return ui.showMatchingTasks(matchingTasks, date.toString(), message);
>>>>>>> branch-A-Level-10

    }
}
