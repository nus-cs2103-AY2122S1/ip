package commands;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

/**
 * The DueCommand Class inherits Command and is
 * a specific type of executable command.
 */

public final class DueCommand extends Command {

    /**
     * Constructs the DueCommand object.
     *
     * @param userInput the entire line of user input
     */
    public DueCommand(ArrayList<String> userInput) {
        super(userInput);
    }

    /**
     * Executes the command.
     *
     * @param list the TaskList object that stores the list of tasks
     * @param ui the ui.Ui object that interacts with the user
     * @param storage the Storage object that saves changes to stored tasks, if any
     * @return the message displaying the result
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        assert list != null : "invalid TaskList object detected";
        assert ui != null : "invalid ui.Ui object detected";
        assert storage != null : "invalid Storage object detected";
        try {
            ArrayList<Task> tasksDue = list.findTasksDue(getInput().get(1));
            if (tasksDue.isEmpty()) {
                return "     No tasks due!";
            }
            String result = "     The tasks due are: \n";
            for (int i = 0; i < tasksDue.size(); i++) {
                if (i + 1 < tasksDue.size()) {
                    result += "     " + (i + 1) + "." + tasksDue.get(i).getType()
                            + tasksDue.get(i).getStatus() + " " + tasksDue.get(i).getDescription() + "\n";
                } else {
                    result += "     " + (i + 1) + "." + tasksDue.get(i).getType()
                            + tasksDue.get(i).getStatus() + " " + tasksDue.get(i).getDescription();
                }
            }
            return result;
        } catch (IndexOutOfBoundsException e) {
            return "     Invalid input :(\n" + Ui.getHelperMessage();
        } catch (DateTimeParseException e) {
            return "     Invalid input :( \n" + "     Please input 'due YYYY/MM/DD' with a valid date.";
        }
    }
}
