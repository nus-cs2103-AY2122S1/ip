package commands;

import java.util.ArrayList;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * The DeleteCommand Class inherits Command and is
 * a specific type of executable command.
 */
public final class DeleteCommand extends Command {

    /**
     * Constructs the DeleteCommand object.
     *
     * @param userInput the entire line of user input
     */
    public DeleteCommand(ArrayList<String> userInput) {
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
        if (getInput().size() == 1) {
            return "     Unable to delete task without an index. Please input index :)\n"
                    + "     Please input in the form: 'delete <task index>'.\n"
                    + "     Note: list can be used to see the current tasks.";
        }
        if (getInput().size() > 2) {
            return "     Please input in the form: 'delete <index>'.";
        }
        if (list.getTasks().isEmpty()) {
            return "     List is empty, no tasks to delete, looking good!";
        }
        try {
            int index = Integer.parseInt(getInput().get(1)) - 1;
            String result = list.deleteTask(index);
            storage.resetFile(list.getTasks());
            return result;
        } catch (NumberFormatException e) {
            return "     Please use a number instead :(";
        } catch (IndexOutOfBoundsException e) {
            return "     Please input a valid index :)\n"
                    + "     Note: 'list' can be used to see the current tasks.";
        }
    }
}
