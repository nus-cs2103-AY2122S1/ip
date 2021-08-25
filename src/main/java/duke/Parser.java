package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Locale;

/**
 * Parser handles parsing of user input.
 *
 * @author Gabriel Goh
 */
public class Parser {

    public Duke.Commands command;
    public boolean haveParameters;
    public int index = -1;
    public String description;
    public Duke.TaskTypes taskTypes;
    public String by;
    public String at;
    public String searchKey;

    private Duke.Commands parseCommand(String commandStr) throws IllegalCommandException {
        try {
            return Duke.Commands.valueOf(commandStr);
        } catch (IllegalArgumentException e) {
            throw new IllegalCommandException(commandStr);
        }
    }

    /**
     * Parse input arguments and assign parser class variables to corresponding input.
     *
     * @param argument Input string
     * @param duke     Duke instance that called this method
     * @throws DukeException Invalid arguments exceptions
     */
    public void parse(String argument, Duke duke) throws DukeException {
        String[] s = argument.trim().split("\\s+", 2);
        command = parseCommand(s[0].toUpperCase());

        switch (command) {
        case LIST:
        case SAVE:
            if (duke.taskSize() == 0) {
                throw new EmptyListException(command);
            }
        case BYE:
            haveParameters = false;      // should have nothing after command
            break;
        case DONE:
        case DELETE:
        case FIND:
            if (duke.taskSize() == 0) {
                throw new EmptyListException(command);
            }
        default:
            if (s.length < 2) {
                throw new NothingAfterCommand(command);
            } else {
                haveParameters = parseParameters(s[1], duke);
            }
        }
    }

    private boolean parseParameters(String argument, Duke duke) throws DukeException {
        switch (command) {
        case DONE:
        case DELETE:
            if (argument.matches("[0-9]+")) {
                try {
                    index = Integer.parseInt(argument.trim()) - 1;
                } catch (NumberFormatException e) {
                    throw new TaskIndexNotInteger(duke.taskSize());
                }
            } else {        // Extra Functionality: Done or delete by task type and name
                String[] s = argument.split("\\s+", 2);
                if (s.length < 2) {
                    throw new MissingArguments(command);
                }
                String taskType = s[0].toUpperCase();
                try {
                    taskTypes = Duke.TaskTypes.valueOf(taskType);
                } catch (IllegalArgumentException e) {
                    throw new IllegalTaskTypeException(taskType);
                }
                description = s[1];
            }
            break;

        case FIND:
            searchKey = argument.trim();
            break;

        case DEADLINE:
            if (!argument.contains("/by")) {
                throw new MissingParams("by");
            }

            String[] s = argument.split(" /by ", 2);
            description = s[0];

            if (s.length < 2) {
                throw new MissingArguments(command);
            } else {
                by = s[1];
            }
            break;

        case EVENT:
            if (!argument.contains("/at")) {
                throw new MissingParams("at");
            }

            String[] ss = argument.split(" /at ", 2);
            description = ss[0];

            if (ss.length < 2) {
                throw new MissingArguments(command);
            } else {
                at = ss[1];
            }
            break;

        case TODO:
            description = argument;
            break;

        default:
            break;
        }
        return true;    // parsed succesfully
    }

    /**
     * Parse Date and Time into computer-understandable format.
     *
     * @param datetime Input date and time
     * @return LocalDateTime instance
     * @throws DateTimeFormatException Invalid date-time format
     */
    public static LocalDateTime parseDateTime(String datetime) throws DateTimeFormatException {
        try {
            return LocalDateTime.parse(datetime,
                    new DateTimeFormatterBuilder()
                            .parseCaseInsensitive()
                            .parseLenient()
                            .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy"))
                            .appendOptional(DateTimeFormatter.ofPattern("yyyy-M-d"))
                            .appendOptional(DateTimeFormatter.ofPattern("dMMMyyyy"))
                            .parseDefaulting(ChronoField.YEAR_OF_ERA, 2021)
                            .appendOptional(DateTimeFormatter.ofPattern("d/M"))
                            .appendOptional(DateTimeFormatter.ofPattern("dMMM"))
                            .appendLiteral(" ")
                            .appendOptional(DateTimeFormatter.ofPattern("HHmm"))
                            .appendOptional(DateTimeFormatter.ofPattern("h.mma"))
                            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                            .appendOptional(DateTimeFormatter.ofPattern("ha"))
                            .toFormatter(Locale.ENGLISH));
        } catch (DateTimeParseException e) {
            throw new DateTimeFormatException(datetime);
        }
    }

    /**
     * Parse Date into computer-understandable format.
     *
     * @param date Input date
     * @return LocalDate instance
     * @throws DateTimeFormatException Invalid date format
     */
    public static LocalDate parseDate(String date) throws DateTimeFormatException {
        try {
            return LocalDate.parse(date,
                    new DateTimeFormatterBuilder()
                            .parseCaseInsensitive()
                            .parseLenient()
                            .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy"))
                            .appendOptional(DateTimeFormatter.ofPattern("yyyy-M-d"))
                            .appendOptional(DateTimeFormatter.ofPattern("dMMMyyyy"))
                            .parseDefaulting(ChronoField.YEAR_OF_ERA, 2021)
                            .appendOptional(DateTimeFormatter.ofPattern("d/M"))
                            .appendOptional(DateTimeFormatter.ofPattern("dMMM"))
                            .toFormatter(Locale.ENGLISH));
        } catch (DateTimeParseException e) {
            throw new DateTimeFormatException(date);
        }
    }

}
