package parser;

import logic.CommandArgument;

/**
 * Command line processor interface that takes in the input from the console.
 */
public interface IParser {
    /**
     * Processes the command obtained from parseInput.
     *
     * @param argument CommandArgument.
     */
    void processCommand(CommandArgument argument);
    
    /**
     * Parses the string input into command argument that are to be passed into process Command function.
     *
     * @param input String from console.
     * @return CommandArgument.
     */
    CommandArgument parseInput(String input);
}
