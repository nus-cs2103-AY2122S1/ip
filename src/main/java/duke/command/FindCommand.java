package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * Contains the objects and methods necessary for a Find Command.
 *
 * @author Toh Wang Bin
 */
public class FindCommand implements Command {

    private static final int MIN_LENGTH_ARRAY = 2;

    private final String[] inputArray;
    private final TaskList taskList;

    /**
     * Constructor for a FindCommand instance.
     *
     * @param inputArray The String[] containing the user inputs.
     * @param taskList The TaskList instance associated with the command.
     */
    public FindCommand(String[] inputArray, TaskList taskList) {
        this.inputArray = inputArray;
        this.taskList = taskList;
    }

    /**
     * Executes the command unique to this Command class.
     *
     * @return A string representing the response to running this command.
     */
    public String execute() {
        if (inputArray.length < MIN_LENGTH_ARRAY) {
            //case if no number is entered
            return Ui.getNumberError();
        }
        StringBuilder str = new StringBuilder();
        for (int i = 1; i < inputArray.length; i++) {
            str.append(inputArray[i]).append(" ");
        }
        return taskList.find(str.toString());
    }
}
