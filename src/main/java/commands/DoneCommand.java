package commands;

import java.util.ArrayList;

import duke.DukeException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

/**
 * The DoneCommand Class inherits Command and is
 * a specific type of executable command.
 */
public final class DoneCommand extends Command {

    /**
     * Constructs the DoneCommand object.
     *
     * @param userInput the entire line of user input
     */
    public DoneCommand(ArrayList<String> userInput) {
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
        ArrayList<Task> tasks = list.getTasks();
        String result;
        try {
            if (getInput().size() == 1) {
                throw new DukeException("Please input index :)");
            }
            if (getInput().size() > 2) {
                throw new DukeException("Please input in the form: 'done <index>'.");
            }
            int index = Integer.parseInt(getInput().get(1)) - 1;
            if (index >= tasks.size() || index < 0) {
                throw new DukeException("No such index. Please input correct index, no such index :(");
            }
            result = tasks.get(index).setIsDone();
            storage.resetFile(tasks);
        } catch (IndexOutOfBoundsException e) {
            return "     Please input a valid index :)\n"
                    + "     Note: 'list' can be used to see the current tasks.";
        } catch (NumberFormatException e) {
            return "     Please use a number instead :(";
        } catch (DukeException e) {
            return "     " + e.getMessage();
        }
        assert !result.equals("") : "message for executing done is null";
        return result;
    }
}
