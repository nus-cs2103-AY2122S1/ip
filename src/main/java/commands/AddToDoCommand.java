package commands;
import tasks.ToDoTask;
import exceptions.DukeException;

/**
 * This is an AddToDoCommand Class, which inherits from AddCommand.
 * The execution of this command will add a to-do task to the task list.
 */
public class AddToDoCommand extends AddCommand {
    public static final String KEYWORD = "todo";

    /**
     * Constructor for AddToDoCommand.
     * @param userInput The input string entered by the user.
     * @throws DukeException
     */
    public AddToDoCommand (String userInput) throws DukeException {
        String inputFormat = String.format("\t\"%s [task]\"", KEYWORD);
        String inputData = userInput.substring(KEYWORD.length()).trim();
        // Check whether input contains task
        boolean hasTask = !inputData.isEmpty();
        if (!hasTask) {
            throw new DukeException("OOPS!!! " +
                    "Please ensure your input is in the following format:\n" + inputFormat);
        }
        this.task = new ToDoTask(inputData);
    }
}
