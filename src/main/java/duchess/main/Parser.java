package duchess.main;

import duchess.command.Command;
import duchess.command.InvalidCommand;

/**
 * This class implements a Parser to read in user input commands.
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */
public class Parser {

    /**
     * The commands recognised by Duchess.
     */
    private enum CommandNames {
        BYE ("bye"),
        LIST ("list"),
        DONE ("done"),
        TODO ("todo"),
        DEADLINE ("deadline"),
        EVENT ("event"),
        DELETE ("delete"),
        TASKS ("tasks"),
        FIND ("find");
        private String commandName;
        CommandNames(String commandName) {
            this.commandName = commandName;
        }
    }

    /**
     * Checks if a given string is present at the front of another string.
     * @param input The string to be checked against.
     * @return The prefix enum present at the front of the string.
     * @throws DuchessException  Exception thrown when the prefix is preceded by an empty string.
     */
    public Command checkPrefix(String input) throws DuchessException {
        String[] parts = input.split(" ", 2);
        String front = parts[0];
        // Check if the prefix matches any command recognised by Duchess
        for (CommandNames c : CommandNames.values()) {
            if (!front.equals(c.commandName)) {
                // No command recognised
                continue;
            } else {
                try {
                    if (front.equals("bye") || front.equals("list")) {
                        return Command.of(front); // No need second argument
                    }
                    String back = parts[1]; // May throw ArrayIndexOutOfBoundsException
                    if (back.isBlank()) {
                        // Second argument is only whitespaces
                        throw new DuchessException("The description of " + front + " cannot be empty.");
                    }
                    return Command.of(front, back);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DuchessException("The description of " + front + " cannot be empty.");
                }
            }
        }
        // No command recognised at all
        return new InvalidCommand();
    }
}
