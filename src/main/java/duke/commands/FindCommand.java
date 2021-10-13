package duke.commands;

import java.util.Arrays;

import duke.DukeException;
import duke.TaskList;

/**
 * This class encapsulates a Find Command.
 *
 * @author Kleon Ang
 */
public class FindCommand extends Command {
    public FindCommand(TaskList tasks) {
        super(tasks);
    }

    @Override
    public String getReply(String arguments) throws DukeException {
        if (arguments.equals("")) {
            throw new DukeException("☹ OOPS!!! The keyword to 'find' cannot be empty.");
        }
        assert !arguments.equals("") : "Arguments required, cannot be empty.";
        return find(arguments);
    }

    /**
     * Finds tasks matching the given keywords.
     *
     * @param keywords A string containing one or more keywords.
     * @return A string representation of the matching tasks.
     * @throws DukeException A Duke-specific exception that may occur when finding tasks.
     */
    private String find(String keywords) throws DukeException {
        String[] splitKeywords = keywords.split(" ");
        TaskList matchingTasks = new TaskList();
        tasks.stream().filter(task -> Arrays.stream(splitKeywords)
                        .anyMatch(keyword -> task.containsKeyword(keyword) && !matchingTasks.contains(task)))
                .forEach(matchingTasks::add);
        return ListCommand.list(matchingTasks, true);
    }
}
