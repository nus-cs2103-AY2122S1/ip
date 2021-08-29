package duke.util;

import java.util.Scanner;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.UnknownCommandException;

/**
 * This is a Parser class that deals with making sense of the user command.
 */
public class Parser {

    /**
     * Returns a Command instance based on the input String from the user.
     *
     * @param command A String representing the command and details keyed in by the user.
     * @return  A Command object that when its execute() method is called, make changes to the tasks list or Duke.
     * @throws EmptyDescriptionException An exception thrown when no description follows a recognizable command.
     * @throws UnknownCommandException  An exception thrown when the command is not recognizable.
     */
    public static Command decipher(String command)
            throws EmptyDescriptionException, UnknownCommandException {
        Scanner s = new Scanner(command);
        String commandType = s.next();

        switch (commandType) {
        case "bye":
            return new ExitCommand();

        case "list":
            return new ListCommand();

        case "event":
            if (!s.hasNext()) {
                throw new EmptyDescriptionException("event");
            }
            String eventDetails = s.nextLine().trim();
            return new AddCommand("event", eventDetails);

        case "deadline":
            if (!s.hasNext()) {
                throw new EmptyDescriptionException("deadline");
            }
            String deadlineDetails = s.nextLine().trim();
            return new AddCommand("deadline", deadlineDetails);

        case "todo":
            if (!s.hasNext()) {
                throw new EmptyDescriptionException("todo");
            }
            String description = s.nextLine().trim();
            return new AddCommand("todo", description);

        case "delete":
            if (!s.hasNext()) {
                throw new EmptyDescriptionException("delete");
            }
            int indexToDelete = s.nextInt() - 1;
            return new DeleteCommand(indexToDelete);

        case "done":
            if (!s.hasNext()) {
                throw new EmptyDescriptionException("done");
            }
            int indexToMark = s.nextInt() - 1;
            return new DoneCommand(indexToMark);

        case "find":
            if (!s.hasNext()) {
                throw new EmptyDescriptionException("find");
            }
            String keyword = s.nextLine().trim();
            return new FindCommand(keyword);

        default:
            throw new UnknownCommandException();
        }
    }
}
