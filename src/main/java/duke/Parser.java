package duke;

import java.time.LocalDate;
import java.util.Scanner;

public class Parser {

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
            default:
                throw new UnsupportedOperationException();
        }
    }

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

        if (!userInputScanner.hasNext())
            throw new MissingInputException(taskType);

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

    public static int parseTaskNum(String userInput) {
        Scanner userInputScanner = new Scanner(userInput);
        userInputScanner.next();
        return userInputScanner.nextInt();
    }

    public static String parseSearchSubject(String userInput) {
        Scanner userInputScanner = new Scanner(userInput);
        userInputScanner.next();
        return userInputScanner.nextLine().trim();
    }
}
