package commands;

import java.util.ArrayList;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

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
     */
    @Override
    public void execute(TaskList lst, Ui ui, Storage storage) {
        if (super.getInput().size() == 1) {
            Ui.showInput("Unable to delete task without an index. Please input index :)",
                    "Please input in the form: 'delete <task index>'.",
                    "Note: list can be used to see the current tasks.");
        } else {
            if (lst.getTasks().isEmpty()) {
                Ui.showInput("List is empty, no tasks to delete, looking good!");
            } else if (super.getInput().size() > 2) {
                Ui.showInput("Please input in the form: 'delete <index>'.");
            } else {
                try {
                    int index = Integer.parseInt(super.getInput().get(1)) - 1;
                    lst.deleteTask(index);
                    storage.resetFile(lst.getTasks());
                } catch (NumberFormatException e) {
                    Ui.showInput("Please use a number instead :(");
                } catch (IndexOutOfBoundsException e) {
                    Ui.showInput("Please input a valid index :)",
                            "Note: 'list' can be used to see the current tasks.");
                }
            }
        }
    }
}
