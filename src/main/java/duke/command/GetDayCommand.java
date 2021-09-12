package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;


/**
 * This class deals with the show task on a specific day command.
 */
public class GetDayCommand extends Command {
    private String next;

    /**
     * Constructs a new GetDayCommand
     *
     * @param next instruction provided, specifically include the date user wants.
     */
    public GetDayCommand(String next) {
        this.next = next;
    }

    /**
     * Executes instructions according to the Command type (here is getting all relevant tasks)
     */
    @Override
    public void execute() {
        //14 for general date, 9 for "get today"
        boolean checkLength = next.length() == 14 || next.length() == 9;
        if (!checkLength) {
            Ui.printWarning("☹ OOPS!!! Please enter a valid date, such as get dd/MM/yyyy or 'get today'");
        }
        try {
            ArrayList<Task> tasks = TaskList.getOnADay(next.substring(4));
            Ui.printTasks(tasks);
        } catch (DukeException e) {
            Ui.showError(e);
        }
    }
}
