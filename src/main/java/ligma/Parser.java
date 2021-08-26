package ligma;

import ligma.command.*;
import ligma.task.Deadline;
import ligma.task.Event;
import ligma.task.Task;
import ligma.task.Todo;

import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;

public class Parser {
    //should handle incorrect command format exceptions
    public static Command parseCommand(String input)
            throws NoSuchMethodException, InputMismatchException, DateTimeParseException {
        if (input.equals("bye")) {
            return new ExitCommand();
        }
        if (input.equals("list")) {
            return new ListCommand();
        } else {
            int splitInd = input.indexOf(' ');
            String action = splitInd == -1
                    ? input
                    : input.substring(0, splitInd);

            if (splitInd == -1) {
                return handleBadCommands(action);
            } else {
                String description = input.substring(splitInd + 1);
                int itemIndex = -1;
                if (action.equals("done") || action.equals("delete")) {
                    itemIndex = Character
                            .getNumericValue(input.charAt(splitInd + 1)) - 1;
                }
                switch (action) {
                case "todo":
                    return new AddCommand(Task.TaskType.TODO,
                            description);
                case "event":
                    return new AddCommand(Task.TaskType.EVENT,
                            description);
                case "deadline":
                    return new AddCommand(Task.TaskType.DEADLINE,
                            description);
                case "done":
                    return new DoneCommand(itemIndex);
                case "delete":
                    return new DeleteCommand(itemIndex);
                case "find" :
                    return new FindCommand(description.trim());
                default:
                    throw new NoSuchMethodException("Sorry, command does not exist.");
                }
            }
        }
    }

    private static Command handleBadCommands(String action)
            throws NoSuchMethodException, InputMismatchException, DateTimeParseException {
        switch (action) {
        case "todo":
        case "event":
        case "deadline":
            throw new InputMismatchException(
                    String.format("The description of %s cannot be empty.",
                            action.toUpperCase()));
        case "done":
            throw new InputMismatchException("Indicate index of item to be marked as done.");
        case "delete":
            throw new InputMismatchException("Indicate index of item to be deleted.");
        case "find":
            throw new InputMismatchException("Indicate term you wish to search for.");
        default:
            throw new NoSuchMethodException("Sorry, command does not exist.");
        }
    }

    public static Task parseFileContent(String s) throws IllegalArgumentException {
        char taskType = s.charAt(1);
        String desc = s.substring(7);
        boolean isDone = s.charAt(4) == 'X';
        switch (taskType) {
            case 'T':
                return new Todo(desc, isDone);
            case 'D':
                return Deadline.createDeadline(desc, isDone);
            case 'E':
                return Event.createEvent(desc, isDone);
            default:
                throw new IllegalArgumentException("File improperly formatted");
        }
    }
}
