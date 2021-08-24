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

import java.time.LocalDate;
import java.time.LocalDateTime;

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
	
	public LocalDateTime parseDate(String dateInput) {
		String[] parse = dateInput.split(" ");
		String date = parse[0];
		String time = parse[1];
		
		int[] separators = new int[2];
		separators[0] = date.indexOf("/");
		separators[1] = date.indexOf("/", separators[0] + 1);
		String y = date.substring(separators[1] + 1);
		String m = date.substring(separators[0] + 1, separators[1]);
		String d = date.substring(0, separators[0]);
		int year = Integer.parseInt(y);
		int month = Integer.parseInt(m);
		int day = Integer.parseInt(d);
		
		String hr = time.substring(0, 2);
		String min = time.substring(2, 4);
		String sec = time.length() == 6
				? time.substring(4, 6) : "00";
		int hour = Integer.parseInt(hr);
		int minute = Integer.parseInt(min);
		int second = Integer.parseInt(sec);
		
		return LocalDate.of(year, month, day)
				.atTime(hour, minute, second);
	}
}
