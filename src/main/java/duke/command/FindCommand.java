package duke.command;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.task.TaskList;

/**
 * The type Find command that finds a user-inputted substring in the given list of tasks.
 */
public class FindCommand extends Command {

    /** Substring to search for in description of tasks */
    private final String substring;
    /** List of tasks to search from */
    private final TaskList tasks;

    /**
     * Instantiates a new Find command.
     *
     * @param userInput the user-inputted string.
     * @param tasks     the list of tasks to search from.
     */
    public FindCommand(String userInput, TaskList tasks) {
        this.substring = userInput.split(" ", 2)[1];
        this.tasks = tasks;
    }

    @Override
    public String execute() {
        String result = "Here are the matching tasks in your list:";

        result += IntStream
                .range(0, tasks.size())
                .filter(idx -> tasks.get(idx).getDescription().contains(substring))
                .mapToObj(idx -> "\n" + (idx + 1) + "." + tasks.get(idx).toString())
                .collect(Collectors.joining());

        return result;
    }
}
