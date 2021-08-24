package duke;


import duke.command.*;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class Parser {

    private static String errorMessage = "Wrong format Sir/Mdm. Dates and times must be given as only a date: DATE\n"
            + "or as date and time: DATE TIME\n"
            + "Accepted formats for DATE: YYYY-MM-DD, DD/MM/YYYY\n"
            + "Accepted formats for TIME (24H format): TT:TT, TTTT\n"
            + "Examples for DATE TIME: 13/2/2019 1800, 13/2/2019 18:00, 2019-02-13 1800,\n"
            + "2019-02-13 18:00\n"
            + "Examples for DATE: 13/2/2019, 2019-02-13";

    public static Command parse(String input, Ui ui, TaskList tasks, Storage storage) throws DukeException {

        if (input.equals("bye")) {
            return new ExitCommand(ui);
        } else if (input.equals("list")) {
            return new ListCommand(tasks, ui);
        } else if (input.split(" ")[0].equals("done")) {
            String[] parsedInput = input.split(" ");

            if (parsedInput.length != 2) {
                throw (new DukeException("Please specify a task you would like marked as done Sir/Mdm:\n" + ui.list(tasks)));
            }

            int taskToMark;

            try {
                taskToMark = Integer.parseInt(parsedInput[1]) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a proper number within this range Sir/Mdm:\n" + ui.list(tasks));
            }

            if (tasks.size() == 0) {
                throw (new DukeException("You have no tasks to mark as done Sir/Mdm!"));
            }

            if (taskToMark < 0 || taskToMark > tasks.size() - 1) {
                throw (new DukeException("Please specify a task within this range Sir/Mdm:\n" + ui.list(tasks)));
            }
            return new MarkDoneCommand(tasks.get(taskToMark), tasks, ui, storage);

        } else if (input.split(" ")[0].equals("delete")) {
            String[] parsedInput = input.split(" ");

            if (parsedInput.length != 2) {
                throw (new DukeException("Please specify a task you would like to delete Sir/Mdm:\n" + ui.list(tasks)));
            }

            int taskToDelete;

            try {
                taskToDelete = Integer.parseInt(parsedInput[1]) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a proper number within this range Sir/Mdm:\n" + ui.list(tasks));
            }

            if (tasks.size() == 0) {
                throw (new DukeException("You have no tasks to delete Sir/Mdm!"));
            }

            if (taskToDelete < 0 || taskToDelete > tasks.size() - 1) {
                throw (new DukeException("Please specify a task within this range Sir/Mdm:\n" + ui.list(tasks)));
            }

            return new DeleteCommand(taskToDelete, tasks, ui, storage);

        } else if (input.split(" ")[0].equals("find")) {

            String[] values = input.split(" ");
            if (values.length != 2) {
                throw new DukeException("Please specify a date for which to find deadlines and events "
                        + "Sir/Mdm!");
            } else {
                try {
                    LocalDate date = Parser.parseDate(values[1]);
                    return new FindCommand(date, tasks, ui);
                } catch (DukeException e) {
                    throw new DukeException("Wrong format for date Sir/Mdm. Examples of dates accepted: "
                            + "2/12/2019, 2019-12-02");
                }
            }
        } else {
            Pattern todoPattern = Pattern.compile("(^(todo ))");
            Pattern deadlinePattern = Pattern.compile("(^(deadline ))");
            Pattern eventPattern = Pattern.compile("(^(event ))");

            if (todoPattern.matcher(input).find() || input.equals("todo")) {
                Task newTask = Task.taskFactory(TaskType.TODO, input);
                return new AddCommand(newTask, tasks, ui, storage);
            } else if (deadlinePattern.matcher(input).find() || input.equals("deadline")) {
                Task newTask = Task.taskFactory(TaskType.DEADLINE, input);
                return new AddCommand(newTask, tasks, ui, storage);
            } else if (eventPattern.matcher(input).find() || input.equals("event")) {
                Task newTask = Task.taskFactory(TaskType.EVENT, input);
                return new AddCommand(newTask, tasks, ui, storage);
            } else {
                throw new DukeException("Pardon me Sir/Mdm, but I do not understand.");
            }
        }
    }

    public static LocalDateTime parseDateAndTime(String dateInput, String timeInput) throws DukeException {


        LocalDate date = parseDate(dateInput);
        LocalTime time = parseTime(timeInput);
        return LocalDateTime.of(date, time);

    }

    public static String dateTimeToString(LocalDateTime dateTime, boolean isDateOnly) {

        int len = dateTime.toString().length();

        if (isDateOnly) {
            return String.format("%s %s %s", dateTime.getDayOfMonth(), dateTime.getMonth().toString().substring(0, 1)
                    + dateTime.getMonth().toString().substring(1).toLowerCase(), dateTime.getYear());
        }
        return String.format("%s %s %s %s", dateTime.getDayOfMonth(), dateTime.getMonth().toString().substring(0, 1)
                        + dateTime.getMonth().toString().substring(1).toLowerCase(), dateTime.getYear(),
                dateTime.toString().substring(len - 5));
    }


    private static LocalDate parseDate(String input) throws DukeException {
        LocalDate date;
        try {
            date = LocalDate.parse(input);
        } catch (DateTimeException e) {
            String[] dayMonthAndTime = input.split("/");
            if (dayMonthAndTime.length != 3) {
                throw new DukeException(errorMessage);
            }
            try {
                int day = Integer.parseInt(dayMonthAndTime[0]);
                int month = Integer.parseInt(dayMonthAndTime[1]);
                int year = Integer.parseInt(dayMonthAndTime[2]);

                date = LocalDate.of(year, month, day);
            } catch (DateTimeException | NumberFormatException f) {
                throw new DukeException(errorMessage);
            }
        }
        return date;
    }

    private static LocalTime parseTime(String input) throws DukeException {
        LocalTime time;
        try {
            time = LocalTime.parse(input);
        } catch (DateTimeParseException e) {
            if (input.length() != 4) {
                throw new DukeException(errorMessage);
            }
            try {
                int hour = Integer.parseInt(input.substring(0, 2));
                int minute = Integer.parseInt(input.substring(2));
                time = LocalTime.of(hour, minute);
            } catch (NumberFormatException | DateTimeException f) {
                throw new DukeException(errorMessage);
            }
        }

        return time;
    }

}
