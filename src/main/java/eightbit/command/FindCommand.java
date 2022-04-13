package eightbit.command;

import java.util.ArrayList;
import java.util.Locale;

import eightbit.task.Task;
import eightbit.util.Storage;
import eightbit.util.TaskList;

/**
 * Represents a command for finding tasks based on some keywords.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Constructs a command for finding tasks.
     *
     * @param keyword Search keyword.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase(Locale.ROOT); // allows case-insensitive search
    }

    /**
     * Retrieves the tasks containing the search keyword and prints them.
     *
     * @param taskList User's list of tasks.
     * @param storage  Storage responsible for reading/writing the file.
     * @return The response after executing the command.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        assert keyword != null : "Keyword should be initialized";

        // Get tasks containing the keyword
        ArrayList<Task> results = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.getDescription().toLowerCase(Locale.ROOT).contains(keyword)) { // case-insensitive search
                results.add(task);
            }
        }

        // Construct the message to be printed
        StringBuilder message = new StringBuilder();
        if (results.size() == 0) {
            message.append("There are no matching tasks in your list.");
        } else {
            message.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < results.size(); i++) {
                String s = (i + 1) + ". " + results.get(i) + "\n";
                message.append(s);
            }
        }

        return message.toString();
    }
}
