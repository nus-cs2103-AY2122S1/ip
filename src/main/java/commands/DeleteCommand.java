package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.util.ArrayList;

/**
 * The DeleteCommand Class inherits Command and is
 * a specific type of executable command.
 */
public final class DeleteCommand extends Command{

    /**
     * Constructs the DeleteCommand object.
     *
     * @param s the entire line of user input
     */
    public DeleteCommand(ArrayList<String> s) {
        super(s);
    }


    /**
     * Executes the command.
     *
     * @param lst the TaskList object that stores the list of tasks
     * @param ui the Ui object that interacts with the user
     * @param storage the Storage object that saves changes to stored tasks, if any
     * @return the message displaying the result
     */
    @Override
    public String execute(TaskList lst, Ui ui, Storage storage) {
        if (super.getInput().size() == 1) {
            return "Unable to delete task without an index. Please input index :)\n"
                    + "Please input in the form: 'delete <task index>'.\n"
                    + "Note: list can be used to see the current tasks.";
        } else {
            if (lst.getTasks().isEmpty()) {
                return "List is empty, no tasks to delete, looking good!";
            } else if (super.getInput().size() > 2) {
                return "Please input in the form: 'delete <index>'.";
            } else {
                try {
                    int index = Integer.parseInt(super.getInput().get(1)) - 1;
                    String result = lst.deleteTask(index);
                    storage.resetFile(lst.getTasks());
                    return result;
                } catch (NumberFormatException e) {
                    return "Please use a number instead :(";
                } catch (IndexOutOfBoundsException e) {
                    return "Please input a valid index :)\n"
                            + "Note: 'list' can be used to see the current tasks.";
                }
            }
        }
    }
}
