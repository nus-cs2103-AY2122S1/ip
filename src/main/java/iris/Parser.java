package iris;

import iris.command.Command;
import iris.command.ListCommand;
import iris.command.ToDoCommand;
import iris.command.DeadlineCommand;
import iris.command.EventCommand;
import iris.command.DoneCommand;
import iris.command.DeleteCommand;
import iris.command.FindCommand;

/**
 * Encapsulates the parsing-related functionality of Iris
 */
public class Parser {
    private static String getMetadata(String command) throws IrisException {
        String[] splitted = command.split(" ", 2);
        if (splitted.length == 1 || splitted[1].equals("")) {
            // TODO: make this error message more specific?
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

    public static Command parse(String command) throws IrisException {
        if (command.equals("list")) {
            return new ListCommand();
        } else if (command.startsWith("todo")) {
            return new ToDoCommand(getMetadata(command));
        } else if (command.startsWith("deadline")) {
            String[] splitted = getMetadata(command).split(" /by ");
            if (splitted.length != 2) {
                throw new IrisException("deadline should have 2 arguments: a name and a time");
            }
            return new DeadlineCommand(splitted[0], splitted[1]);
        } else if (command.startsWith("event")) {
            String[] splitted = getMetadata(command).split(" /at ");
            if (splitted.length != 2) {
                throw new IrisException("event should have 2 arguments: a name and a time");
            }
            return new EventCommand(splitted[0], splitted[1]);
        } else if (command.startsWith("done")) {
            int index = parseInt(getMetadata(command));
            return new DoneCommand(index);
        } else if (command.startsWith("delete")) {
            int index = parseInt(getMetadata(command));
            return new DeleteCommand(index);
        } else if (command.startsWith("find")) {
            String searchTerm = getMetadata(command);
            return new FindCommand(searchTerm);
        } else {
            throw new IrisException("I'm sorry, but I don't know what that means.");
        }
    }
}
