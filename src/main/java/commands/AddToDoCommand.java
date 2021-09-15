package commands;
import exceptions.MorganException;
import tasks.ToDoTask;

/**
 * This is an AddToDoCommand Class, which inherits from AddCommand.
 * The execution of this command will add a to-do task to the task list.
 */
public class AddToDoCommand extends AddCommand {
    public static final String KEYWORD = "todo";
    public static final String INPUT_FORMAT = String.format("\t%s [task]", KEYWORD);
    private static final String INPUT_FORMAT_ERROR = String.format("Please "
            + "ensure your input is in the following format:\n" + INPUT_FORMAT);

    /**
     * Constructor for AddToDoCommand.
     * @param userInput The input string entered by the user.
     * @throws MorganException If input format is invalid.
     */
    public AddToDoCommand (String userInput) throws MorganException {
        assert userInput != null;
        String inputData = userInput.substring(KEYWORD.length()).trim();
        boolean hasTask = !inputData.isEmpty();
        if (!hasTask) {
            throw new MorganException(INPUT_FORMAT_ERROR);
        }
        this.task = new ToDoTask(inputData);
    }
}
