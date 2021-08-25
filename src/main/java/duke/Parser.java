package duke;

import duke.command.*;
import duke.exception.CorruptedFileException;
import duke.exception.DukeException;
import duke.exception.InvalidArgumentsException;
import duke.exception.InvalidTaskException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private enum TaskType {
        TODO,
        DEADLINE,
        EVENT,
    }

    public static DukeCommand parseInput(String input) {
        try {
            if (input.equals("bye")) {
                return new ExitCommand();
            } else if (input.equals("list")) {
                return new ListCommand();
            } else if (input.startsWith("done")) {
                try {
                    // filter out doneXXXX
                    // StringIndexOutOfBoundsException thrown here if input = "done",
                    // which is caught by IndexOutOfBoundsException
                    if (input.charAt(4) != ' ') {
                        throw new InvalidTaskException();
                    }
                    // NumberFormatException thrown here if substring is a invalid integer string
                    int taskNum = Integer.parseInt(input.substring(5)) - 1;
                    return new DoneCommand(taskNum);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    throw new InvalidArgumentsException();
                }
            } else if (input.startsWith("delete")) {
                try {
                    // filter out deleteXXXX
                    // StringIndexOutOfBoundsException thrown here if input = "delete",
                    // which is caught by IndexOutOfBoundsException
                    if (input.charAt(6) != ' ') {
                        throw new InvalidTaskException();
                    }
                    // NumberFormatException thrown here if substring is a invalid integer string
                    int taskNum = Integer.parseInt(input.substring(7)) - 1;
                    return new DeleteCommand(taskNum);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    throw new InvalidArgumentsException();
                }
            } else if (input.startsWith("find")) {
                try {
                    // filter out findXXXX
                    // StringIndexOutOfBoundsException thrown here if input = "find",
                    // which is caught by IndexOutOfBoundsException
                    if (input.charAt(4) != ' ') {
                        throw new InvalidTaskException();
                    }
                    // filter out "find "
                    if (input.length() == 5) {
                        throw new InvalidArgumentsException();
                    }
                    String keyWord = input.substring(5);
                    return new FindCommand(keyWord);
                } catch (IndexOutOfBoundsException e) {
                    throw new InvalidArgumentsException();
                }
            } else if (input.startsWith("todo")) {
                return new TodoCommand((ToDo) parseTask(input, TaskType.TODO));
            } else if (input.startsWith("deadline")) {
                return new DeadlineCommand((Deadline) parseTask(input, TaskType.DEADLINE));
            } else if (input.startsWith("event")) {
                return new EventCommand((Event) parseTask(input, TaskType.EVENT));
            } else {
                throw new InvalidTaskException();
            }
        } catch (DukeException e) {
            return new InvalidCommand(e);
        }
    }

    public static Task parseFileLine(String line) throws CorruptedFileException {
        String[] splitted = line.split("%,", 4);
        String input;
        TaskType type;
        boolean isDone = Boolean.parseBoolean(splitted[1]);
        switch (splitted[0]) {
        case "T":
            input = "todo " + splitted[2];
            type = TaskType.TODO;
            break;
        case "D":
            input = "deadline " + splitted[2] + " /by " + splitted[3];
            type = TaskType.DEADLINE;
            break;
        case "E":
            input = "event " + splitted[2] + " /at " + splitted[3];
            type = TaskType.EVENT;
            break;
        default:
            throw new CorruptedFileException();
        }
        try {
            return parseTask(input, type);
        } catch (InvalidArgumentsException | InvalidTaskException e) {
            throw new CorruptedFileException();
        }
    }

    private static Task parseTask(String input, TaskType type) throws InvalidArgumentsException, InvalidTaskException {
        int descriptionEnd;
        String description;
        String dateTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        Task task;
        switch (type) {
        case TODO:
            try {
                // filter out todoXXXX
                // StringIndexOutOfBoundsException thrown here if input = "todo"
                if (input.charAt(4) != ' ') {
                    throw new InvalidTaskException();
                }
                // StringIndexOutOfBoundsException thrown here if input = "todo "
                description = input.substring(5);
                // Checks if description is all whitespace
                if (description.trim().isEmpty()) {
                    throw new InvalidArgumentsException();
                }
            } catch (StringIndexOutOfBoundsException e) {
                throw new InvalidArgumentsException();
            }
            task = new ToDo(description);
            break;
        case DEADLINE:
            try {
                // filter out deadlineXXXX
                // StringIndexOutOfBoundsException thrown here if input = "deadline"
                if (input.charAt(8) != ' ') {
                    throw new InvalidTaskException();
                }
                descriptionEnd = input.indexOf(" /by ");
                // StringIndexOutOfBoundsException thrown here if input = "deadline /by "
                // or if " /by " is not present in input
                description = input.substring(9, descriptionEnd);
                // Checks if description is all whitespace
                if (description.trim().isEmpty()) {
                    throw new InvalidArgumentsException();
                }
                // StringIndexOutOfBoundsException thrown here if input = "$String /by "
                dateTime = input.substring(descriptionEnd + 5);
                // Checks if dateTime is all whitespace
                if (dateTime.trim().isEmpty()) {
                    throw new InvalidArgumentsException();
                }
                task = new Deadline(description, LocalDateTime.parse(dateTime, formatter));
            } catch (StringIndexOutOfBoundsException | DateTimeParseException e) {
                throw new InvalidArgumentsException();
            }

            break;
        case EVENT:
            try {
                // filter out eventXXXX
                // StringIndexOutOfBoundsException thrown here if input = "event"
                if (input.charAt(5) != ' ') {
                    throw new InvalidTaskException();
                }
                descriptionEnd = input.indexOf(" /at ");
                // StringIndexOutOfBoundsException thrown here if input = "event /at"
                // or if " /at " is not present in input
                description = input.substring(6, descriptionEnd);
                // Checks if description is all whitespace
                if (description.trim().isEmpty()) {
                    throw new InvalidArgumentsException();
                }
                // StringIndexOutOfBoundsException thrown here if input = "$String /at "
                dateTime = input.substring(descriptionEnd + 5);
                // Checks if dateTime is all whitespace
                if (dateTime.trim().isEmpty()) {
                    throw new InvalidArgumentsException();
                }
                task = new Event(description, LocalDateTime.parse(dateTime, formatter));
            } catch (StringIndexOutOfBoundsException | DateTimeParseException e) {
                throw new InvalidArgumentsException();
            }
            break;
        default:
            throw new InvalidTaskException();
        }
        return task;
    }
}
