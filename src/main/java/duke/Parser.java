package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
        String[] details;
        String description;
        String[] dateTimeArr;
        LocalDate date;
        LocalTime time;
        LocalDateTime dateTimeInfo;
        int taskid;
        switch (inputArr[0]) {
        case "list":
            return new ListCommand();
        case "done":
            if (inputArr.length == 1) {
                throw new DukeException("Indicate the id of the task which you have completed!");
            }
            taskid = Integer.parseInt(inputArr[1]);
            return new DoneCommand(taskid);
        case "delete":
            if (inputArr.length == 1) {
                throw new DukeException("Indicate the id of the task which you want to remove!");
            }
            taskid = Integer.parseInt(inputArr[1]);
            return new DeleteCommand(taskid);
        case "todo":
            if (inputArr.length == 1) {
                throw new DukeException("Input in the format: todo *description*");
            }
            description = input.replaceFirst("todo ", "");
            return new AddCommand(new Task(description));
        case "event":
            details = input.split(" /at ");
            description = details[0].replaceFirst("event ", "");
            if (details.length == 1 || description.equals("")) {
                throw new DukeException("Input the following format: event *description* /at *DD/MM/YYYY* *24H-Time*");
            }
            try {
                dateTimeArr = details[1].split(" ");
                date = LocalDate.parse(dateTimeArr[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                time = dateTimeArr.length == 1
                        ? LocalTime.of(0, 0) :
                        LocalTime.parse(dateTimeArr[1], DateTimeFormatter.ofPattern("HHmm"));
                dateTimeInfo = LocalDateTime.of(date, time);
                return new AddCommand(new Event(description, dateTimeInfo));
            } catch (DateTimeParseException e) {
                throw new DukeException(e.getMessage());
            }
        case "deadline":
            details = input.split(" /by ");
            description = details[0].replaceFirst("deadline ", "");
            if (details.length == 1 || description.equals("")) {
                throw new DukeException("Input the following format: deadline *description* /at *DD/MM/YYYY* *24H-Time*");
            }
            try {
                dateTimeArr = details[1].split(" ");
                date = LocalDate.parse(dateTimeArr[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                time = dateTimeArr.length == 1
                        ? LocalTime.of(0, 0) :
                        LocalTime.parse(dateTimeArr[1], DateTimeFormatter.ofPattern("HHmm"));
                dateTimeInfo = LocalDateTime.of(date, time);
                return new AddCommand(new Deadline(description, dateTimeInfo));
            } catch (DateTimeParseException e) {
                throw new DukeException(e.getMessage());
            }
        case "bye":
            return new ExitCommand();
        default:
            throw new DukeException("I don't get what you mean? Try again!");
        }

    }

}
