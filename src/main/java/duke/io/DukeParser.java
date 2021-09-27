package duke.io;

import duke.exception.DukeFileSystemException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.type.DukeCommand;

/**
 * A class that handles anything related to the parsing of information for Duke.
 */
public class DukeParser {
    // Defines the representation of a task that is done in Duke's store format
    private static final String TRUTH_REPRESENTATION = "1";

    /**
     * Provides a task based on data read from line.
     *
     * @param line line that data is read from.
     * @return a task matching the information from the line of data.
     * @throws DukeFileSystemException if the line provided does not conform to the Duke storing format.
     */
    public Task parseTaskFromLine(String line) throws DukeFileSystemException {
        String[] args = line.split(" \\| ");
        String taskType = args[0];
        boolean isDone = args[1].equals(TRUTH_REPRESENTATION);
        String taskDescription = args[2];
        String date = args.length > 3 ? args[3] : "";
        Task task;
        switch (taskType) {
        case "T":
            task = new Todo(taskDescription);
            break;
        case "D":
            task = new Deadline(taskDescription, date);
            break;
        case "E":
            task = new Event(taskDescription, date);
            break;
        default:
            throw new DukeFileSystemException("Invalid data read from file while loading tasks. A new list will "
                    + "be used for this session.");
        }
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Gets the type of Duke command of a given input string.
     * The Duke command of an input string is given by the first argument provided in the string.
     *
     * @param inputString the string literal of the input.
     * @return a corresponding Duke command type based on the input string.
     */
    public DukeCommand getCommandType(String inputString) {
        DukeCommand inputCommand;
        String command = inputString.split(" ", 2)[0];
        switch (command) {
        case "find":
            inputCommand = DukeCommand.FIND;
            break;
        case "done":
            inputCommand = DukeCommand.DONE;
            break;
        case "bye":
            inputCommand = DukeCommand.BYE;
            break;
        case "list":
            inputCommand = DukeCommand.LIST;
            break;
        case "todo":
            inputCommand = DukeCommand.TODO;
            break;
        case "event":
            inputCommand = DukeCommand.EVENT;
            break;
        case "deadline":
            inputCommand = DukeCommand.DEADLINE;
            break;
        case "delete":
            inputCommand = DukeCommand.DELETE;
            break;
        default:
            inputCommand = DukeCommand.INVALID;
        }

        return inputCommand;
    }

    /**
     * Gets the string literal of the arguments provided in a duke command.
     * This refers to the entire string literal after the first word provided in the input.
     *
     * @param inputString the string literal of the input.
     * @return a string literal of the input after the first word. If there is only one word, an empty string is
     * returned instead.
     */
    public String getArgsLiteral(String inputString) {
        String[] args = inputString.split(" ", 2);
        return args.length > 1 ? args[1] : "";
    }

    /**
     * Checks if given string contains the range symbol, defined as "...".
     *
     * @param inputString the string to be checked.
     * @return true if given string contains range symbol.
     */
    public boolean containsRangeSymbol(String inputString) {
        return inputString.contains("...");
    }

    /**
     * Returns an array of the two numbers in the string of format of a range: "%d...%d", where the first
     * number corresponds with the first element of the array.
     *
     * @param inputString the string to be parsed.
     * @return an integer array that contains the 2 numbers following the format, in the order they appear.
     * @throws NumberFormatException if the given string does not contain valid integers.
     * @throws ArrayIndexOutOfBoundsException if the string does not follow the given format.
     */
    public int[] getRangeBoundaries(String inputString) throws NumberFormatException, ArrayIndexOutOfBoundsException {
        String[] args = inputString.split("\\.\\.\\.", 2);
        int[] resultRange = new int[2];
        resultRange[0] = Integer.parseInt(args[0]);
        resultRange[1] = Integer.parseInt(args[1]);
        return resultRange;
    }
}
