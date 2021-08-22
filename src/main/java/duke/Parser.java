package duke;

import command.*;
import dukeException.DukeException;
import task.Task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        try {
            if (fullCommand.equals("bye")) {
                return new ExitCommand();
            }
            if (fullCommand.equals("list")) {
                return new ListCommand();
            }
            if (fullCommand.length() >= 6 && fullCommand.substring(0, 4).equals("done")) {
                String s2 = fullCommand.substring(5, fullCommand.length());
                int taskIndex = Integer.parseInt(s2) - 1;
                return new DoneCommand(taskIndex);
            }
            if (fullCommand.length() >= 8 && fullCommand.substring(0, 6).equals("delete")) {
                String s2 = fullCommand.substring(7, fullCommand.length());
                int taskIndex = Integer.parseInt(s2) - 1;
                return new DeleteCommand(taskIndex);
            }
            if (fullCommand.length() >= 6 && fullCommand.substring(0, 4).equals("todo")) {
                String description = fullCommand.substring(5, fullCommand.length());
                return new AddCommand(Task.taskType.TODO, description, null, null);
            }
            if (fullCommand.length() >= 25 && fullCommand.substring(0, 8).equals("deadline")) {
                String description = fullCommand.substring(9, fullCommand.indexOf("/") - 1);
                String dateAndTime = fullCommand.substring(fullCommand.indexOf("/") + 4, fullCommand.length());
                LocalDate date = LocalDate.parse(
                        dateAndTime.substring(0, 10),
                        DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                LocalTime time = null;
                if (dateAndTime.length() > 10) {
                    time = LocalTime.parse(
                            dateAndTime.substring(dateAndTime.indexOf(" ") + 1, dateAndTime.length()),
                            DateTimeFormatter.ofPattern("HHmm"));
                }
                return new AddCommand(Task.taskType.DEADLINE, description, date, time);
            }
            if (fullCommand.length() >= 22 && fullCommand.substring(0, 5).equals("event")) {
                String description = fullCommand.substring(6, fullCommand.indexOf("/") - 1);
                String dateAndTime = fullCommand.substring(fullCommand.indexOf("/") + 4, fullCommand.length());
                LocalDate date = LocalDate.parse(
                        dateAndTime.substring(0, 10),
                        DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                LocalTime time = null;
                if (dateAndTime.length() > 10) {
                    time = LocalTime.parse(
                            dateAndTime.substring(dateAndTime.indexOf(" ") + 1, dateAndTime.length()),
                            DateTimeFormatter.ofPattern("HHmm"));
                }
                return new AddCommand(Task.taskType.EVENT, description, date, time);
            }
            throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (DukeException e) {
            throw new DukeException("Cannot parse this command: " + fullCommand);
        }
    }
}
