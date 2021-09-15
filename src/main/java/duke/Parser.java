package duke;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DatesCommand;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;


/**
 * The parser for Duke.
 */
public class Parser {

    /** The list of tasks */
    private TaskList taskList;

    /**
     * Constructor for parser.
     * @param taskList The list of tasks.
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns a HashMap from the parsed user input string.
     * @param rawInput The user input.
     * @return HashMap from the parsed input {"command": "event", "description": "desc", ...}.
     * @throws DukeException An invalid user input will produce this exception.
     */
    public Command parseInput(String rawInput) throws DukeException {
        assert rawInput != null : "[duke.Parser.parseInput]: rawInput is null";

        // Splitting of raw input by white space.
        String[] inputs = rawInput.split("\\s+");
        if (inputs.length < 1) {
            throw new DukeException(DukeException.Errors.INVALID_COMMAND.toString());
        }

        // Check if the command is valid.
        Constant.Command command;
        String commandStr = inputs[0].toUpperCase();
        try {
            command = Constant.Command.valueOf(commandStr);
        } catch (Exception e) {
            throw new DukeException(DukeException.Errors.INVALID_COMMAND.toString());
        }

        // Process the command and the raw input.
        switch (command) {
        case LIST:
            if (inputs.length != 1) {
                throw new DukeException(DukeException.Errors.INVALID_ARGUMENT.toString()
                        + " `list` command has no arguments");
            }
            return new ListCommand(taskList);

        case DONE:
            if (inputs.length != 2) {
                throw new DukeException(DukeException.Errors.INVALID_ARGUMENT.toString()
                        + " (example: 'done 5')");
            }
            try {
                // The index in the backend is 0-based (that's why the input is subtracted by 1).
                int index = convertToInt(inputs[1]) - 1;
                return new DoneCommand(index, taskList);
            } catch (Exception e) {
                throw new DukeException(DukeException.Errors.WRONG_ARGUMENT_TYPE.toString()
                        + " (example: 'done 5')");
            }

        case TODO:
            if (inputs.length < 2) {
                throw new DukeException(DukeException.Errors.MISSING_DESCRIPTION.toString()
                        + " (example: 'todo watch Borat')");
            }
            String description = combineStringArray(inputs, 1, inputs.length);
            return new TodoCommand(description, taskList);

        case DEADLINE:
            if (inputs.length < 2) {
                throw new DukeException(DukeException.Errors.MISSING_DESCRIPTION.toString()
                        + " (example: 'deadline watch Borat /by 2021-08-21 18:00')");
            }

            // Split the deadline description and date.
            String argument = combineStringArray(inputs, 1, inputs.length);
            String[] arguments = argument.split(" /by ");
            if (arguments.length < 2) {
                throw new DukeException(DukeException.Errors.MISSING_DATE.toString()
                        + " (example: 'deadline watch Borat /by 2021-08-21 18:00')");
            } else if (arguments.length > 2) {
                throw new DukeException(DukeException.Errors.INVALID_DATE.toString()
                        + " (example: 'deadline watch Borat /by 2021-08-21 18:00')");
            }

            // Get the deadline date.
            String date = parseDateTime(arguments[1]);
            return new DeadlineCommand(arguments[0], date, taskList);

        case EVENT:
            if (inputs.length < 2) {
                throw new DukeException(DukeException.Errors.MISSING_DESCRIPTION.toString()
                        + " (example: 'event Borat concert /at 2021-08-21 18:00')");
            }

            // Split the event description and date.
            String arg = combineStringArray(inputs, 1, inputs.length);
            String[] args = arg.split(" /at ");
            if (args.length < 2) {
                throw new DukeException(DukeException.Errors.MISSING_DATE.toString()
                        + " (example: 'event watch Borat /at 2021-08-21 18:00')");
            } else if (args.length > 2) {
                throw new DukeException(DukeException.Errors.INVALID_DATE.toString()
                        + " (example: 'event watch Borat /at 2021-08-21 18:00')");
            }

            // Get the event date.
            String eventDate = parseDateTime(args[1]);
            return new EventCommand(args[0], eventDate, taskList);

        case BYE:
            if (inputs.length != 1) {
                throw new DukeException(DukeException.Errors.INVALID_ARGUMENT.toString()
                        + " `bye` command has no arguments");
            }
            return new ByeCommand();

        case DELETE:
            if (inputs.length != 2) {
                throw new DukeException(DukeException.Errors.INVALID_ARGUMENT.toString()
                        + " (example: 'delete 5')");
            }
            try {
                // The index in the backend is 0-based (that's why the input is subtracted by 1).
                int index = convertToInt(inputs[1]) - 1;
                return new DeleteCommand(index, taskList);
            } catch (Exception e) {
                throw new DukeException(DukeException.Errors.WRONG_ARGUMENT_TYPE.toString()
                        + " (example: 'delete 5')");
            }

        case HELP:
            if (inputs.length != 1) {
                throw new DukeException(DukeException.Errors.INVALID_ARGUMENT.toString()
                        + " `help` command has no arguments");
            }
            return new HelpCommand();

        case DATES:
            if (inputs.length != 1) {
                throw new DukeException(DukeException.Errors.INVALID_ARGUMENT.toString()
                        + " `dates` command has no arguments");
            }
            return new DatesCommand();

        case FIND:
            if (inputs.length < 2) {
                throw new DukeException(DukeException.Errors.MISSING_DESCRIPTION.toString()
                        + " (example: 'find book')");
            }
            String keyword = combineStringArray(inputs, 1, inputs.length);
            return new FindCommand(keyword, taskList);

        default:
            // Invalid command
            throw new DukeException(DukeException.Errors.INVALID_ARGUMENT.toString());
        }
    }

