package duke;

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


/**
 * Parser handles parsing of user input.
 *
 * @author Gabriel Goh
 */
public class Parser {

    private Duke.Commands command;
    private int index = -1;
    private String description;
    private Duke.TaskTypes taskType;
    private Temporal datetime;
    private String searchKey;
    private boolean haveParameters;

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
        HawkingTimeParser parser = new HawkingTimeParser();
        DateTime jodaStart;
        DateTime jodaEnd;
        try {
            DatesFound datesFound = parser.parse(datetime, new Date(), new HawkingConfiguration(), "eng");
            ParserOutput po = datesFound.getParserOutputs().get(0);
            jodaStart = po.getDateRange().getStart();
            jodaEnd = po.getDateRange().getEnd();
        } catch (Exception e) {
            throw new DateTimeFormatException(datetime);
        }
        assert jodaStart != null && jodaEnd != null;
        Temporal result;
        try {
            LocalDateTime ldtStart = LocalDateTime.of(
                    jodaStart.getYear(),
                    jodaStart.getMonthOfYear(),
                    jodaStart.getDayOfMonth(),
                    jodaStart.getHourOfDay(),
                    jodaStart.getMinuteOfHour()
            );
            LocalDateTime ldtEnd = LocalDateTime.of(
                    jodaEnd.getYear(),
                    jodaEnd.getMonthOfYear(),
                    jodaEnd.getDayOfMonth(),
                    jodaEnd.getHourOfDay(),
                    jodaEnd.getMinuteOfHour()
            );

            if (ldtStart.plusHours(23).plusMinutes(59).equals(ldtEnd)) {
                result = ldtEnd.toLocalDate();
            } else if (ldtEnd.getHour() == 23 && ldtEnd.getMinute() == 59) {
                result = ldtEnd.toLocalDate();
            } else {
                result = ldtEnd;
            }

        } catch (DateTimeParseException e) {
            throw new DateTimeFormatException(datetime + "could not convert to local date time!");
        }
        assert result != null;
        return result;
        /*
        if (datetime.trim().split("\\s+", 2).length < 2) { // no time given
            return parseDate(datetime);
        } else {
            return parseDateTime(datetime);
        }
        */
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
     * Parse input arguments and assign parser class variables to corresponding input.
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
        case LIST:
        case SAVE:
            if (duke.taskSize() == 0) {
                throw new EmptyListException(command);
            }
        case LOAD:
        case BYE:
            haveParameters = false; // should have nothing after command
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
        return getParsedInput();
    }

    private Duke.Commands parseCommand(String commandStr) throws IllegalCommandException {
        try {
            return Duke.Commands.valueOf(commandStr);
        } catch (IllegalArgumentException e) {
            throw new IllegalCommandException(commandStr);
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
            } else { // Extra Functionality: Done or delete by task type and name
                String[] s = argument.split("\\s+", 2);
                if (s.length < 2) {
                    throw new MissingArguments(command);
                }
                String taskType = s[0].toUpperCase();
                try {
                    this.taskType = Duke.TaskTypes.valueOf(taskType);
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
        return true; // parsed succesfully
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
