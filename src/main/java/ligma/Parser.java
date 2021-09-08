package ligma;

import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;

import ligma.command.AddCommand;
import ligma.command.Command;
import ligma.command.DeleteCommand;
import ligma.command.DoneCommand;
import ligma.command.ExitCommand;
import ligma.command.FindCommand;
import ligma.command.ListCommand;
import ligma.task.Deadline;
import ligma.task.Event;
import ligma.task.Task;
import ligma.task.Todo;

/**
 * This class contains functions that help parse strings into
 * either Task or Command objects.
 */
public class Parser {
    /**
     * Parses user input to Command object.
     *
     * @return corresponding Command object
     * @throws NoSuchMethodException if user input is an invalid command
     * @throws InputMismatchException if description of task is improperly formatted
     * @throws DateTimeParseException if time of task is improperly formatted
     */
    public static Command parseCommand(String input)
            throws NoSuchMethodException, InputMismatchException, DateTimeParseException {
        int splitInd = input.indexOf(' ');
        boolean isSingleWord = splitInd == -1;
        if (isSingleWord) {
            switch (input) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            default:
                return handleBadCommands(input);
            }
        } else {
            String action = input.substring(0, splitInd);
            String description = input.substring(splitInd + 1).trim();

            switch (action) {
            case "todo":
                return new AddCommand(Task.TaskType.TODO, description);
            case "event":
                return new AddCommand(Task.TaskType.EVENT, description);
            case "deadline":
                return new AddCommand(Task.TaskType.DEADLINE, description);
            case "done":
                return new DoneCommand(extractItemIndex(splitInd, input));
            case "delete":
                return new DeleteCommand(extractItemIndex(splitInd, input));
            case "find" :
                return new FindCommand(description);
            default:
                throw new NoSuchMethodException("Sorry, command does not exist.");
            }
        }
    }

    private static int extractItemIndex(int splitInd, String input) {
        return Character
                .getNumericValue(input.charAt(splitInd + 1)) - 1;
    }

    private static Command handleBadCommands(String action)
            throws NoSuchMethodException, InputMismatchException {
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

    /**
     * Parses file contents to Command object.
     *
     * @return corresponding Command object
     * @throws NoSuchMethodException if user input is an invalid command
     * @throws InputMismatchException if description of task is improperly formatted
     * @throws DateTimeParseException if time of task is improperly formatted
     */
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