    /**
     * Returns a task from a line of the saved file.
     * @param line The line from the saved file.
     * @return A Task
     * @throws DukeException Thrown when task is invalid.
     */
    public static Task parseSavedFile(String line) throws DukeException {
        // Split by '|' character
        String[] datas = line.split(" \\| ");

        // Parsing the saved data.
        String taskType = datas[0];
        boolean isDone = datas[1].equals("1");
        Task task = null;
        switch (taskType) {
        case "T":
            // Add a todo task.
            task = new Todo(datas[2]);

            break;
        case "D":
            // Add a deadline task.
            task = new Deadline(datas[2], datas[3]);

            break;
        case "E":
            // Add an event task.
            task = new Event(datas[2], datas[3]);

            break;
        default:
            throw new DukeException(DukeException.Errors.INVALID_COMMAND.toString());
        }
        if (task != null) {
            if (isDone) {
                task.markDone();
            }
            return task;
        }
        throw new DukeException(DukeException.Errors.INVALID_COMMAND.toString());
    }

    /**
     * Returns an integer from a number in string form.
     * @param number A number in string form.
     * @return An integer form of the given string.
     * @throws DukeException when the given argument is not a number.
     */
    public static int convertToInt(String number) throws DukeException {
        assert number != null : "[duke.Parser.convertToInt]: number parameter should not be null.";
        try {
            return Integer.parseInt(number);
        } catch (Exception e) {
            throw new DukeException(DukeException.Errors.INVALID_ARGUMENT.toString());
        }
    }

    /**
     * Returns a valid date as a string from a raw date string.
     * @param input The raw date string.
     * @return A string valid as a date.
     * @throws DukeException Thrown if the input is an invalid date.
     */
    private String parseDateTime(String input) throws DukeException {
        assert input != null : "[duke.Parser.parseDate]: input parameter should not be null.";

        String[] dateTime = input.split("\\s+");
        String result = parseDate(dateTime) + parseTime(dateTime);

        // Test for validity
        try {
            LocalDateTime.parse(result);
        } catch (DateTimeParseException e) {
            throw new DukeException(DukeException.Errors.INVALID_DATE.toString());
        }
        return result;
    }

    /**
     * Returns a parsed date as a String.
     * @param dateTime String array of date and time.
     * @return A parsed date as a string.
     * @throws DukeException Thrown when date is invalid.
     */
    private String parseDate(String[] dateTime) throws DukeException {
        String date = dateTime[0].toUpperCase();
        String formatPattern = "yyyy-MM-dd";
        String result = "";
        LocalDate todayDate = LocalDate.now();
        try {
            switch (date) {
            case "TODAY":
                result += todayDate.format(DateTimeFormatter.ofPattern(formatPattern));

                break;
            case "TOMORROW":
                LocalDate tomorrowDate = todayDate.plusDays(1);
                result += tomorrowDate.format(DateTimeFormatter.ofPattern(formatPattern));

                break;
            case "MON":
            case "MONDAY":
                LocalDate nextMonday = todayDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
                result += nextMonday.format(DateTimeFormatter.ofPattern(formatPattern));

                break;
            case "TUE":
            case "TUESDAY":
                LocalDate nextTuesday = todayDate.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
                result += nextTuesday.format(DateTimeFormatter.ofPattern(formatPattern));

                break;
            case "WED":
            case "WEDNESDAY":
                LocalDate nextWednesday = todayDate.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
                result += nextWednesday.format(DateTimeFormatter.ofPattern(formatPattern));

                break;
            case "THU":
            case "THURSDAY":
                LocalDate nextThursday = todayDate.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
                result += nextThursday.format(DateTimeFormatter.ofPattern(formatPattern));

                break;
            case "FRI":
            case "FRIDAY":
                LocalDate nextFriday = todayDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
                result += nextFriday.format(DateTimeFormatter.ofPattern(formatPattern));

                break;
            case "SAT":
            case "SATURDAY":
                LocalDate nextSaturday = todayDate.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
                result += nextSaturday.format(DateTimeFormatter.ofPattern(formatPattern));

                break;
            case "SUN":
            case "SUNDAY":
                LocalDate nextSunday = todayDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
                result += nextSunday.format(DateTimeFormatter.ofPattern(formatPattern));

                break;
            default:
                String[] date1 = date.split("-");
                String[] date2 = date.split("/");
                if (date1.length == 3 || date2.length == 3) {
                    result = date1.length == 3 ? stringToDate(date1) : stringToDate(date2);
                } else {
                    throw new DukeException(DukeException.Errors.INVALID_DATE.toString());
                }
            }
        } catch (Exception e) {
            throw new DukeException(DukeException.Errors.INVALID_DATE.toString());
        }
        return result;
    }

