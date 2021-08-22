import java.io.*;
import java.util.*;

public class Parser {
	public static ToDo parseToDo(String line) {
		if (line.length() <= 5) {
			throw new TaskException(new ToDo("?"));
		}
		return new ToDo(line.substring(5));
	}

	public static Deadline parseDeadline(String line) {
		int pos = line.indexOf("/by");
		if (pos == -1 || line.length() < pos + 5) {
			throw new TaskException(new Deadline("?", "?"));
		}
		return new Deadline(line.substring(9, pos-1), line.substring(pos+4));
	}

	public static Event parseEvent(String line) {
		int pos = line.indexOf("/at");
		if (pos == -1 || line.length() < pos + 5) {
			throw new TaskException(new Event("?", "?"));
		}
		return new Event(line.substring(6, pos-1), line.substring(pos+4));
	}

	public static Command parse(String line) throws DukeException, IOException {
		if (line.equals("list")) {
			return new DisplayCommand();

		} else if (line.equals("bye")) {
			return new ExitCommand();

		} else if (line.split(" ")[0].equals("done")) {
			/* todo: catch format and ioob exceptions */
			int index = Integer.parseInt(line.split(" ")[1]) - 1;
			return new MarkDoneCommand(index);

		} else if (line.split(" ")[0].equals("delete")) {
			/* todo: catch format and ioob exceptions */
			int index = Integer.parseInt(line.split(" ")[1]) - 1;
			return new DeleteCommand(index);

		} else {
			Task task = null;
			String type = line.split(" ")[0];
			if (type.equals("todo")) {
				task = Parser.parseToDo(line);
	
			} else if (type.equals("deadline")) {
				task = Parser.parseDeadline(line);
	
			} else if (type.equals("event")) {
				task = Parser.parseEvent(line);
	
			} else {
				throw new UnknownCommandException(line);
			}

			return new AddCommand(task);
		}
	}
}