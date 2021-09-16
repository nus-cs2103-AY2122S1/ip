package duke.commands;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidInputException;
import duke.tasks.Task;


public class FindCommand extends Command {

    private final String keyWord;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * Finds all tasks from the tasklist containing the keyword and reflects the result via the Ui.
     * @throws InvalidInputException if the task does not exist in the list.
     */
    public String execute(TaskList task, Storage storage) throws DukeException {
        if (keyWord.equals("") || keyWord.equals(" ")) {
            throw new InvalidInputException("Keyword cannot be a space or be empty");
        } else {
            ArrayList<Task> matched = task.findMatching(keyWord);
            return printFoundTasks(matched, keyWord);
        }
    }

    /**
     * Helper method used by {@link #printFoundTasks(ArrayList, String) printFoundTasks}
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
     * Prints out all matching tasks in the list which contains the keyword
     *
     * @param listOfTasks ArrayList of current session's most updated list
     * @param keyWord user input
     */
    public String printFoundTasks(ArrayList<Task> listOfTasks, String keyWord) {
        if (listOfTasks.size() == 0) {
            return String.format("No matching tasks containing '%s' could be found :(", keyWord);
        } else {
            String output = "Here are the matching tasks in your list: \n";
            output += processList(listOfTasks);
            return output;
        }
    }

    public boolean isExit() {
        return false;
    }
}