    /**
     * Returns a String representation of the parsed time.
     * @param dateTime The String Array of date and time.
     * @return The String representation of the parsed time.
     * @throws DukeException Thrown when a time is invalid.
     */
    private String parseTime(String[] dateTime) throws DukeException {
        if (dateTime.length == 2) {
            return "T" + stringToTime(dateTime[1]);
        } else {
            return "T23:59";
        }
    }

    /**
     * Returns a valid time as a string.
     * @param time The time in the form of a string
     * @return The string representation of the time
     * @throws DukeException Thrown if the time is invalid
     */
    private String stringToTime(String time) throws DukeException {
        assert time != null : "[duke.Parser.stringToTime]: time parameter should not be null.";
        String[] splitTime = time.split(":");
        if (splitTime.length > 2 || splitTime.length < 1) {
            throw new DukeException(DukeException.Errors.INVALID_TIME.toString());
        }
        for (String s : splitTime) {
            try {
                // Check if all the string are numbers:
                convertToInt(s);
            } catch (Exception e) {
                System.out.println("Time is not a number");
                throw new DukeException(DukeException.Errors.INVALID_TIME.toString());
            }
        }
        if (splitTime.length == 2) {
            // in the form of [hh, mm]
            if ((splitTime[0].length() == 2 || splitTime[0].length() == 1)
                    && (splitTime[1].length() == 2)) {
                String hh = String.format("%02d", convertToInt(splitTime[0]));
                String mm = String.format("%02d", convertToInt(splitTime[1]));
                return hh + ":" + mm;
            }
        } else {
            // in the form of [hhmm]
            if (splitTime[0].length() == 3 || splitTime[0].length() == 4) {
                String hh = splitTime[0].length() == 3 ? splitTime[0].substring(0, 1) : splitTime[0].substring(0, 2);
                String mm = splitTime[0].length() == 3 ? splitTime[0].substring(1, 3) : splitTime[0].substring(2, 4);
                hh = String.format("%02d", convertToInt(hh));
                mm = String.format("%02d", convertToInt(mm));
                return hh + ":" + mm;
            }
        }
        throw new DukeException(DukeException.Errors.INVALID_TIME.toString());
    }

    /**
     * Returns a valid date string from a date array.
     * @param date A String array e.g. [yyyy, mm, dd].
     * @return Null if invalid, else a string representation of the date -> yyyy-mm-dd.
     * @throws DukeException An invalid date will produce this
     */
    private String stringToDate(String[] date) throws DukeException {
        assert date != null : "[duke.Parser.stringToDate]: date parameter should not be null.";
        // can be [yyyy, mm, dd] or [dd, mm, yyyy]
        try {
            // Check if all the string are numbers:
            convertToInt(date[0]);
            convertToInt(date[1]);
            convertToInt(date[2]);
        } catch (Exception e) {
            throw new DukeException(DukeException.Errors.INVALID_DATE.toString() + " Date is not a number.");
        }

        if (date[0].length() == 4
                && (date[1].length() == 1 || date[1].length() == 2)
                && (date[2].length() == 1 || date[2].length() == 2)) {
            // In the form of [yyyy, mm, dd]
            String year = date[0];
            String month = String.format("%02d", convertToInt(date[1]));
            String day = String.format("%02d", convertToInt(date[2]));
            return year + "-" + month + "-" + day;
        } else if ((date[0].length() == 1 || date[0].length() == 2)
                && (date[1].length() == 1 || date[1].length() == 2)
                && (date[2].length() == 4)
        ) {
            // In the form of [dd, mm, yyyy]
            String year = date[2];
            String month = String.format("%02d", convertToInt(date[1]));
            String day = String.format("%02d", convertToInt(date[0]));
            return year + "-" + month + "-" + day;
        }
        throw new DukeException(DukeException.Errors.INVALID_DATE.toString());
    }


    /**
     * Returns a string by combining an array of strings into a space separated sentence.
     * @param arr The string array.
     * @param start The starting index to be combined (inclusive).
     * @param exclusiveEnd The ending index (exclusive).
     * @return The sentence.
     */
    private String combineStringArray(String[] arr, int start, int exclusiveEnd) {
        assert arr != null : "[duke.Parser.combineStringArray]: arr input should not be null.";
        StringBuilder tmp = new StringBuilder();
        if (exclusiveEnd > arr.length) {
            exclusiveEnd = arr.length;
        }
        for (int i = start; i < exclusiveEnd; ++i) {
            if (i + 1 >= exclusiveEnd) {
                tmp.append(arr[i]);
            } else {
                tmp.append(arr[i]).append(" ");
            }
        }
        return tmp.toString();
    }

}
