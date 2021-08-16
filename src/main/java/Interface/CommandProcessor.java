package Interface;

import Model.Command;

import java.util.List;

public interface CommandProcessor {
	/**
	 * function that takes a valid command from the model and the corresponding arguments (that are assumed to be correct already)
	 *
	 * @param command one of the command from Command enum class
	 * @param arguments corresponding arguments for each command
	 */
	void processCommand(Command command, List<String> arguments);
}
