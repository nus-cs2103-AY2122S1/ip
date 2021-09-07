package duke;


import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindByDateCommand;
import duke.command.FindByDescriptionCommand;
import duke.command.ListCommand;
import duke.command.MarkDoneCommand;
import duke.command.SortCommand;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;

/**
 * The Parser class is responsible for parsing all user commands and input dates and times for Duke.
 *
 * @author Aiken Wong
 */
public class Parser {

    private static final String ERROR_MESSAGE = "Wrong format Sir/Mdm. Dates and times must be given as only a date: "
        + "DATE\n"
        + "or as date and time: DATE TIME\n"
        + "Accepted formats for DATE: YYYY-MM-DD, DD/MM/YYYY\n"
        + "Accepted formats for TIME (24H format): TT:TT, TTTT\n"
        + "Examples for DATE TIME: 13/2/2019 1800, 13/2/2019 18:00, 2019-02-13 1800,\n"
        + "2019-02-13 18:00\n"
        + "Examples for DATE: 13/2/2019, 2019-02-13";


    /**
     * Returns an executable Command after parsing the user's input.
     * If the command is invalid, a DukeException is thrown
     *
     * @param input   User command line input.
     * @param ui      A Duke user interface object.
     * @param tasks   List of all tasks in the user's current task list.
     * @param storage Current storage object used to store the user's current tasks.
     * @return Executable command.
     * @throws DukeException
     */
    public static Command parse(String input, Ui ui, TaskList tasks, Storage storage) throws DukeException {
        if (input.equals("bye")) {
            return new ExitCommand(ui);
        } else if (input.equals("list")) {
            return new ListCommand(tasks, ui);
        } else if (input.split(" ")[0].equals("done")) {
            return generateMarkTaskCommand(input, ui, tasks, storage);
        } else if (input.split(" ")[0].equals("delete")) {
            return generateDeleteTaskCommand(input, ui, tasks, storage);
        } else if (input.split(" ")[0].equals("find/date")) {
            return generateFindByDateCommand(input, ui, tasks);
        } else if (input.split(" ")[0].equals("find")) {
            return generateFindByDescriptionCommand(input, ui, tasks);
        } else if (input.equals("sort")) {
            return generateSortCommand(ui, tasks, storage);
        } else if (input.split(" ")[0].equals("todo") || input.split(" ")[0].equals("event")
            || input.split(" ")[0].equals("deadline")) {
            return generateAddCommand(input, ui, tasks, storage);
        } else {
            throw new DukeException("Pardon me Sir/Mdm, but I do not understand.");
        }
    }


    /**
     * Parses date and time Strings into a LocalDateTime object.
     *
     * @param dateInput The input String for date.
     * @param timeInput The input Sting for time.
     * @return LocalDateTime object.
     * @throws DukeException
     */
    public static LocalDateTime parseDateAndTime(String dateInput, String timeInput) throws DukeException {
        LocalDate date = parseDate(dateInput);
        LocalTime time = parseTime(timeInput);
        return LocalDateTime.of(date, time);
    }

