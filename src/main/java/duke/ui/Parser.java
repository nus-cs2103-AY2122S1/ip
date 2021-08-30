package duke.ui;

import duke.command.*;
import duke.exception.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.time.format.DateTimeParseException;

/**
 * Represents a parser that deals with making sense of the user command.
 */
public class Parser {

    /**
     * Returns parsed command which involves time.
     * 
     * @param words An array of words in the command.
     * @param isEvent Whether the command is an `event` command.
     * @return Parsed command.
     * @throws DukeException If command is invalid.
     */
    private static Command parseCommandWithTime(String[] words, boolean isEvent) throws DukeException {
        // Determine key information based on type of the task
        String timeFormat = isEvent ? "yyyy-MM-dd HH:mm to yyyy-MM-dd HH:mm" : "yyyy-MM-dd HH:mm";
        String regex = isEvent ? "/at" : "/by";
        String taskType = isEvent ? "event" : "deadline";

        // Parse the command
        if (words.length >= 2) {
            String[] information = words[1].split(regex);
            if (information.length == 2) {
                try {
                    if (isEvent) {
                        return new AddTaskCommand(new Event(information[0], information[1]));
                    } else {
                        return new AddTaskCommand(new Deadline(information[0], information[1]));
                    }
                } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
                    throw new InvalidTimeException(timeFormat);
                }
            } else if (information.length < 2) {
                throw new MissingCommandDetailException("time", taskType, String.format("%s %s", regex, timeFormat));
            } else {
                throw new MultipleTimeSlotsException(taskType);
            }
        } else {
            throw new MissingCommandDetailException("description", taskType,
                    String.format("%s %s", regex, timeFormat));
        }
    }

    /**
     * Returns parsed command which involves a task number.
     *
     * @param words An array of words in the command.
     * @param isDoneCommand Whether the command is a `done` command.
     * @return Parsed command.
     * @throws InvalidTaskNoException If task number is invalid.
     */
    private static Command parseCommandWithTaskNo(
            String[] words, boolean isDoneCommand) throws InvalidTaskNoException {
        try {
            int index = Integer.parseInt(words[1]) - 1;
            return isDoneCommand ? new TaskDoneCommand(index) : new DeleteTaskCommand(index);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidTaskNoException();
        }
    }

    /**
     * Parses and returns a command from a string to a Command object.
     *
     * @param command Command received from keyboard.
     * @return Parsed command.
     * @throws DukeException If command is invalid.
     */
    public static Command parse(String command) throws DukeException {
        // Split the command into two phrases
        String[] words = command.split(" ", 2);

        // Determine type of the command and return corresponding command instance
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new GetListCommand();
        } else if (words[0].equals("done")) {
            return parseCommandWithTaskNo(words, true);
        } else if (words[0].equals("delete")) {
            return parseCommandWithTaskNo(words, false);
        } else if (words[0].equals("todo")) {
            if (words.length >= 2) {
                return new AddTaskCommand(new ToDo(words[1]));
            } else {
                throw new MissingCommandDetailException("description","todo", "");
            }
        } else if (words[0].equals("deadline")) {
            return Parser.parseCommandWithTime(words, false);
        } else if (words[0].equals("event")) {
            return Parser.parseCommandWithTime(words, true);
        } else if (words[0].equals("find")) {
            if (words.length >= 2) {
                return new FindTaskCommand(words[1].trim());
            } else {
                throw new MissingCommandDetailException("keyword","find", "");
            }
        } else {
            throw new InvalidCommandException();
        }
    }
}
