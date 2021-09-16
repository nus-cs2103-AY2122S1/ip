package duke.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;

import com.zoho.hawking.HawkingTimeParser;
import com.zoho.hawking.datetimeparser.configuration.HawkingConfiguration;
import com.zoho.hawking.language.english.model.DatesFound;
import com.zoho.hawking.language.english.model.ParserOutput;

import duke.Duke;
import duke.command.Command;
import duke.exception.DateTimeFormatException;
import duke.exception.DukeException;
import duke.exception.EmptyListException;
import duke.exception.IllegalCommandException;
import duke.exception.IllegalTaskTypeException;
import duke.exception.MissingArguments;
import duke.exception.MissingParams;
import duke.exception.NothingAfterCommand;
import duke.exception.TaskIndexNotInteger;
import duke.task.Task;

/**
 * Parser handles parsing of user input.
 *
 * @author Gabriel Goh
 */
public class Parser {

    private Command.CommandTypes command;
    private int index = -1;
    private String description;
    private Task.TaskTypes taskType;
    private Temporal datetime;
    private String searchKey;

    /**
     * Parses temporal string from load file.
     *
     * @param datetime Date-time string
     * @return Temporal instance
     * @throws DateTimeFormatException Incorrect date time format given
     */
    public static Temporal parseLoadTemporal (String datetime) throws DateTimeFormatException {
        try {
            if (datetime.contains("All day")) {
                return LocalDate.parse(
                        datetime.replace("All day", "").trim(),
                        DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH));
            } else {
                return LocalDateTime.parse(
                        datetime,
                        DateTimeFormatter.ofPattern("MMM dd yyyy h.mma", Locale.ENGLISH));
            }
        } catch (DateTimeParseException e) {
            throw new DateTimeFormatException(datetime);
        }
    }

    /**
     * Parses Date or Date and Time into computer-understandable format.
     *
     * @param datetime Input date and time
     * @return Temporal instance
     * @throws DateTimeFormatException Invalid date-time format
     */
    public static Temporal parseTemporal(String datetime) throws DateTimeFormatException {
        Temporal result;
        try {
            if (datetime.trim().split("\\s+", 2).length < 2) { // no time given
                result = parseDate(datetime);
            } else {
                result = parseDateTime(datetime);
            }
        } catch (DateTimeFormatException e) {
            result = parseSmart(datetime);
        }
        assert result != null;
        return result;
    }

    /**
     * Parses using Hawking external dependency. Uses NLP.
     *
     * @param datetime Input string
     * @return Temporal instance
     * @throws DateTimeFormatException Parse failed
     */
    private static Temporal parseSmart(String datetime) throws DateTimeFormatException {
        HawkingTimeParser parser = new HawkingTimeParser();
        DateTime jodaStart;
        DateTime jodaEnd;
        try {
            DatesFound datesFound = parser.parse(datetime, new Date(), new HawkingConfiguration(), "eng");
            ParserOutput po = datesFound.getParserOutputs().get(0);
            jodaStart = po.getDateRange().getStart();
            jodaEnd = po.getDateRange().getEnd();
        } catch (Exception e) {
            throw new DateTimeFormatException(datetime + " Could not parse using Hawkings!");
        }
        assert jodaStart != null && jodaEnd != null;

        LocalDateTime ldtStart;
        LocalDateTime ldtEnd;
        try {
            ldtStart = LocalDateTime.of(
                    jodaStart.getYear(),
                    jodaStart.getMonthOfYear(),
                    jodaStart.getDayOfMonth(),
                    jodaStart.getHourOfDay(),
                    jodaStart.getMinuteOfHour()
            );
            ldtEnd = LocalDateTime.of(
                    jodaEnd.getYear(),
                    jodaEnd.getMonthOfYear(),
                    jodaEnd.getDayOfMonth(),
                    jodaEnd.getHourOfDay(),
                    jodaEnd.getMinuteOfHour()
            );
        } catch (DateTimeParseException e) {
            throw new DateTimeFormatException(datetime + " Could not convert to local date time!");
        }

        if (ldtStart.plusHours(23).plusMinutes(59).equals(ldtEnd)) {
            return ldtEnd.toLocalDate();
        } else if (ldtEnd.getHour() == 23 && ldtEnd.getMinute() == 59) {
            return ldtEnd.toLocalDate();
        } else {
            return ldtEnd;
        }
    }

    /**
     * Parses Date and Time into computer-understandable format.
     *
     * @param datetime Input date and time
     * @return LocalDateTime instance
     * @throws DateTimeFormatException Invalid date-time format
     */
    private static LocalDateTime parseDateTime(String datetime) throws DateTimeFormatException {
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
     * Parses Date into computer-understandable format.
     *
     * @param date Input date
     * @return LocalDate instance
     * @throws DateTimeFormatException Invalid date format
     */
    private static LocalDate parseDate(String date) throws DateTimeFormatException {
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

    /**
     * Parses input arguments and assign parser class variables to corresponding input.
     *
     * @param argument Input string
     * @param duke     Duke instance that called this method
     * @return ParsedInput data class storing parameters
     * @throws DukeException Invalid arguments exceptions
     */
    public ParsedInput parse(String argument, Duke duke) throws DukeException {
        String[] s = argument.trim().split("\\s+", 2);
        command = parseCommand(s[0].toUpperCase());

        switch (command) {
        // Always possible
        case BYE:
            break;

        // List cannot be empty
        case LIST:
        case SAVE:
        case LOAD:
            if (duke.taskSize() == 0) {
                throw new EmptyListException(command);
            }
            break;

        // List cannot be empty and must have args after command
        case DONE:
        case DELETE:
        case FIND:
            if (duke.taskSize() == 0) {
                throw new EmptyListException(command);
            }
            if (s.length < 2) {
                throw new NothingAfterCommand(command);
            } else {
                parseParameters(s[1], duke);
            }
            break;

        // Must have args after command
        case TODO:
        case EVENT:
        case DEADLINE:
            if (s.length < 2) {
                throw new NothingAfterCommand(command);
            } else {
                parseParameters(s[1], duke);
            }
            break;

        default:
            break;
        }
        return getParsedInput();
    }

    /**
     * Parses command of string.
     *
     * @param commandStr Command as string
     * @return Command type
     * @throws IllegalCommandException Not a command
     */
    private Command.CommandTypes parseCommand(String commandStr) throws IllegalCommandException {
        try {
            return Command.CommandTypes.valueOf(commandStr);
        } catch (IllegalArgumentException e) {
            throw new IllegalCommandException(commandStr);
        }
    }

    /**
     * Assigns parameter values to instance.
     *
     * @param argument String arguments
     * @param duke Duke instance
     * @throws DukeException Parse failed
     */
    private void parseParameters(String argument, Duke duke) throws DukeException {
        switch (command) {
        case DONE:
        case DELETE:
            parseTaskIndexOrName(argument, duke);
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
                datetime = parseTemporal(s[1]);
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
                datetime = parseTemporal(ss[1]);
            }
            break;

        case TODO:
            description = argument;
            break;

        default:
            break;
        }
    }

    /**
     * Parses task index or name.
     *
     * @param argument String argument
     * @param duke Duke instance
     * @throws DukeException Task cannot be found
     */
    private void parseTaskIndexOrName(String argument, Duke duke) throws DukeException {
        if (argument.matches("[0-9]+")) {
            try {
                index = Integer.parseInt(argument.trim()) - 1;
            } catch (NumberFormatException e) {
                throw new TaskIndexNotInteger(duke.taskSize());
            }
        } else { // Extra Functionality: Done or delete by task type and name
            String[] s = argument.split("\\s+", 2);
            if (s.length < 2) {
                throw new MissingArguments(command);
            }
            String taskType = s[0].toUpperCase();
            try {
                this.taskType = Task.TaskTypes.valueOf(taskType);
            } catch (IllegalArgumentException e) {
                throw new IllegalTaskTypeException(taskType);
            }
            description = s[1];
        }
    }

    private ParsedInput getParsedInput() {
        return new ParsedInput(
                this.command,
                this.description,
                this.datetime,
                this.index,
                this.taskType,
                this.searchKey);
    }

}
