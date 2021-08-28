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
     * @param fullCommand User's input.
     * @return Command.
     */
    public static Command parse(String fullCommand) {
        String nextInput = fullCommand.toLowerCase();
        if (nextInput.contains("delete")) {
            // extract number from input
            String taskToBeRemoved = nextInput.replaceAll("[^0-9]", "");
            // number into integer
            int numToBeRemoved = Integer.parseInt(taskToBeRemoved) - 1;
            return new DeleteCommand(numToBeRemoved);

        } else if (nextInput.contains("done")) {
            // extract number from input
            String taskToBeMarked = nextInput.replaceAll("[^0-9]", "");
            // number into integer
            int numToBeMarked = Integer.parseInt(taskToBeMarked) - 1;
            return new DoneCommand(numToBeMarked);

        } else if (nextInput.contains("find")) {
            String inputToMatch = nextInput.replace("find", "").trim();
            return new FindCommand(inputToMatch);

        } else if (nextInput.contains("bye")) {
            return new ExitCommand();

        } else if (nextInput.contains("todo") || nextInput.contains("event") || nextInput.contains("deadline")) {
            return new AddCommand(nextInput);

        } else if (nextInput.contains("list")) {
            return new ListCommand();
        } else {
            return new WrongCommand();
        }
    }

}
