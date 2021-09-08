package iris;

import iris.command.ByeCommand;
import iris.command.Command;
import iris.command.CommandWord;
import iris.command.DeadlineCommand;
import iris.command.DeleteCommand;
import iris.command.DoneCommand;
import iris.command.EventCommand;
import iris.command.FindCommand;
import iris.command.ListCommand;
import iris.command.ToDoCommand;
import iris.task.TaskPriority;

/**
 * Encapsulates the parsing-related functionality of Iris
 */
public class Parser {
    private static String getMetadata(String command) throws IrisException {
        String[] splitted = command.split(" ", 2);
        if (splitted.length == 1 || splitted[1].equals("")) {
            throw new IrisException("The description cannot be empty");
        } else {
            return splitted[1];
        }
    }

    private static int parseInt(String text) throws IrisException {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException exception) {
            throw new IrisException("Please enter a valid integer");
        }
    }

    private static TaskPriority getPriority(String fullCommand) {
        String command = fullCommand.split(" ")[0];
        if (command.endsWith("!!")) {
            return TaskPriority.HIGH;
        } else if (command.endsWith("!")) {
            return TaskPriority.MEDIUM;
        } else {
            return TaskPriority.LOW;
        }
    }

    /**
     * Parses a given command
     *
     * @param fullCommand raw command string to parse
     * @return Command object representing the action to be taken
     * @throws IrisException if command is invalid
     */
    public static Command parse(String fullCommand) throws IrisException {
        CommandWord commandWord = CommandWord.getCommandWord(fullCommand);
        String[] splitted;

        switch (commandWord) {
        case LIST:
            return new ListCommand();
        case TODO:
            return new ToDoCommand(getMetadata(fullCommand), getPriority(fullCommand));
        case DEADLINE:
            splitted = getMetadata(fullCommand).split(" /by ");
            if (splitted.length != 2) {
                throw new IrisException("Expected format: deadline [name] /by [date]");
            }
            return new DeadlineCommand(splitted[0], splitted[1], getPriority(fullCommand));
        case EVENT:
            splitted = getMetadata(fullCommand).split(" /at ");
            if (splitted.length != 2) {
                throw new IrisException("Expected format: event [name] /at [date]");
            }
            return new EventCommand(splitted[0], splitted[1], getPriority(fullCommand));
        case DONE:
            return new DoneCommand(parseInt(getMetadata(fullCommand)));
        case DELETE:
            return new DeleteCommand(parseInt(getMetadata(fullCommand)));
        case FIND:
            return new FindCommand(getMetadata(fullCommand));
        case BYE:
            return new ByeCommand();
        case INVALID:
            throw new IrisException("I'm sorry, but I don't know what that means.");
        default:
            assert false;
            throw new IrisException("I'm sorry, but I don't know what that means.");
        }
    }
}
