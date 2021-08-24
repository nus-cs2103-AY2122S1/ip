package bloom.app;

import bloom.command.Command;
import bloom.command.GreetCommand;
import bloom.command.ByeCommand;
import bloom.command.ListCommand;
import bloom.command.MarkCommand;
import bloom.command.DeleteCommand;
import bloom.command.ToDoCommand;
import bloom.command.DeadlineCommand;
import bloom.command.EventCommand;
import bloom.constant.Message;
import bloom.exception.command.BloomUnknownCommandException;

public class Parser {
	
	public Command parse(String userInput) throws BloomUnknownCommandException {
		String[] parse = userInput.split(" ");
		String action = parse[0];
		int descIdx, dateIdx;
		switch (action) {
		case "greet":
			return new GreetCommand();
		case "bye":
			return new ByeCommand();
		case "list":
			return new ListCommand();
		case "done":
			return new MarkCommand(Integer.parseInt(parse[1]));
		case "delete":
			return new DeleteCommand(Integer.parseInt(parse[1]));
		case "todo":
			descIdx = action.length() + 1;
			return new ToDoCommand(userInput.substring(descIdx));
		case "deadline":
			descIdx = action.length() + 1;
			dateIdx = userInput.indexOf("/");
			return new DeadlineCommand(
					userInput.substring(descIdx, dateIdx - 1),
					userInput.substring(dateIdx + 4));
		case "event":
			descIdx = action.length() + 1;
			dateIdx = userInput.indexOf("/");
			return new EventCommand(
					userInput.substring(descIdx, dateIdx - 1), 
					userInput.substring(dateIdx + 4));
		default:
			throw new BloomUnknownCommandException(
					Message.EXCEPTION_UNKNOWN_COMMAND.getMessage());
		}
	}
}
