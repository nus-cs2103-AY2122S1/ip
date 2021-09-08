package duke.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.TreeSet;

import duke.Duke;
import duke.command.*;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

/**
 * The class that parses user input and returns parsed objects.
 */
public class Parser {

    private static String DEADLINE_DATETIME_FORMAT = "yyyy/MM/dd HH:mm";
    private static String EVENT_DATETIME_FORMAT = "yyyy/MM/dd HH:mm";
    private static String DEFAULT_TIME_STRING = "23:59";  // default time string when time is not given.
    private static String DEFAULT_DELIMITER = " ";
    private static String DEADLINE_DATETIME_FLAG = "/by";
    private static String EVENT_DATETIME_FLAG = "/at";
    private static String PRIORITY_FLAG = "-p";

    /**
     * The method takes a user input and parse out all necessary parts needed
     * to execute user command and finally returns a Command object that can be executed
     *
     * @param input             A line of user input
     * @return                  A Command object which can then be called to execute user command
     * @throws DukeException    When the input is empty String or the command is invalid
     */
    public static Command parseCommand(String input) throws DukeException {
        String[] splitInput = input.strip().split(DEFAULT_DELIMITER);
        if (splitInput.length < 1) {
            throw new DukeException("The input cannot be empty!");
        }

        String cmdType = splitInput[0].toLowerCase();
        switch(cmdType) {
        case "bye":
            return new CommandBye();
        case "done":
            return new CommandDone(parseIndex(splitInput));
        case "delete":
            return new CommandDelete(parseIndex(splitInput));
        case "list":
            return new CommandList();
        case "todo": {
            if (hasPriorityFlag(input)) {
                Priority priority = parsePriority(splitInput);
                return new CommandAdd(new Task(parseDescription(splitInput), priority));
            }

            return new CommandAdd(new Task(parseDescription(splitInput)));
        }
        case "deadline": {
            String description = parseDescription(splitInput);
            LocalDateTime dateTime = parseDeadlineDateTime(splitInput);
            return new CommandAdd(new Deadline(description, dateTime));
        }
        case "event": {
            String description = parseDescription(splitInput);
            LocalDateTime[] dateTimes = parseEventDateTime(splitInput);
            return new CommandAdd(new Event(description, dateTimes[0], dateTimes[1]));
        }
        case "find": {
            String keyword = parseKeyword(splitInput);
            return new CommandFind(keyword);
        }
        default:
            throw new DukeException("The command is invalid");
        }

    }


    /**
     * Parse and return the task index of CommandDelete and CommandDone.
     *
     * @param strArr            String array that contains split input string.
     * @return                  Parsed index.
     * @throws DukeException    When the index is missing from the input.
     */
    public static int parseIndex(String[] strArr) throws DukeException {
        if (strArr.length < 2) {
            throw new DukeException("Missing index after command");
        }
        return Integer.parseInt(strArr[1]);
    }


    /**
     * Parse and return the description of the task.
     *
     * @param strArr            String array that contains split input string.
     * @return                  description
     * @throws DukeException    When the task description is missing from input.
     */
    public static String parseDescription(String[] strArr) throws DukeException {
        StringJoiner sj = new StringJoiner(DEFAULT_DELIMITER);
        if (strArr.length >= 2 && !isDateTimeFlag(strArr[1])) {
            sj.add(strArr[1]);
        }
        for (int i = 2; i < strArr.length; i++) {
            if (isDateTimeFlag(strArr[i])) {
                break;
            }
            sj.add(strArr[i]);
        }
        if (sj.length() < 1) {
            throw new DukeException("The task description cannot be empty");
        }

        return sj.toString();
    }


    private static String parseKeyword(String[] strArr) throws DukeException {
        if (strArr.length < 2) {
            throw new DukeException("Missing keyword after command");
        }
        return strArr[1];
    }


    private static LocalDateTime parseDeadlineDateTime(String[] strArr) throws DukeException {
        int i = 1;
        while (!isDateTimeFlag(strArr[i]) && i < strArr.length) {
            i++;
        }
        if (i == strArr.length - 1) {
            throw new DukeException("The time of deadline cannot be empty");
        }

        // format should be "yyyy/MM/dd" or "yyyy/MM/dd HH:mm"
        String dateStr = strArr[i + 1];
        String timeStr = DEFAULT_TIME_STRING;
        if (strArr.length - i - 1 >= 2) {
            timeStr = strArr[i + 2];
        }

        try {
            return LocalDateTime.parse(dateStr + " " + timeStr,
                    DateTimeFormatter.ofPattern(DEADLINE_DATETIME_FORMAT));
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }

    }


