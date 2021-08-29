package fan.cs2103t.duke.command;

import fan.cs2103t.duke.task.Task;
import fan.cs2103t.duke.task.TaskList;
import fan.cs2103t.duke.ui.Ui;

/**
 * Represents a Duke's command which looks for all the tasks in Duke's task list
 * whose description contains a query string upon execution.
 * <p>
 * This is a subclass of the <code>Command</code> class.
 */
public class FindCommand extends Command {

    private final String searchQuery;

    /**
     * Constructs a find command with the specified query string.
     *
     * @param searchQuery the query string to be used for searching.
     */
    public FindCommand(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    /**
     * Executes this command. Looks for all the tasks in the specified task list
     * whose description contains a query string.
     * Displays a message to the user through the specified UI
     * listing all the matching tasks found or indicating nothing matches the search query.
     * Returns the result message as a string.
     *
     * @param taskList the task list in which the search is conducted.
     * @param ui the UI for the message to be displayed through.
     * @return a message to indicate the result of searching.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        String output;
        StringBuilder result = new StringBuilder();
        int count = 0;
        boolean hasFound = false;
        for (Task t : taskList.getTasks()) {
            count++;
            if (t.getDescription().contains(searchQuery)) {
                hasFound = true;
                result.append(count)
                        .append(".")
                        .append(t.getDescriptionWithStatus())
                        .append("\n");
            }
        }
        if (hasFound) {
            result.setLength(result.length() - 1);
            output = "Here are the matching tasks in your list: \n" + result;
            ui.displayText(output);
        } else {
            output = "It seems nothing in your list matches the search query...";
            ui.displayText(output);
        }
        return output;
    }

}
