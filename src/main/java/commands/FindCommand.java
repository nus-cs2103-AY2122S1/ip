package commands;
import exceptions.MorganException;
import tasks.TaskList;

/**
 * This is an FindCommand Class, which inherits from Command.
 * The execution of this command will return a list of tasks
 * which contains the keyword.
 */
public class FindCommand extends Command {
    public static final String KEYWORD = "find";
    private final String keyTerm;
    private final String NOT_FOUND_ERROR_MESSAGE = "No matching task found. "
            + "Please try another keyword.";

    /**
     * Constructor for command.
     * @param userInput The input string entered by the user.
     */
    public FindCommand(String userInput) {
        String inputFormat = String.format("\t\"%s [keyword/(s)]\"", KEYWORD);
        String inputData = userInput.substring(KEYWORD.length()).trim();
        this.keyTerm = inputData.toLowerCase();
    }

    /**
     * Returns a list of tasks containing the specified keyword.
     * @param taskList The existing list of tasks.
     * @return The list of tasks containing the specified keyword.
     */
    public String execute(TaskList taskList) throws MorganException {
        TaskList foundTasks = taskList.findTasks(keyTerm);
        if (foundTasks.isEmpty()) {
            throw new MorganException(NOT_FOUND_ERROR_MESSAGE) ;
        }
        String output = "Here are the matching tasks in your list:\n";
        output += foundTasks.toString();
        return output;
    }
}
