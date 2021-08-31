package duke;

import duke.commands.*;

/**
 * Encapsulates a parser class which parses user input
 * and creates a different command object depending on the input.
 */
public class Parser {
    private final Command[] COMMAND_LIST = {
            new ListCommand(""),
            new ByeCommand(""),
            new TodoCommand(""),
            new FormatsCommand(""),
            new DoneCommand(""),
            new DeleteCommand(""),
            new DeadlineCommand(""),
            new EventCommand(""),
            new FindCommand(""),
    };

    /**
     * Takes in a string input from the user and returns the invoked command object.
     *
     * @param input The string input by the user.
     * @return The created command object.
     */
    public Command parse(String input) {
        for(Command c : COMMAND_LIST) {
            if(input.startsWith(c.startsWith())) {
                if (input.equals(c.startsWith())) {
                    return c.of("");
                }
                return c.of(input.substring(c.startsWith().length()+1));
            }
        }
        return null;
    }

}
