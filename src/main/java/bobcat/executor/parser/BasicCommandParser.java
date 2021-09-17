package bobcat.executor.parser;

/**
 * Implements a <code>CommandParser</code> which process basic commands i.e. commands that do read-only operations
 * Such commands include "list", "find", and "bye".
 */
public class BasicCommandParser implements CommandParser {
    /**
     * Process read-only commands.
     * @param command The given basic command
     * @param commandArgs Arguments to the basic command
     * @return Array of strings, where the 0th index is occupied by command, followed by the arguments to the command
     */
    @Override
    public String[] parse(String command, String[] commandArgs) {
        return commandArgs;
    }
}
