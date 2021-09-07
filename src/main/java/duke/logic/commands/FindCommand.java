package duke.logic.commands;

import java.util.ArrayList;
import java.util.List;

import duke.logic.tasks.Task;

/**
 * Finds the tasks whose descriptions contain the keyword.
 */
public class FindCommand extends Command {
    private static final String SEARCH_RESULT_MSG = "Here are the matching tasks in your list:";

    private String[] keywords;

    /**
     * Creates a command to find matching tasks.
     *
     * @param keywords The array of keywords to search for.
     */
    public FindCommand(String[] keywords) {
        this.keywords = keywords;
    }

    /**
     * Searches by the keyword and returns the matching tasks.
     *
     * @return The execution result.
     */
    @Override
    public CommandResult execute() {
        List<Task> matches = getTaskList().find(keywords);
        List<String> results = new ArrayList<>();
        results.add(SEARCH_RESULT_MSG);
        for (int i = 0; i < matches.size(); i++) {
            results.add(i + 1 + "." + matches.get(i).toString());
        }
        return new CommandResult(String.join("\n", results));
    }
}
