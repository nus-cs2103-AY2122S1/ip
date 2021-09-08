package duke.ui;

import java.time.format.DateTimeParseException;

import duke.command.*;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidTaskNoException;
import duke.exception.InvalidTimeException;
import duke.exception.MissingCommandDetailException;
import duke.exception.MultipleTimeSlotsException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

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

        if (words.length < 2) {
            // If there is only one word in the command, the description is missing.
            throw new MissingCommandDetailException("description", taskType,
                    String.format("%s %s", regex, timeFormat));
        }
        String[] information = words[1].split(regex);
        if (information.length < 2) {
            // time is missing
            throw new MissingCommandDetailException("time", taskType, String.format("%s %s", regex, timeFormat));
        }
        if (information.length > 2) {
            // more than one time slots are given
            throw new MultipleTimeSlotsException(taskType);
        }
        try {
            Task task = isEvent
                    ? new Event(information[0], information[1])
                    : new Deadline(information[0], information[1]);
            return new AddTaskCommand(task);
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidTimeException(timeFormat);
        }
    }

    /**
     * Returns parsed command which involves a task number.
     *
     * @param words An array of words in the command.
     * @return Parsed command.
     * @throws InvalidTaskNoException If task number is invalid.
     */
    private static Command parseCommandWithTaskNo(
            String[] words) throws InvalidTaskNoException {
        String leadingWord = words[0];
        try {
            int index = Integer.parseInt(words[1]) - 1;
            return leadingWord.equals("done")
                    ? new TaskDoneCommand(index)
                    : leadingWord.equals("undone")
                        ? new TaskUndoneCommand(index)
                        : new DeleteTaskCommand(index);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidTaskNoException();
        }
    }

    private static Command parseTodo(String[] words) throws MissingCommandDetailException {
        if (words.length < 2) {
            throw new MissingCommandDetailException("description", "todo", "");
        }
        return new AddTaskCommand(new ToDo(words[1]));
    }

    private static Command parseFind(String[] words) throws MissingCommandDetailException {
        if (words.length < 2) {
            throw new MissingCommandDetailException("keyword", "find", "");
        }
        return new FindTaskCommand(words[1].trim());
    }

    private static Command parseCommandWithTwoOrMoreWords(String[] words) throws DukeException {
        String leadingWord = words[0];
        if (leadingWord.equals("done")) {
            return Parser.parseCommandWithTaskNo(words);
        } else if (leadingWord.equals("undone")) {
            return Parser.parseCommandWithTaskNo(words);
        } else if (leadingWord.equals("delete")) {
            return Parser.parseCommandWithTaskNo(words);
        } else if (leadingWord.equals("todo")) {
            return Parser.parseTodo(words);
        } else if (leadingWord.equals("deadline")) {
            return Parser.parseCommandWithTime(words, false);
        } else if (leadingWord.equals("event")) {
            return Parser.parseCommandWithTime(words, true);
        } else if (leadingWord.equals("find")) {
            return Parser.parseFind(words);
        } else {
            throw new InvalidCommandException();
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
        // Determine type of the command and return corresponding command instance
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new GetListCommand();
        } else {
            // Split the command into two phrases
            String[] words = command.split(" ", 2);
            return Parser.parseCommandWithTwoOrMoreWords(words);
        }
    }
}
