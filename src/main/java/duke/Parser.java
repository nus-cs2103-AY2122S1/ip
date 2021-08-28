package duke;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Parser is the class that deals with making sense of the user commands.
 */
public class Parser {

    /**
     * Returns type of command given in the specified user input.
     *
     * @param userInput the whole line of command from the user.
     * @return the type of command requested.
     * @throws UnsupportedOperationException  if the user entered a
     *                command that is not recognized.
     */
    public static CommandType parseCommandType(String userInput)
            throws UnsupportedOperationException {

        Scanner userInputScanner = new Scanner(userInput);
        String operation = userInputScanner.next();

        switch (operation.toLowerCase()) {
        case "bye":
            return CommandType.EXIT;
        case "list":
            return CommandType.LIST;
        case "done":
            return CommandType.COMPLETE_TASK;
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            return CommandType.ADD_TASK;
        case "delete":
            return CommandType.DELETE_TASK;
        default:
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Creates a new task based on the user's inputs.
     * The user command given is expected to be "todo", "deadline"
     * or "event".
     *
     * @param userInput the whole line of command from the user.
     * @return the new task that was requested.
     * @throws MissingInputException  if the user did not enter any
     *                other task details after the command word.
     */
    public static Task parseNewTask(String userInput) throws MissingInputException {
        Scanner userInputScanner = new Scanner((userInput));
        TaskType taskType = null;
        switch (userInputScanner.next().toLowerCase()) {
        case "todo":
            taskType = TaskType.TODO;
            break;
        case "deadline":
            taskType = TaskType.DEADLINE;
            break;
        case "event":
            taskType = TaskType.EVENT;
            break;
        }

        if (!userInputScanner.hasNext()) {
            throw new MissingInputException(taskType);
        }

        switch (taskType) {
        case TODO:
            return new ToDo(userInputScanner.nextLine().trim());
        case DEADLINE:
            userInputScanner.useDelimiter(" /by ");
            return new Deadline(userInputScanner.next().trim(),
                    LocalDate.parse(userInputScanner.next().trim()));
        case EVENT:
            userInputScanner.useDelimiter(" /at ");
            return new Event(userInputScanner.next().trim(),
                    LocalDate.parse(userInputScanner.next().trim()));
        default:
            return null;    // Error
        }
    }

    /**
     * Returns the task number specified in the user command.
     * The user command given is expected to be "done" or "delete".
     *
     * @param userInput the whole line of command from the user.
     * @return the number of the task to execute the command on.
     */
    public static int parseTaskNum(String userInput) {
        Scanner userInputScanner = new Scanner(userInput);
        userInputScanner.next();
        return userInputScanner.nextInt();
    }
}
