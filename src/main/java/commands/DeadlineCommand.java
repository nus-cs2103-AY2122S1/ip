package commands;

import java.util.ArrayList;

import duke.DukeException;
import storage.Storage;
import tasks.DeadLineTask;
import tasks.TaskList;
import ui.Ui;

/**
 * The DeadlineCommand Class inherits Command and is
 * a specific type of executable command.
 */
public final class DeadlineCommand extends Command {

    /**
     * Constructs the DeadlineCommand object.
     *
     * @param userInput the entire line of user input
     */
    public DeadlineCommand(ArrayList<String> userInput) {
        super(userInput);
    }

    /**
     * Executes the command.
     *
     * @param list the TaskList object that stores the list of tasks
     * @param ui the Ui object that interacts with the user
     * @param storage the Storage object that saves changes to stored tasks, if any
     * @return the message displaying the result
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        assert list != null : "invalid TaskList object detected";
        assert ui != null : "invalid Ui object detected";
        assert storage != null : "invalid Storage object detected";
        try {
            DeadLineTask task = new DeadLineTask(list.filterInfo(getInput()),
                    list.getDeadline(getInput()));
            String result = list.addTask(task);
            if (!task.hasValidTime()) {
                result = "     No valid time found, please remember to add later if need be!\n" + result;
            }
            if (!task.hasValidTime() && !task.hasValidDate()
                    || task.hasValidTime() && !task.hasValidDate()) {
                result = "     Not a valid date, please remember to reschedule later!\n" + result;
            }
            storage.resetFile(list.getTasks());
            return result;
        } catch (DukeException e) {
            if (e.getMessage().equals("deadline")) {
                return "     Invalid input :(\n"
                        + "     Please input in the form: 'deadline <Name> /by <Date>'.";
            } else {
                return "     " + e.getMessage() + "\n"
                        + "     Hey, no deadline recorded does not mean no deadline >:(\n"
                        + "     An estimation will be fine, I will remind you on that day.";
            }
        }
    }
}
