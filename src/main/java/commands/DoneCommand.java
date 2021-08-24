package commands;

import java.util.ArrayList;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

/**
 * The DoneCommand Class inherits Command and is
 * a specific type of executable command.
 */
public final class DoneCommand extends Command{

    /**
     * Constructs the DoneCommand object.
     *
     * @param s the entire line of user input
     */
    public DoneCommand(ArrayList<String> s) {
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
        ArrayList<Task> tasks = lst.getTasks();
        try {
            if (getInput().size() == 1) {
                throw new IllegalArgumentException("Please input index :)");
            }
            if (getInput().size() > 2) {
                throw new IllegalArgumentException("Please input in the form: 'done <index>'.");
            }
            int index = Integer.parseInt(getInput().get(1)) - 1;
            if (index >= tasks.size() || index < 0) {
                throw new IllegalArgumentException("No such index. Please input correct index, no such index :(");
            }
            tasks.get(index).setIsDone();
            storage.resetFile(tasks);
        } catch (IndexOutOfBoundsException e) {
            Ui.showInput("Please input a valid index :)",
                    "Note: 'list' can be used to see the current tasks.");
        } catch (NumberFormatException e) {
            Ui.showInput("Please use a number instead :(");
        } catch (IllegalArgumentException e) {
            Ui.showInput(e.getMessage());
        }
    }
}
