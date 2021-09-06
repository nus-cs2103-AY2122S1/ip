package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import command.AddCommand;
import command.Command;
import command.DateCommand;
import command.DeleteCommand;
import command.DoneCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.SortByTimeCommand;
import duke.exception.DukeException;
import task.Task;

public class Parser {
    /**
     * A method to parse the given String command.
     *
     * @param fullCommand A String, the given command to be parsed.
     * @return A Command, the result of parsing the String.
     * @throws DukeException Exception thrown when the command cannot be parsed.
     */
    public static Command parse(String fullCommand) throws DukeException {
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        }
        if (fullCommand.equals("list")) {
            return new ListCommand();
        }
        if (fullCommand.equals("sort by time")) {
            return new SortByTimeCommand();
        }
        if (fullCommand.length() >= 6 && fullCommand.substring(0, 4).equals("done")) {
            String s2 = fullCommand.substring(5);
            try {
                int taskIndex = Integer.parseInt(s2) - 1;
                return new DoneCommand(taskIndex);
            } catch (NumberFormatException e) {
                throw new DukeException("Cannot parse this command.");
            }
        }
        if (fullCommand.length() >= 8 && fullCommand.substring(0, 6).equals("delete")) {
            String s2 = fullCommand.substring(7);
            try {
                int taskIndex = Integer.parseInt(s2) - 1;
                return new DeleteCommand(taskIndex);
            } catch (NumberFormatException e) {
                throw new DukeException("Cannot parse this command.");
            }
        }
        if (fullCommand.length() >= 6 && fullCommand.substring(0, 4).equals("todo")) {
            String description = fullCommand.substring(5);
            return new AddCommand(Task.TaskType.TODO, description, null, null);
        }
        if (fullCommand.length() >= 25 && fullCommand.substring(0, 8).equals("deadline")) {
            String description = fullCommand.substring(9, fullCommand.indexOf("/") - 1);
            String dateAndTime = fullCommand.substring(fullCommand.indexOf("/") + 4);
            try {
                LocalDate date = LocalDate.parse(
                        dateAndTime.substring(0, 10),
                        DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                LocalTime time = null;
                if (dateAndTime.length() > 10) {
                    time = LocalTime.parse(
                            dateAndTime.substring(dateAndTime.indexOf(" ") + 1),
                            DateTimeFormatter.ofPattern("HHmm"));
                }
                return new AddCommand(Task.TaskType.DEADLINE, description, date, time);
            } catch (DateTimeParseException e) {
                throw new DukeException("Cannot parse this command, "
                        + "please enter correct date and time format: dd-MM-yyyy HHmm");
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Cannot parse this command, "
                        + "please enter correct date and time format: dd-MM-yyyy HHmm");
            }
        }
        if (fullCommand.length() >= 22 && fullCommand.substring(0, 5).equals("event")) {
            String description = fullCommand.substring(6, fullCommand.indexOf("/") - 1);
            String dateAndTime = fullCommand.substring(fullCommand.indexOf("/") + 4);
            try {
                LocalDate date = LocalDate.parse(
                        dateAndTime.substring(0, 10),
                        DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                LocalTime time = null;
                if (dateAndTime.length() > 10) {
                    time = LocalTime.parse(
                            dateAndTime.substring(dateAndTime.indexOf(" ") + 1),
                            DateTimeFormatter.ofPattern("HHmm"));
                }
                return new AddCommand(Task.TaskType.EVENT, description, date, time);
            } catch (DateTimeParseException e) {
                throw new DukeException("Cannot parse this command, "
                        + "please enter correct date and time format: dd-MM-yyyy HHmm");
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Cannot parse this command, "
                        + "please enter correct date and time format: dd-MM-yyyy HHmm");
            }
        }
        if (fullCommand.length() >= 6 && fullCommand.substring(0, 4).equals("find")) {
            String findKeyword = fullCommand.substring(fullCommand.indexOf(" ") + 1);
            return new FindCommand(findKeyword);
        }
        if (fullCommand.length() >= 15 && fullCommand.substring(0, 4).equals("date")) {
            String dateString = fullCommand.substring(fullCommand.indexOf(" ") + 1);
            try {
                LocalDate date = LocalDate.parse(
                        dateString.substring(0, 10),
                        DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                return new DateCommand(date);
            } catch (DateTimeParseException e) {
                throw new DukeException("Cannot parse this command, "
                        + "please enter correct date and time format: dd-MM-yyyy HHmm");
            }
        }
        throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
