package bobcat.executor.parser;

/**
 * Interface that all command parsers in BobCat.BobCat must obey. A <code>CommandParser</code> checks if the given
 * command and commandArgs are appropriate for each other. Specifically, it checks for the number of given arguments.
 */
public interface CommandParser {
    String[] parse(String command, String[] commandArgs);
}
