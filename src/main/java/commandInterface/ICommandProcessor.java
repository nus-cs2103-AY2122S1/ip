package commandInterface;

/**
 * Command line processor interface that takes in the input from the console
 */
public interface ICommandProcessor {
	/**
	 * Parse the input from console into corresponding command and process it, current implementation using Command Processor
	 */
	void processInput();
}