    /**
     * Formats LocalDateTime object into String for UI display.
     *
     * @param dateTime   The LocalDateTime object to be formatted.
     * @param isDateOnly Determines whether time will be displayed along with the date.
     * @return Formatted date and/or time String.
     */
    public static String getDateTimeString(LocalDateTime dateTime, boolean isDateOnly) {

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
                throw new DukeException(ERROR_MESSAGE);
            }
            try {
                int day = Integer.parseInt(dayMonthAndTime[0]);
                int month = Integer.parseInt(dayMonthAndTime[1]);
                int year = Integer.parseInt(dayMonthAndTime[2]);

                date = LocalDate.of(year, month, day);
            } catch (DateTimeException | NumberFormatException f) {
                throw new DukeException(ERROR_MESSAGE);
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
                throw new DukeException(ERROR_MESSAGE);
            }
            try {
                int hour = Integer.parseInt(input.substring(0, 2));
                int minute = Integer.parseInt(input.substring(2));
                time = LocalTime.of(hour, minute);
            } catch (NumberFormatException | DateTimeException f) {
                throw new DukeException(ERROR_MESSAGE);
            }
        }

        return time;
    }

    private static Command generateMarkTaskCommand(String input, Ui ui, TaskList tasks, Storage storage)
        throws DukeException {
        String[] parsedInput = input.split(" ");
        int taskToMark;

        // No task given to mark
        if (parsedInput.length != 2) {
            throw (new DukeException("Please specify a task you would like marked as done Sir/Mdm:\n"
                + ui.list(tasks)));
        }
        try {
            taskToMark = Integer.parseInt(parsedInput[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a proper number within this range Sir/Mdm:\n" + ui.list(tasks));
        }


        // No tasks in task list
        if (tasks.size() == 0) {
            throw (new DukeException("You have no tasks to mark as done Sir/Mdm!"));
        }

        // Task not in enumerated range
        if (taskToMark < 0 || taskToMark > tasks.size() - 1) {
            throw (new DukeException("Please specify a task within this range Sir/Mdm:\n" + ui.list(tasks)));
        }
        return new MarkDoneCommand(tasks.get(taskToMark), tasks, ui, storage);
    }

    private static Command generateDeleteTaskCommand(String input, Ui ui, TaskList tasks, Storage storage)
        throws DukeException {
        String[] parsedInput = input.split(" ");
        int taskToDelete;
        //No task given to delete
        if (parsedInput.length != 2) {
            throw (new DukeException("Please specify a task you would like to delete Sir/Mdm:\n"
                + ui.list(tasks)));
        }
        try {
            taskToDelete = Integer.parseInt(parsedInput[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a proper number within this range Sir/Mdm:\n" + ui.list(tasks));
        }

        // No tasks in task list
        if (tasks.size() == 0) {
            throw (new DukeException("You have no tasks to delete Sir/Mdm!"));
        }

        // Task not in enumerated range
        if (taskToDelete < 0 || taskToDelete > tasks.size() - 1) {
            throw (new DukeException("Please specify a task within this range Sir/Mdm:\n" + ui.list(tasks)));
        }
        return new DeleteCommand(taskToDelete, tasks, ui, storage);
    }

    private static Command generateFindByDateCommand(String input, Ui ui, TaskList tasks) throws DukeException {
        String[] values = input.split(" ");
        // No date given
        if (values.length != 2) {
            throw new DukeException("Please specify a date for which to find deadlines and events "
                + "Sir/Mdm!");
        } else {
            try {
                LocalDate date = Parser.parseDate(values[1]);
                return new FindByDateCommand(date, tasks, ui);
            } catch (DukeException e) {
                throw new DukeException("Wrong format for date Sir/Mdm. Examples of dates accepted: "
                    + "2/12/2019, 2019-12-02");
            }
        }
    }

    private static Command generateFindByDescriptionCommand(String input, Ui ui, TaskList tasks) {
        String searchPhrase = input.substring(4).trim();
        return new FindByDescriptionCommand(searchPhrase, tasks, ui);
    }

    private static Command generateAddCommand(String input, Ui ui, TaskList tasks, Storage storage)
        throws DukeException {
        Pattern todoPattern = Pattern.compile("(^(todo ))");
        Pattern deadlinePattern = Pattern.compile("(^(deadline ))");
        Pattern eventPattern = Pattern.compile("(^(event ))");

        if (todoPattern.matcher(input).find() || input.equals("todo")) {
            Task newTask = Task.of(TaskType.TODO, input);
            return new AddCommand(newTask, tasks, ui, storage);
        } else if (deadlinePattern.matcher(input).find() || input.equals("deadline")) {
            Task newTask = Task.of(TaskType.DEADLINE, input);
            return new AddCommand(newTask, tasks, ui, storage);
        } else if (eventPattern.matcher(input).find() || input.equals("event")) {
            Task newTask = Task.of(TaskType.EVENT, input);
            return new AddCommand(newTask, tasks, ui, storage);
        } else {
            throw new DukeException("Pardon me Sir/Mdm, but I do not understand.");
        }
    }

    private static Command generateSortCommand(Ui ui, TaskList tasks, Storage storage) {
        return new SortCommand(tasks, ui, storage);
    }

}
