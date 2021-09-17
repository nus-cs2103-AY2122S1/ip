package hyddd.logics;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import hyddd.exceptions.HydddException;
/**
 * @@author Hang Zelin
 *
 * Parser will take in a full input Message and take out the operation type, task, time, index from the
 * one line command input by users.
 * It can also deal with the local saved data and return the parsed Message, which can be a task, time, done(or not).
 * It can also parse the time users input into the LocalDateTime.
 * Some invalid input Messages may cause throwing HydddException.
 */
public class Parser {
    //Constant values
    private static final String EMPTY = "";
    private static final String SLASH = "/";
    private static final String SPACE = " ";
    private static final String DASH = "-";
    private static final String PIPE = "|";
    private static final String BY = "/by";
    private static final String AT = "/at";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String TELL = "tell";
    private static final char FIRST_LETTER_DEADLINE = 'D';
    private static final char FIRST_LETTER_EVENT = 'E';
    private final String input;
    private final ParserExceptionDetector parserExceptionDetector;

    /**
     * Constructor for Parser that takes in an input from user.
     *
     * @param input Input messsage user takes in to be parsed.
     */
    public Parser(String input) {
        this.input = input;
        this.parserExceptionDetector = new ParserExceptionDetector(input);
    }

    private static boolean isValidDate(int day, int month, int year, int hour, int minute) {

        boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0) && month == 2;
        boolean isValidLeapYearFeb = day <= 29 && day > 0;
        boolean isValidFeb = day <= 28 && day > 0;
        boolean isValidMonth = month > 0 && month <= 12;
        boolean isValidDay = day <= 31 && day > 0; //Is day valid for the month has 31 days.
        boolean isInValidDay = (month == 4 || month == 6 || month == 9
                || month == 11) && day > 30; //Is day valid for specific months.
        boolean isValidHour = hour <= 24 && hour >= 0;
        boolean isValidMinute = minute <= 60 && minute >= 0;

        if ((isLeapYear && month == 2 && !isValidLeapYearFeb)
                || (!isLeapYear && month == 2 && !isValidFeb)) {
            return false;
        }

        if (isInValidDay) {
            return false;
        }

        return isValidMonth && isValidDay && isValidHour && isValidMinute;
    }

    private LocalDateTime parseTimeInFormat1(String time) {
        int day;
        int month;
        int year;
        int hour;
        int minute;
        int endIndex1 = time.indexOf(SLASH);
        int endIndex2 = time.lastIndexOf(SPACE);
        day = Integer.parseInt(time.substring(0, endIndex1));
        int dayInteger = day;
        int endIndex3 = time.indexOf(SLASH, Integer.toString(dayInteger).length() + 1);

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
     * @return Parsed time converted in the type of LocalDateTime.
     */
    public LocalDateTime parseTime(String time) {
        LocalDateTime parsedTime;
        boolean isFormat1 = time.contains(SLASH) && time.indexOf(SLASH, 3) != -1
                && time.contains(SPACE) && !time.contains(DASH);
        boolean isFormat2 = time.contains(DASH);

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
     * Returns the key 4 information from users' input encapsulated in a ArrayList of String.
     * They are: operationType, task, time, index. They will be useful when executing in hyddd programme.
     *
     * @return Size of 4 ArrayList contains Input of operationType, task, time and index.
     * @throws HydddException Throws when the input cannot be parsed.
     */
    public ArrayList<String> returnSplitComponent() throws HydddException {
        ArrayList<String> parsedInputList = new ArrayList<>();

        parsedInputList.add(getOperationType());
        parsedInputList.add(getTask());
        parsedInputList.add(getTime());
        parsedInputList.add(getIndex().toString());

        return parsedInputList;
    }

    /**
     * Returns a String which is a task info in a local save data.
     * Note: you must specify it as local data, otherwise it can go wrong.
     *
     * @return Task retrieved from save data.
     */
    public String getSaveTask() {
        String task;
        char taskType = input.charAt(0);
        //Save Data taskType is in the form of 'D', 'E' or 'T'
        if (taskType == FIRST_LETTER_DEADLINE || taskType == FIRST_LETTER_EVENT) {
            task = input.substring(8, input.indexOf(PIPE, 8) - 1);
        } else {
            task = input.substring(8);
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

        char taskType = input.charAt(0);
        //Save Data taskType is in the form of 'D', 'E' or 'T'
        if ((taskType == FIRST_LETTER_DEADLINE || taskType == FIRST_LETTER_EVENT) && input.contains(SLASH)) {
            time = input.substring(input.lastIndexOf(PIPE) + 2);
        } else {
            time = EMPTY;
        }
        return time;
    }

    /**
     * Returns a String which is an operation type in a line of command.
     *
     * @return Operation type parsed from users' one line of command.
     * @throws HydddException Throws when the operation type does not belong to any one of the types that
     * hyddd can do.
     */
    public String getOperationType() throws HydddException {
        String operationType;

        if (input.contains(SPACE)) {
            operationType = input.substring(0, input.indexOf(SPACE));
        } else {
            operationType = input;
        }

        parserExceptionDetector.detectOperationTypeException();

        return operationType;
    }

    /**
     * Returns a String which is task info in a line of command.
     *
     * @return Operation type parsed from users' one line of command.
     * @throws HydddException Throws when the task info cannot be retrieved from users' one line of command.
     */
    public String getTask() throws HydddException {
        String task;

        parserExceptionDetector.detectGetTaskException();

        if (input.contains(SLASH)) {
            task = input.substring(input.indexOf(SPACE) + 1, input.indexOf(SLASH) - 1);
        } else {
            task = input.substring(input.indexOf(SPACE) + 1);
        }
        return task;
    }

    /**
     * Returns a String which is time info in a line of command.
     *
     * @return Time parsed from users' one line of command.
     * @throws HydddException Throws when users' the time cannot be parsed out or the parsed out time does not
     * fit the format for a specific task type.
     */
    public String getTime() throws HydddException {
        String time = EMPTY;

        parserExceptionDetector.detectGetTimeException();

        if (input.startsWith(DEADLINE)) {
            time = input.substring(input.indexOf(BY) + 4);
        } else if (input.startsWith(EVENT)) {
            time = input.substring(input.indexOf(AT) + 4);
        } else if (input.startsWith(TELL)) {
            time = input.substring(input.indexOf(SPACE) + 1);
        }

        return time;
    }

    /**
     * Returns a String which is index info in a line of command.
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

        index = Integer.parseInt(input.substring(input.indexOf(SPACE) + 1)) - 1;

        return index;
    }
}
