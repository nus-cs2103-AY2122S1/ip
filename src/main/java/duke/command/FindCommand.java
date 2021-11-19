package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Class implementing command. This class is responsible for finding all tasks in the list that
 * fits the user search term
 */
public class FindCommand implements Command {
    private String searchTerm;

    /**
     * Class constructor for FindCommand.
     *
     * @param searchTerm The term input by user to be searched.
     */
    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     * Getter method to determine if the program should continue running
     *
     * @return Always returns true.
     */
    @Override
    public boolean isRunning() {
        return true;
    }

    /**
     * Method to execute the given command.
     *
     * @param t The task list currently loaded.
     * @param ui The Ui object being used for the application.
     * @param storage The Storage object being used by the application.
     */
    @Override
    public String execute(TaskList t, Ui ui, Storage storage) {
        String itemString = "";
        for (int i = 0; i < t.getSize(); i++) {
            Task currTask = t.get(i);
            if (currTask.getTaskName().toLowerCase().contains(searchTerm.toLowerCase())) {
                itemString += currTask.toString() + "\n";
            }
        }
        if (itemString.equals("")) {
            itemString = "There are no matching tasks in your list for: " + searchTerm;
        } else {
            String temp = itemString.substring(0, itemString.length() - 1);
            itemString = "Here are the matching tasks in your list:\n";
            itemString += temp;
        }
        return ui.textFrame(itemString);
    }
}
