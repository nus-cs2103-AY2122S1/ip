package duke.commands;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.InvalidDirectoryException;
import duke.tasks.Task;


/**
 * Tells Duke to reflect the most updated lists of tasks in the current session.
 */
public class ListCommand extends Command {

    public ListCommand() {

    }

    /**
     * Calls the Ui to output the tasks in the list.
     * @throws InvalidDirectoryException if the directory path is invalid as the list would not have been initialised.
     */
    public String execute(TaskList tasks, Storage storage) throws InvalidDirectoryException {
        try {
            return printList(tasks);
        } catch (NullPointerException npe) {
            throw new InvalidDirectoryException("Unable to retrieve task info as specified directory does not exist");
        }
    }

    /**
     * Helper method used by {@link #printList(TaskList) printList}
     * Processes the list and concatenates the toString() values of tasks into a readable format
     *
     * @param listOfTasks The list to be processed (filtered by prior conditions in other functions)
     */
    public String processList(ArrayList<Task> listOfTasks) {
        String output = "";
        boolean isEmptyList = false;
        int counter = 1;

        while (!isEmptyList) {
            if (counter - listOfTasks.size() == 1) {
                output = output.replaceAll("[\n\r]$", ""); // remove last newline
                isEmptyList = true;
            } else {
                String lineAdded = String.format("   %d.%s \n", counter, listOfTasks.get(counter - 1));
                output += lineAdded;
                counter++;
            }
        }
        return output;
    }

    /**
     * Prints out all the tasks in the list
     *
     * @param lst current session's most updated list
     */
    public String printList(TaskList lst) {
        if (lst.getListOfTasks().size() == 0) {
            return "Current List is empty...";
        } else {
            String output = "Here are the tasks in your list: \n";
            output += processList(lst.getListOfTasks());
            return output;
        }
    }

    /**
     * Helper function to tell Duke to continue reading inputs
     */
    public boolean isExit() {
        return false;
    }
}
