package duke.main;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FilterCommand;
import duke.command.ListCommand;
import duke.command.MarkDoneCommand;

/**
 * Represents a abstraction to read user input.
 * 
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */
public class Parser {
    
    public enum Commands { done, list, delete, filter, others};
    
    private Parser() {
    }
    
    /**
     * Takes in a user command, logic decides which command to call.
     * @param command
     * 
     * @return the command to call based on user command.
     */
    public static Command parse(String command) throws DukeException {
        String[] commandDescription = command.split(" ", 0);
        String action = commandDescription[0];
        try {
            switch (action) {
                case "done":
                    return new MarkDoneCommand(Integer.parseInt(commandDescription[1]));
                case "list":
                    return new ListCommand();
                case "delete":
                    return new DeleteCommand(Integer.parseInt(commandDescription[1]));
                case "filter":
                    return new FilterCommand(commandDescription[1]);
                case "bye":
                    return new ExitCommand();
                default:
                    return new AddCommand(command);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(e);
        } catch (NumberFormatException e) {
            throw new DukeException(e);
        }
    }
}
