package Implementation;

import Interface.CommandParser;
import Interface.CommandProcessor;
import Model.Command;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static Util.Display.printSentence;

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
	
	private void processInputHelper(List<String> parsedCommands) {
		switch (parsedCommands.get(0)) {
			case "bye":
				commandProcessor.processCommand(Command.BYE, Map.of());
				break;
			case "list":
				commandProcessor.processCommand(Command.LIST, Map.of());
				break;
			case "done":
				try {
					commandProcessor.processCommand(Command.DONE, Map.of(
							"index", parsedCommands.get(1)
					));
				} catch (Exception e) {
					processException(e);
				}
				
				break;
			case "deadline": {
				try {
					int byIndex = parsedCommands.indexOf("/by");
					if (byIndex == -1) throw new IllegalArgumentException("/by command not found");
					
					String desc = String.join(" ", parsedCommands.subList(1, byIndex));
					if (desc.isBlank()) throw new IllegalArgumentException("deadline desc cannot be empty");
					
					String timing = String.join(" ", parsedCommands.subList(byIndex + 1, parsedCommands.size()));
					if (timing.isBlank()) throw new IllegalArgumentException("deadline timing cannot be empty");
					
					commandProcessor.processCommand(Command.DEADLINE, Map.of(
							"description", desc,
							"timing", timing
					));
				} catch (Exception e) {
					processException(e);
				}
				
				break;
			}
			case "todo": {
				try {
					String desc = String.join(" ", parsedCommands.subList(1, parsedCommands.size()));
					
					if (desc.isBlank()) throw new IllegalArgumentException("todo desc cannot be empty");
					
					commandProcessor.processCommand(Command.TODOS, Map.of(
							"description", desc
					));
				} catch (Exception e) {
					processException(e);
				}
				break;
			}
			case "event": {
				try {
					int byIndex = parsedCommands.indexOf("/at");
					if (byIndex == -1) throw new IllegalArgumentException("/at command not found");
					
					String desc = String.join(" ", parsedCommands.subList(1, byIndex));
					if (desc.isBlank()) throw new IllegalArgumentException("event desc cannot be empty");
					
					String timing = String.join(" ", parsedCommands.subList(byIndex + 1, parsedCommands.size()));
					if (timing.isBlank()) throw new IllegalArgumentException("event timing cannot be empty");
					
					commandProcessor.processCommand(Command.EVENT, Map.of(
							"description", desc,
							"timing", timing
					));
				} catch (Exception e) {
					processException(e);
				}
				
				break;
			}
			case "delete":
				try {
					commandProcessor.processCommand(Command.DELETE, Map.of(
							"index", parsedCommands.get(1)
					));
				} catch (Exception e) {
					processException(e);
				}
				
				break;
			default:
				processException(new IllegalCallerException("I'm sorry, but I don't know what that means :-("));
				break;
		}
	}
	
	private void processException(Exception e) {
		printSentence("☹ OOPS!!! " + e.getMessage());
	}
}
