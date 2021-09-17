package tipsy;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Parser is the class that deals with making sense of the user commands.
 */
public class Parser {

    /**
     * Returns type of command given in the specified user input.
     *
     * @param userInput The whole line of command from the user.
     * @return The type of command requested.
     * @throws UnsupportedOperationException  If the user entered a
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
        case "find":
            return CommandType.FIND_TASK;
        case "showpath":
            return CommandType.SHOW_PATH;
        case "setpath":
            return CommandType.SET_PATH;
        default:
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Creates a new task based on the user's inputs.
     * The user command given is expected to be "todo", "deadline"
     * or "event".
     *
     * @param userInput The whole line of command from the user.
     * @return The new task that was requested.
     * @throws MissingInputException  If the user did not enter any
     *                other task details after the command word.
     */
    public static Task parseNewTask(String userInput) throws MissingInputException {
        Scanner userInputScanner = new Scanner((userInput));
        TaskType taskType = TaskType.getTaskType(userInputScanner.next().toLowerCase());

        if (!userInputScanner.hasNext()) {
            throw new MissingInputException(taskType);
        }

        switch (taskType) {
        case TODO:
            String todoName = userInputScanner.nextLine().trim();
            return new ToDo(todoName);
        case DEADLINE:
            userInputScanner.useDelimiter(" /by ");
            String deadlineName = userInputScanner.next().trim();
            LocalDate deadlineDateTime =
                    LocalDate.parse(userInputScanner.next().trim());

            return new Deadline(deadlineName, deadlineDateTime);
        case EVENT:
            userInputScanner.useDelimiter(" /at ");
            String eventName = userInputScanner.next().trim();
            LocalDate eventDateTime =
                    LocalDate.parse(userInputScanner.next().trim());

            return new Event(eventName, eventDateTime);
        default:
            assert false; // Undefined task type input
            return null;
        }
    }

    /**
     * Returns the task number specified in the user command.
     * The user command given is expected to be "done" or "delete".
     *
     * @param userInput The whole line of command from the user.
     * @return The number of the task to execute the command on.
     */
    public static int parseTaskNum(String userInput) {
        Scanner userInputScanner = new Scanner(userInput);
        userInputScanner.next();
        return userInputScanner.nextInt();
    }

    /**
     * Returns the search term to be searched for among the names
     * of tasks in the list.
     *
     * @param userInput The whole line of command from the user.
     * @return The search term specified in the user command.
     */
    public static String parseSearchSubject(String userInput) {
        Scanner userInputScanner = new Scanner(userInput);
        userInputScanner.next();
        return userInputScanner.nextLine().trim();
    }

    /**
     * Returns the new path to be set as the save file location.
     *
     * @param userInput The whole line of command from the user.
     * @return The new save file path.
     */
    public static String parseNewPath(String userInput) {
        Scanner userInputScanner = new Scanner(userInput);
        userInputScanner.next();
        return userInputScanner.nextLine().trim();
    }
}
