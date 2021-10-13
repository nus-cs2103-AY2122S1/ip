package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;


public class Parser {

    /**
     * Takes in user's input and convert it into a command obj.
     *
     * @param input User's raw input
     * @return A command object
     * @throws IncompleteCommandException if command is incomplete
     */
    public static Command parse(String input) throws IncompleteCommandException {
        if (input.toUpperCase().equals(CommandList.BYE.toString())) {
            return new ByeCommand(input);
        } else if (input.toUpperCase().equals(CommandList.LIST.toString())) {
            return new ListCommand(input);
        } else if (input.toUpperCase().contains(CommandList.DELETE.toString())) {
            return new DeleteCommand(input);
        } else if (isDone(input)) {
            return new DoneCommand(input);
        } else if (input.toUpperCase().contains(CommandList.FIND.toString())) {
            return new FindCommand(input.strip());
        } else if (input.toUpperCase().contains(CommandList.TODO.toString())) {
            return new TodoCommand(input.strip());
        } else if (input.toUpperCase().contains(CommandList.DEADLINE.toString())) {
            return new DeadlineCommand(input.strip());
        } else if (input.toUpperCase().contains(CommandList.EVENT.toString())) {
            return new EventCommand(input.strip());
        }
        return new InvalidCommand("");
    }

    /**
     * Check if a given input has done command.
     *
     * @param input a string that is the input of the user.
     * @return a boolean if done command is found.
     */
    public static boolean isDone(String input) {
        if (input.length() >= 4) {
            return input.toUpperCase().startsWith(CommandList.DONE.toString());
        } else {
            return false;
        }
    }

    /**
     * Check if input string is numeric or not.
     *
     * @param input a string input from user.
     * @return a boolean if input is numeric.
     */
    public static boolean isNumeric(String input) {
        if (input == null) {
            return false;
        }
        try {
            int num = Integer.parseInt(input);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
