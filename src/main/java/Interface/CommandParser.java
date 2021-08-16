package Interface;

public interface CommandParser {
	/**
	 * Parse the input from console into corresponding command and process it, current implementation using Command Processor
	 *
	 * @param input String containing the lines
	 */
	void processInput(String input);
}
