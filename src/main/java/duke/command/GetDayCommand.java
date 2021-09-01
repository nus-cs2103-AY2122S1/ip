package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;

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
        if (next.length() == 14) {
            try {
                TaskList.getOnADay(next.substring(4));
            } catch (DukeException e) {
                Ui.showError(e);
            }
        } else {
            Ui.myPrint("☹ OOPS!!! Please enter a valid date, such as get dd/MM/yyyy");
        }
    }
}
