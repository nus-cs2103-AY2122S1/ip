package commandInterface;

/**
 * Command line processor interface that takes in the input from the console
 */
public interface CommandProcessor {
	/**
	 * Parse the input from console into corresponding command and process it, current implementation using Command Processor
	 *
	 * @param input String containing the lines
	 */
	void processInput(String input);
}
