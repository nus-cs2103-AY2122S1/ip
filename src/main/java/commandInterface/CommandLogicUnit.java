package commandInterface;

import model.Command;

import java.util.Map;

public interface CommandLogicUnit {
	/**
	 * function that takes a valid command from the model and the corresponding arguments (that are assumed to be correct already)
	 *
	 * @param command   one of the command from Command enum class
	 * @param arguments corresponding arguments for each command
	 */
	void processCommand(Command command, Map<String, String> arguments);
}
