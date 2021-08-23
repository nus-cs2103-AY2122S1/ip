package commandimpl;

import icommand.ICommandLogicUnit;
import icommand.ICommandProcessor;
import model.Command;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static util.Display.printIndexedList;
import static util.Display.printSentence;

/**
 * An entity that takes in the input from the console and parse it to valid command or throw exception
 * if the command is invalid
 */
public class CommandProcessorImpl implements ICommandProcessor {
	/** Scanner to take input from the console */
	private final Scanner scanner = new Scanner(System.in);
	
	/** CommandLogicUnit to process all the commands */
	private final ICommandLogicUnit commandLogicUnit;
	
	private final List<String> availableCommands = List.of(
			"bye",
			"list",
			"done #index",
			"deadline #desc /by #deadline",
			"todo #desc",
			"event #desc /at #timing",
			"delete #index"
	);
	
	/**
	 * CommandProcessorImplementation that would parse the inputs into commands.
	 *
	 * @param commandLogicUnit CommandLogicUnit that would process the parsed input.
	 */
	public CommandProcessorImpl(ICommandLogicUnit commandLogicUnit) {
		this.commandLogicUnit = commandLogicUnit;
	}
	
	@Override
	public void processInput() {
		// take the next command within a line, inputted using enter
		String input = scanner.nextLine();
		
		List<String> parsedCommands = Arrays.asList(input.split(" "));
		
		processInputHelper(parsedCommands);
	}
	
	/**
	 * Processes the input as list of strings into commands.
	 * Helper method for processInput method.
	 *
	 * @param parsedCommands List of string from user inputs.
	 */
	private void processInputHelper(List<String> parsedCommands) {
		switch (parsedCommands.get(0)) {
		case "bye":
			commandLogicUnit.processCommand(Command.BYE, Map.of());
			this.close();
			break;
		case "list":
			commandLogicUnit.processCommand(Command.LIST, Map.of());
			break;
		case "done":
			try {
				commandLogicUnit.processCommand(Command.DONE, Map.of(
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
				
				commandLogicUnit.processCommand(Command.DEADLINE, Map.of(
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
				
				commandLogicUnit.processCommand(Command.TODOS, Map.of(
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
				
				commandLogicUnit.processCommand(Command.EVENT, Map.of(
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
				commandLogicUnit.processCommand(Command.DELETE, Map.of(
						"index", parsedCommands.get(1)
				));
			} catch (Exception e) {
				processException(e);
			}
			
			break;
		case "man":
			printIndexedList(availableCommands);
			break;
		default:
			processException(new IllegalCallerException("I'm sorry, but I don't know what that means :-("));
			break;
		}
	}
	
	@Override
	public void close() {
		scanner.close();
	}
	
	private void processException(Exception e) {
		printSentence("☹ OOPS!!! " + e.getMessage());
	}
}
