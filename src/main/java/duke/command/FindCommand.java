package duke.command;

import duke.parser.ParsedInput;
import duke.task.TaskList;

/**
 * Represents a command to find tasks.
 */
public class FindCommand implements Command {

    private final TaskList tasks;
    private final String searchKey;

    /**
     * Creates a find command.
     *
     * @param tasks Task list
     */
    public FindCommand(ParsedInput parsedInput, TaskList tasks) {
        this.searchKey = parsedInput.searchKey;
        this.tasks = tasks;
    }

    /**
     * Runs the find command.
     *
     * @return Result string of tasks
     */
    @Override
    public String run() {
        return tasks.find(searchKey).toString().replace(
                "Here are the tasks in your list, meow:",
                "Here are the matching tasks found, meow:");
    }
}
