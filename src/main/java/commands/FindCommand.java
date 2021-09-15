package commands;

import exceptions.MorganException;
import storage.Storage;
import tasks.TaskList;

/**
 * This is an FindCommand Class, which inherits from Command.
 * The execution of this command will return a list of tasks
 * which contains the keyword.
 */
public class FindCommand extends Command {
    public static final String KEYWORD = "find";
    public static final String INPUT_FORMAT = String.format("\t%s [keyword]", KEYWORD);
    private static final String INPUT_FORMAT_ERROR = String.format("Please "
            + "ensure your input is in the following format:\n" + INPUT_FORMAT);
    private static final String NOT_FOUND_ERROR = "No matching task found. "
            + "Please try another keyword.";
    private final String keyTerm;

    /**
     * Constructor for command.
     * @param userInput The input string entered by the user.
     * @throws MorganException If input format is invalid.
     */
    public FindCommand(String userInput) throws MorganException {
        assert userInput != null;

        String inputData = userInput.substring(KEYWORD.length()).trim();
        this.keyTerm = inputData.toLowerCase();

        boolean isKeyTermFound = !keyTerm.isEmpty();
        if (!isKeyTermFound) {
            throw new MorganException(INPUT_FORMAT_ERROR);
        }
    }

    /**
     * Returns a list of tasks containing the specified keyword.
     * @param tasks The existing list of tasks.
     * @param storage The storage object to store task data.
     * @return The list of tasks containing the specified keyword.
     * @throws MorganException If there is no matching task found.
     */
    public String execute(TaskList tasks, Storage storage) throws MorganException {
        assert tasks != null && storage != null;
        TaskList foundTasks = tasks.findTasks(keyTerm);
        boolean isMatchingTaskFound = !foundTasks.isEmpty();
        if (!isMatchingTaskFound) {
            throw new MorganException(NOT_FOUND_ERROR);
        }
        String output = "Here are the matching tasks in your list:\n";
        output += foundTasks.toString();
        return output;
    }
}
