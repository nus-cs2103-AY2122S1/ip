package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

/**
 * This class encapsulates a command to find a Task in a TaskList.
 * Extends Command class.
 */
public class FindCommand extends Command {

    private String[] searchInput;

    /**
     * Constructor for a FindCommand.
     * @param taskList TaskList from which to search.
     * @param storage Storage involved in command.
     * @param ui Ui involved in command.
     * @param searchInput Array of words input by user to search from TaskList.
     */
    public FindCommand(TaskList taskList, Storage storage, Ui ui, String[] searchInput) {
        super(taskList, storage, ui);
        this.searchInput = searchInput;
    }

    /**
     * Executes the command.
     */
    @Override
    public void execute() {
        String result = "Here's a list of the stuff I've found that match what you're looking for:\n";
        ArrayList<String> resultList = new ArrayList<>();
        for (int i = 1; i <= taskList.getSize(); i++) {
            boolean isMatch = false;
            Task task = taskList.get(i);
            String[] taskWords = task.getName().split(" ");
            for (String keyword : searchInput) {
                for (String word: taskWords) {
                    if (keyword.equalsIgnoreCase(word)) {
                        resultList.add(i + ". " + task.toString());
                        isMatch = true;
                        break;
                    }
                }

                if (isMatch) {
                    break;
                }
            }
        }

        for (String searchResult: resultList) {
            result += searchResult + "\n";
        }
        ui.setMessage(result);
    }

}