    private static LocalDateTime[] parseEventDateTime(String[] strArr) throws DukeException {
        int i = 1;
        while (!isDateTimeFlag(strArr[i]) && i < strArr.length) {
            i++;
        }
        if (strArr.length - 1 - i < 3) {
            throw new DukeException("Invalid start and end dateTime format");
        }

        // format should be "yyyy/MM/dd HH:mm yyyy/MM/dd HH:mm"
        // alternatively can be "yyyy-MM-dd HH:mm HH:mm"
        String startDateStr = strArr[i + 1];
        String startTimeStr = strArr[i + 2];
        String endDateStr, endTimeStr;
        if (strArr.length - 1 - i == 3) {
            endDateStr = startDateStr;
            endTimeStr = strArr[i + 3];
        } else {
            endDateStr = strArr[i + 3];
            endTimeStr = strArr[i + 4];
        }

        try {
            LocalDateTime startDateTime = LocalDateTime
                    .parse(String.format("%s %s", startDateStr, startTimeStr),
                            DateTimeFormatter.ofPattern(EVENT_DATETIME_FORMAT));

            LocalDateTime endDateTime = LocalDateTime
                    .parse(String.format("%s %s", endDateStr, endTimeStr),
                            DateTimeFormatter.ofPattern(EVENT_DATETIME_FORMAT));

            return new LocalDateTime[]{startDateTime, endDateTime};
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }


    private static boolean hasPriorityFlag(String input) {
        return input.contains(PRIORITY_FLAG);
    }


    private static Priority parsePriority(String[] splitInput) throws DukeException {
        int idx = 0;
        for (String str : splitInput) {
            idx++;
            if (str.equals(PRIORITY_FLAG)) {
                break;
            }
        }

        String priorityStr = splitInput[idx].toUpperCase();

        switch(priorityStr) {
        case "H":
            return Priority.HIGH;
        case "M":
            return Priority.MEDIUM;
        case "L":
            return Priority.LOW;
        default:
            throw new DukeException("The specified priority is invalid!");
        }
    }

    /**
     * Encodes the taskList to formatted string to be stored.
     *
     * @param tasks The taskList to be converted to formatted string.
     * @return      Formatted string of the taskList.
     */
    public static String encode(TreeSet<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.encode() + "\n");
        }
        return sb.toString();
    }


    /**
     * Decodes lines of strings to a taskList to be loaded by Duke.
     *
     * @param lines String array containing lines of strings to be decoded.
     * @return      A taskList that contains decoded tasks.
     */
    public static TreeSet<Task> decode(String[] lines) {
        TreeSet<Task> tasks = new TreeSet<>();
        for (String line : lines) {
            String type = line.substring(0, 1);
            switch(type) {
            case "T": {
                Task todo = decodeTodo(line);
                tasks.add(todo);
                break;
            }
            case "D": {
                Deadline ddl = decodeDeadline(line);
                tasks.add(ddl);
                break;
            }
            case "E": {
                Event e = decodeEvent(line);
                tasks.add(e);
                break;
            }
            default:
                break;  // ignore unidentifiable lines.
            }
        }

        return tasks;
    }


    private static Event decodeEvent(String line) {
        String[] strArr = line.split(DEFAULT_DELIMITER);
        boolean isDone = Boolean.parseBoolean(strArr[1]);

        StringJoiner descriptionSj = new StringJoiner(DEFAULT_DELIMITER);
        StringJoiner dateTimeSj = new StringJoiner(DEFAULT_DELIMITER);
        boolean isDateTimeStr = false;
        for (int i = 2; i < strArr.length; i++) {
            String curr = strArr[i];
            if (curr.equals(EVENT_DATETIME_FLAG)) {
                isDateTimeStr = true;
                continue;
            }
            if (!isDateTimeStr) {
                descriptionSj.add(curr);
            } else {

                dateTimeSj.add(curr);
            }
        }
        String description = descriptionSj.toString();
        String[] dateTimes = dateTimeSj.toString().split(" ");
        LocalDateTime startDateTime = LocalDateTime
                .parse(String.format("%s %s", dateTimes[0], dateTimes[1]),
                        DateTimeFormatter.ofPattern(EVENT_DATETIME_FORMAT));
        LocalDateTime endDateTime = LocalDateTime
                .parse(String.format("%s %s", dateTimes[2], dateTimes[3]),
                        DateTimeFormatter.ofPattern(EVENT_DATETIME_FORMAT));

        return new Event(description, isDone, startDateTime, endDateTime);
    }


    private static Deadline decodeDeadline(String line) {
        String[] strArr = line.split(DEFAULT_DELIMITER);
        boolean isDone = Boolean.parseBoolean(strArr[1]);

        StringJoiner descriptionSj = new StringJoiner(DEFAULT_DELIMITER);
        StringJoiner dateTimeSj = new StringJoiner(DEFAULT_DELIMITER);
        boolean isDateTimeStr = false;
        for (int i = 2; i < strArr.length; i++) {
            String curr = strArr[i];
            if (curr.equals(DEADLINE_DATETIME_FLAG)) {
                isDateTimeStr = true;
                continue;
            }
            if (!isDateTimeStr) {
                descriptionSj.add(curr);
            } else {
                dateTimeSj.add(curr);
            }
        }
        String description = descriptionSj.toString();
        LocalDateTime dateTime = LocalDateTime
                .parse(dateTimeSj.toString(),
                        DateTimeFormatter.ofPattern(DEADLINE_DATETIME_FORMAT));

        return new Deadline(description, isDone, dateTime);
    }


    private static Task decodeTodo(String line) {
        String[] strArr = line.split(DEFAULT_DELIMITER);
        boolean isDone = Boolean.parseBoolean(strArr[1]);

        StringJoiner sj = new StringJoiner(DEFAULT_DELIMITER);
        for (int i = 2; i < strArr.length; i++) {
            sj.add(strArr[i]);
        }
        String description = sj.toString();

        return new Task(description, isDone);
    }


    private static boolean isDateTimeFlag(String str) {
        assert str != null;

        return str.equals(DEADLINE_DATETIME_FLAG) || str.equals(EVENT_DATETIME_FLAG);
    }
}
