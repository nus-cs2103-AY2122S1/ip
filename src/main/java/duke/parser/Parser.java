package duke.parser;

import duke.command.Command;

/**
 * Class providing static parse method for parsing user input
 */
public class Parser {

    /**
     * parse takes in user input and returns the corresponding Command enum
     *
     * @param input String[] of the user input split once by " "
     *
     * @return Command enum corresponding to the input provided
     */
    public static Command parse(String[] input)  {
        if (input[0].equals("bye") || input[0].equals("exit")) {
            return Command.EXIT;
        } else if (input[0].equals("list")) {
            return Command.LIST;
        } else  if (input[0].equals("done") || input[0].equals("delete")) {
            return Command.INDEXCOMMAND;
        } else if (input[0].equals("todo") || input[0].equals("deadline") || input[0].equals("event")) {
            return Command.ADDCOMMAND;
        } else if (input[0].equals("find")) {
            return Command.FIND;
        } else {
            return Command.UNKNOWN;
        }
    }
}
