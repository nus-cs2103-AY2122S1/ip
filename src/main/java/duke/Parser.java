package duke;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.WrongCommand;

/**
 * Deals with making sense of user's commands.
 */
public class Parser {

    /**
     * Returns the Command to be executed after reading
     * in user's input.
     *
     * @param fullCommand User's input.
     * @return Command.
     */
    public static Command parse(String fullCommand) {
        String nextInput = fullCommand.toLowerCase();
        String[] inputArray = nextInput.split(" ");
        String commandType = inputArray[0];
        switch (commandType) {
        case "delete":
            // extract number from input
            String taskToBeRemoved = nextInput.replaceAll("[^0-9]", "");
            // number into integer
            int numToBeRemoved = Integer.parseInt(taskToBeRemoved) - 1;
            return new DeleteCommand(numToBeRemoved);
        case "done":
            // extract number from input
            String taskToBeMarked = nextInput.replaceAll("[^0-9]", "");
            // number into integer
            int numToBeMarked = Integer.parseInt(taskToBeMarked) - 1;
            return new DoneCommand(numToBeMarked);
        case "find":
            String inputToMatch = nextInput.replace("find", "").trim();
            return new FindCommand(inputToMatch);
        case "bye":
            return new ExitCommand();
        case "todo":
        case "event":
        case "deadline":
            return new AddCommand(nextInput);
        case "list":
            return new ListCommand();
        default:
            return new WrongCommand();
        }
    }
}
