package duke.command;

import duke.exceptions.DukeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * @@author Hang Zelin
 *
 * Parser will take in a full input Message and take out the operation type, task, time, index from the
 * one line command input by users.
 * It can also deal with the local saved data and return the parsed Message, which can be a task, time, done(or not).
 * It can also parse the time users input into the LocalDateTime.
 * Some invalid input Messages may cause throwing DukeException.
 */
public class Parser {
    private String message;
    private ParserExceptionDetector parserExceptionDetector;

    /**
     * @param message Message users take in to be parsed.
     */
    public Parser(String message) {
        this.message = message;
        this.parserExceptionDetector = new ParserExceptionDetector(message);
    }

    private static boolean isValidDate(int day, int month, int year, int hour, int minute) {

        boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0) && month == 2;
        boolean isValidLeapYearFeb = day <= 29 && day > 0;
        boolean isValidFeb =  day <= 28 && day > 0;
        boolean isValidMonth = month > 0 && month <= 12;
        boolean isValidDay =  day <= 31 && day > 0; //Is day valid for the month has 31 days.
        boolean isValidDay2 = (month == 4 || month == 6 || month == 9
                || month == 11) && day <= 30; //Is day valid for specific months.
        boolean isValidHour = hour <= 24 && hour >= 0;
        boolean isValidMinute = minute <= 60 && minute >= 0;

        if ((isLeapYear && !isValidLeapYearFeb) || (month == 2 && !isValidFeb)) {
            return false;
        }

        if (!isValidMonth || !isValidDay || !isValidDay2 || !isValidHour || !isValidMinute) {
            return false;
        }

        return true;
    }

    private LocalDateTime parseTimeInFormat1(String time) {
        int day, month, year, hour, minute;
        int endIndex1 = time.indexOf("/");
        int endIndex2 = time.lastIndexOf(" ");
        day = Integer.parseInt(time.substring(0, endIndex1));
        Integer dayInteger = day;
        int endIndex3 = time.indexOf("/", dayInteger.toString().length() + 1);

        month = Integer.parseInt(time.substring(endIndex1 + 1, endIndex3));
        year = Integer.parseInt(time.substring(endIndex3 + 1, endIndex2));
        hour = Integer.parseInt(time.substring(endIndex2 + 1).substring(0, 2));
        minute = Integer.parseInt(time.substring(endIndex2 + 1).substring(2));
        if (!isValidDate(day, month, year, hour, minute)) {
            return null;
        } else {
            return LocalDate.of(year, month, day).atTime(hour, minute);
        }
    }

    private LocalDateTime parseTimeInFormat2(String time) {
        try {
            return LocalDate.parse(time).atTime(0, 0);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Returns a LocalDateTime type that encapsulates the year, month, day, hour, minute of a time input.
     * The method takes in a String of time and converts it into a LocalDateTime
     * The format can only be: 1. dd/mm/yyyy hhmm
     * 2. yyyy-mm-dd
     *
     * @param time Time User takes in to be parsed.
     * @return Parsed time converted in the type of LocalDateTime
     */
    public LocalDateTime parseTime(String time) {
        LocalDateTime parsedTime;
        boolean isFormat1 = time.contains("/") && time.indexOf("/", 3) != -1
                && time.contains(" ") && !time.contains("-");
        boolean isFormat2 = time.contains("-");

        if (isFormat1) {
            parsedTime = parseTimeInFormat1(time);
        } else if (isFormat2) {
            parsedTime = parseTimeInFormat2(time);
        } else {
            parsedTime = null;
        }
        return parsedTime;
    }

    /**
     * Returns a String which is a task info in a local save data.
     * Note: you must specify it as local data, otherwise it can go wrong.
     *
     * @return Task retrieved from save data.
     */
    public String getSaveTask() {
        String task;
        char taskType = message.charAt(0);
        //Save Data taskType is in the form of 'D', 'E' or 'T'
        if (taskType == 'D' || taskType == 'E') {
            task = message.substring(8, message.indexOf("|", 8) - 1);
        } else {
            task = message.substring(8);
        }

        return task;
    }

    /**
     * Returns a String which is a time info in a local save data.
     * Noted: you must specify it as local data, otherwise it can go wrong.
     *
     * @return Time retrieved from save data.
     */
    public String getSaveTime() {
        String time;

        char taskType = message.charAt(0);
        //Save Data taskType is in the form of 'D', 'E' or 'T'
        if ((taskType == 'D' || taskType == 'E') && message.contains("/")) {
            time = message.substring(message.lastIndexOf("|") + 2);
        } else {
            time = "";
        }
        return time;
    }

    /**
     * Returns a String which is an operation type in a line of command.
     *
     * @return Operation type parsed from users' one line of command.
     * @throws DukeException Throws when the operation type does not belong to any one of the types that
     * duke can do.
     */
    public String getOperationType() throws DukeException {
        String operationType;

        if (message.contains(" ")) {
            operationType = message.substring(0, message.indexOf(" "));
        } else {
            operationType = message;
        }

        parserExceptionDetector.detectOperationTypeException();

        return operationType;
    }

    /**
     * Returns a String which is task info in a line of command.
     *
     * @return Operation type parsed from users' one line of command.
     * @throws DukeException Throws when the task info cannot be retrieved from users' one line of command.
     */
    public String getTask() throws DukeException{
        String task = "";

        parserExceptionDetector.detectGetTaskException();

        if (message.contains("/")) {
            task = message.substring(message.indexOf(" ") + 1, message.indexOf("/") - 1);
        } else {
            task = message.substring(message.indexOf(" ") + 1);
        }
        return task;
    }

    /**
     * Returns a String which is time info in a line of command.
     *
     * @return Time parsed from users' one line of command.
     * @throws DukeException Throws when users' the time cannot be parsed out or the parsed out time does not
     * fit the format for a specific task type.
     */
    public String getTime() throws DukeException {
        String time = "";

        parserExceptionDetector.detectGetTimeException();

        if (message.startsWith("deadline")) {
            time = message.substring(message.indexOf("/by") + 4);
        } else if (message.startsWith("event")) {
            time = message.substring(message.indexOf("/at") + 4);
        } else if (message.startsWith("tell")) {
            time = message.substring(message.indexOf(" ") + 1);
        }

        return time;
    }

    /**
     * return a String which is index info in a line of command.
     * Noted: It is possible that index does not exist. This method will only be applicable for "tell", "find",
     * "done" and "delete" operation type.
     *
     * @return Index parsed from users' one line of command if it contains an index.
     */
    public Integer getIndex() {
        int index;
        if (!parserExceptionDetector.detectIndexException()) {
            return -1;
        }

        index = Integer.parseInt(message.substring(message.indexOf(" ") + 1)) - 1;

        return index;
    }
}
