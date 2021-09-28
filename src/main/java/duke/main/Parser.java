package duke.main;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FilterCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkDoneCommand;

/**
 * Represents a abstraction to read user input.
 *
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */
public class Parser {

    private enum Commands { delete, done, filter, list, deadline, event, todo }
    /**
     * Takes in a user command, logic decides which command to call.
     *
     * @param userInput a string of user input command.
     * @return a command object to execute the user command.
     * @throws DukeException exception handled by DukeException class.
     */
    public static Command parse(String userInput) throws DukeException {
        String commandAction = splitActionAndDetails(userInput)[0];
        try {
            switch (commandAction) {
            case "done":
                return new MarkDoneCommand(splitActionAndDetails(userInput)[1]);
            case "list":
                return new ListCommand();
            case "delete":
                return new DeleteCommand(splitActionAndDetails(userInput)[1]);
            case "filter":
                return new FilterCommand(splitActionAndDetails(userInput)[1]);
            case "find":
                return new FindCommand(userInput);
            case "bye":
                return new ExitCommand();
            case "deadline" :
            case "event" :
            case "todo" :
                return new AddCommand(userInput);
            default:
                assert commandAction != "bye" : "action is not bye";
                throw new IllegalArgumentException();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(DukeException.Exceptions.ArrayIndexOutOfBoundsException);
        } catch (IllegalArgumentException e) {
            throw new DukeException(DukeException.Exceptions.EXCEPTIONS);
        }
    }
    private static String[] splitActionAndDetails(String userInput) {
        return userInput.split(" ", 0);
    }
}
