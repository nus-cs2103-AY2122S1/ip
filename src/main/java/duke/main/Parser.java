package duke.main;

import com.sun.prism.shader.Solid_RadialGradient_REFLECT_AlphaTest_Loader;
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
    private enum Commands { delete, done, filter, list, others }
    /**
     * Takes in a user command, logic decides which command to call.
     *
     * @param userInput a string of user input command.
     * @return a command object to execute the user command.
     * @throws DukeException exception handled by DukeException class.
     */
    public static Command parse(String userInput) throws DukeException {
        String action = getAction(userInput)[0];
        try {
            switch (action) {
            case "done":
                    return new MarkDoneCommand(getAction(userInput)[1]);
            case "list":
                    return new ListCommand();
            case "delete":
                    return new DeleteCommand(getAction(userInput)[1]);
            case "filter":
                    return new FilterCommand(getAction(userInput)[1]);
            case "find":
                    return new FindCommand(userInput);
            case "bye":
                    return new ExitCommand();
            default:
                    return new AddCommand(getAction(userInput)[1]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(DukeException.Exceptions.ArrayIndexOutOfBoundsException);
        }
    }
    private static String[] getAction(String userInput) {
        return userInput.split(" ", 0);
    }
}
