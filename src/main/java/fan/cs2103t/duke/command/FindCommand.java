package fan.cs2103t.duke.command;

import static fan.cs2103t.duke.commons.Messages.MESSAGE_NOTHING_MATCHING_QUERY;
import static fan.cs2103t.duke.commons.Messages.MESSAGE_SUCCESSFULLY_FOUND_FORMAT;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

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
        ArrayList<Integer> count = new ArrayList<>();
        count.add(0);
        AtomicBoolean isFound = new AtomicBoolean(false);
        taskList.getTasks().stream()
                .forEachOrdered(t -> {
                    count.set(0, count.get(0) + 1);
                    if (t.getDescription().toLowerCase().contains(searchQuery.toLowerCase())) {
                        isFound.set(true);
                        result.append(count.get(0))
                                .append(".")
                                .append(t.getDescriptionWithStatus())
                                .append("\n");
                    }
                }); // simply replace the original for loop with stream; for the purpose of using stream only
        if (isFound.get()) {
            result.setLength(result.length() - 1);
            output = String.format(MESSAGE_SUCCESSFULLY_FOUND_FORMAT, result);
        } else {
            output = MESSAGE_NOTHING_MATCHING_QUERY;
        }
        return output;
    }

}
