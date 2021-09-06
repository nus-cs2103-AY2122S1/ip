package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.TagCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

/**
 * Parser class which handles the processing of the inputs detected
 * by the User Interface.
 */

public class Parser {
    /**
     * Parses the input from the user.
     *
     * @param input The input inserted by the user.
     * @return The respective commands to be executed based on the input.
     * @throws DukeException This exception is thrown when the input is invalid.
     */
    public static Command parse(String input) throws DukeException {
        String[] inputArr = input.split(" ");
        switch (inputArr[0]) {
        case "list":
           return parseList();
        case "done":
            return parseDone(inputArr);
        case "delete":
           return parseDelete(inputArr);
        case "todo":
            return parseToDo(inputArr);
        case "event":
            return parseEvent(input);
        case "deadline":
            return parseDeadline(input);
        case "bye":
            return new ExitCommand();
        case "find":
            return parseFind(inputArr);
        case "tag":
            return parseTag(inputArr);
        case "/help":
            return new HelpCommand();
        default:
            throw new DukeException("I don't get what you mean? Try again!");
        }
    }

    public static Command parseList() {
        return new ListCommand();
    }

    public static Command parseDone(String[] inputArr) {
        if (inputArr.length == 1) {
            throw new DukeException("Indicate the id of the task which you have completed, "
                    + "seperated by commas for multiple ids!");
        }
        String[] strTaskId = inputArr[1].split(",");
        int numTasks = strTaskId.length;
        int[] taskIds = new int[numTasks];
        for (int i = 0; i < numTasks; i++) {
            taskIds[i] = Integer.parseInt(strTaskId[i]);
        }
        return new DoneCommand(taskIds);
    }

    public static Command parseDelete(String[] inputArr) {
        if (inputArr.length == 1) {
            throw new DukeException("Indicate the id of the task which you want to remove, "
                    + "seperated by commas for multiple ids!");
        }
        String[] strTaskId = inputArr[1].split(",");
        int numTasks = strTaskId.length;
        int[] taskIds = new int[numTasks];
        for (int i = 0; i < numTasks; i++) {
            taskIds[i] = Integer.parseInt(strTaskId[i]);
        }
        return new DeleteCommand(taskIds);
    }

    public static Command parseToDo(String[] inputArr) {
        if (inputArr.length == 1) {
            throw new DukeException("Input in the format: todo *description*");
        }

        String description = "";
        for (int i = 1; i < inputArr.length; i++) {
            if (inputArr[i].charAt(0) != '(') {
                description += (" " +inputArr[i]);
            } else {
                String duration = inputArr[i];
                duration = duration.replaceAll("[()]", "");
                return new AddCommand(new Task(description, duration));
            }
        }
        return new AddCommand(new Task(description));
    }

    public static Command parseEvent(String input) {
        String[] details = input.split(" /at ");
        String description = details[0].replaceFirst("event ", "");
        if (details.length == 1 || description.equals("")) {
            throw new DukeException("Input the following format: event *description* /at *DD/MM/YYYY* *24H-Time*");
        }
        try {
            String[] dateTimeArr = details[1].split(" ");
            LocalDate date = LocalDate.parse(dateTimeArr[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalTime time = dateTimeArr.length == 1
                    ? LocalTime.of(0, 0)
                    : LocalTime.parse(dateTimeArr[1], DateTimeFormatter.ofPattern("HHmm"));
            LocalDateTime dateTimeInfo = LocalDateTime.of(date, time);
            return new AddCommand(new Event(description, dateTimeInfo));
        } catch (DateTimeParseException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public static Command parseDeadline(String input) {
        String[] details = input.split(" /by ");
        String description = details[0].replaceFirst("deadline ", "");
        if (details.length == 1 || description.equals("")) {
            throw new DukeException("Input the following format: "
                    + "deadline *description* /at *DD/MM/YYYY* *24H-Time*");
        }
        try {
            String[] dateTimeArr = details[1].split(" ");
            LocalDate date = LocalDate.parse(dateTimeArr[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalTime time = dateTimeArr.length == 1
                    ? LocalTime.of(0, 0)
                    : LocalTime.parse(dateTimeArr[1], DateTimeFormatter.ofPattern("HHmm"));
            LocalDateTime dateTimeInfo = LocalDateTime.of(date, time);
            return new AddCommand(new Deadline(description, dateTimeInfo));
        } catch (DateTimeParseException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public static Command parseFind(String[] inputArr) {
        if (inputArr.length == 1) {
            throw new DukeException("Input the keyword to be used for the search!");
        } else {
            return new FindCommand(inputArr[1]);
        }
    }

    public static Command parseTag(String[] inputArr) {
        if (inputArr.length < 3) {
            throw new DukeException("Indicate the task and the tag with the format: tag *task* *tag*!");
        } else {
            return new TagCommand(inputArr[1], inputArr[2]);
        }
    }

}
