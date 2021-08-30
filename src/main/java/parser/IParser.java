package parser;

/**
 * Command line processor interface that takes in the input from the console.
 */
public interface IParser {
    /**
     * Parses the input from console into corresponding command and process it,
     * current implementation using Command Processor.
     *
     * @param input String.
     */
    void processInput(String input);
}
