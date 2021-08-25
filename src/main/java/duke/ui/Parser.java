package duke.ui;

import duke.command.*;
import duke.exception.*;
import duke.task.Event;
import duke.task.ToDo;

import java.time.format.DateTimeParseException;

/**
 * Represents a parser that deals with making sense of the user command.
 */
public class Parser {

    private static Command parseCommandWithTime(String[] splitted, String taskType) throws DukeException {
        String timeFormat = taskType.equals("event") ? "yyyy-MM-dd HH:mm to yyyy-MM-dd HH:mm" : "yyyy-MM-dd HH:mm";
        String regex = taskType.equals("event") ? "/at" : "/by";
        if (splitted.length >= 2) {
            String[] information = splitted[1].split(regex);
            if (information.length == 2) {
                try {
                    return new AddTaskCommand(new Event(information[0], information[1]));
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
     * Based on the command received, either quit the program or process an event.
     */
    public static Command parse(String command) throws DukeException {
        String[] splitted = command.split(" ", 2);

        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new GetListCommand();
        } else if (splitted[0].equals("done")) {
            int index = Integer.parseInt(splitted[1]) - 1;
            return new TaskDoneCommand(index);
        } else if (splitted[0].equals("delete")) {
            int index = Integer.parseInt(splitted[1]) - 1;
            return new DeleteTaskCommand(index);
        } else if (splitted[0].equals("todo")) {
            if (splitted.length >= 2) {
                return new AddTaskCommand(new ToDo(splitted[1]));
            } else {
                throw new MissingCommandDetailException("description","todo", "");
            }
        } else if (splitted[0].equals("deadline")) {
            return Parser.parseCommandWithTime(splitted, "deadline");
        } else if (splitted[0].equals("event")) {
            return Parser.parseCommandWithTime(splitted, "event");
        } else {
            throw new InvalidCommandException();
        }
    }
}
