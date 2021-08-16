package Implementation;

import Interface.CommandParser;
import Interface.CommandProcessor;
import Model.Command;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * An entity that takes in the input from the console and parse it to valid command or throw exception
 * if the command is invalid
 */
public class CommandParserImpl implements CommandParser {
	private final CommandProcessor commandProcessor;
	
	public CommandParserImpl(CommandProcessor commandProcessor) {
		this.commandProcessor = commandProcessor;
	}
	
	@Override
	public void processInput(String input) {
		List<String> parsedCommands = Arrays.asList(input.split(" "));
		
		processInputHelper(parsedCommands);
	}
	
	public void processInputHelper(List<String> parsedCommands) {
		switch (parsedCommands.get(0)) {
			case "bye":
				commandProcessor.processCommand(Command.BYE, Map.of());
				break;
			case "list":
				commandProcessor.processCommand(Command.LIST, Map.of());
				break;
			case "done":
				commandProcessor.processCommand(Command.DONE, Map.of(
						"index", parsedCommands.get(1)
				));
				break;
			case "deadline": {
				int byIndex = parsedCommands.indexOf("/by");
				String desc = String.join(" ", parsedCommands.subList(1, byIndex));
				String timing = String.join(" ", parsedCommands.subList(byIndex + 1, parsedCommands.size()));
				
				commandProcessor.processCommand(Command.DEADLINE, Map.of(
						"description", desc,
						"timing", timing
				));
				break;
			}
			case "todo": {
				String desc = String.join(" ", parsedCommands.subList(1, parsedCommands.size()));
				commandProcessor.processCommand(Command.TODOS, Map.of(
						"description", desc
				));
				break;
			}
			case "event": {
				int byIndex = parsedCommands.indexOf("/at");
				String desc = String.join(" ", parsedCommands.subList(1, byIndex));
				String timing = String.join(" ", parsedCommands.subList(byIndex + 1, parsedCommands.size()));
				
				commandProcessor.processCommand(Command.EVENT, Map.of(
						"description", desc,
						"timing", timing
				));
				break;
			}
			default:
				// add
				commandProcessor.processCommand(Command.INVALID, Map.of("message", parsedCommands.get(0)));
				break;
		}
	}
}
