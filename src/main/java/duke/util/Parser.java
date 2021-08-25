package duke.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

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

    /**
     * The method takes a user input and parse out all necessary parts needed
     * to execute user command and finally returns a Command object that can be executed
     * @param input A line of user input
     * @return A Command object which can then be called to execute user command
     * @throws DukeException When the input is empty String or the command is invalid
     */
    public static Command parseCommand(String input) throws DukeException {
        String[] splited = input.strip().split(" +");
        if(splited.length < 1) {
            throw new DukeException("The input cannot be empty!");
        }

        String cmdType = splited[0].toLowerCase();
        switch (cmdType) {
        case "bye":
            return new CommandBye();
        case "done":
            return new CommandDone(parseIndex(splited));
        case "delete":
            return new CommandDelete(parseIndex(splited));
        case "list":
            return new CommandList();
        case "todo": {
            String description = parseDescription(splited);
            return new CommandAdd(new Task(description));
        }
        case "deadline": {
            String description = parseDescription(splited);
            LocalDateTime dateTime = parseDeadlineDateTime(splited);
            return new CommandAdd(new Deadline(description, dateTime));
        }
        case "event": {
            String description = parseDescription(splited);
            LocalDateTime[] dateTimes = parseEventDateTime(splited);
            return new CommandAdd(new Event(description, dateTimes[0], dateTimes[1]));
        }
        case "find": {
            String keyword = parseKeyword(splited);
            return new CommandFind(keyword);
        }
        default:
            throw new DukeException("The command is invalid");
        }

    }

    /**
     * Parse and return the task index of CommandDelete and CommandDone.
     * @param strArr String array that contains splited input string.
     * @return parsedIndex
     * @throws DukeException when the index is missing from the input.
     */
    public static int parseIndex(String[] strArr) throws DukeException {
        if(strArr.length < 2) {
            throw new DukeException("Missing index after command");
        }
        return Integer.parseInt(strArr[1]);
    }

    /**
     * Parse and return the description of the task.
     * @param strArr String array that contains splited input string.
     * @return description
     * @throws DukeException when the task description is missing from input.
     */
    public static String parseDescription(String[] strArr) throws DukeException {
        StringBuilder sb = new StringBuilder();
        if(strArr.length >= 2 && !isDateTimeDelim(strArr[1])) {
            sb.append(strArr[1]);
        }
        for(int i = 2; i < strArr.length; i++) {
            if(isDateTimeDelim(strArr[i])) {
                break;
            }
            sb.append(" " + strArr[i]);
        }
        if(sb.length() < 1) {
            throw new DukeException("The task description cannot be empty");
        }

        return sb.toString();
    }

    private static String parseKeyword(String[] strArr) throws DukeException {
        if(strArr.length < 2) {
            throw new DukeException("Missing keyword after command");
        }
        return strArr[1];
    }

    private static LocalDateTime parseDeadlineDateTime(String[] strArr) throws DukeException {
        int i = 1;
        while(!isDateTimeDelim(strArr[i]) && i < strArr.length) {
            i++;
        }
        if(i == strArr.length - 1) {
            throw new DukeException("The time of deadline cannot be empty");
        }

        // format should be "yyyy/MM/dd" or "yyyy/MM/dd HH:mm"
        String dateStr = strArr[i + 1];
        String timeStr = "23:59";  // default value in case time is missing from input
        if(strArr.length - i - 1 >= 2) {
            timeStr = strArr[i + 2];
        }

        try {
            return LocalDateTime.parse(dateStr + " " + timeStr,
                    DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }

    }

    private static LocalDateTime[] parseEventDateTime(String[] strArr) throws DukeException {
        int i = 1;
        while(!isDateTimeDelim(strArr[i]) && i < strArr.length) {
            i++;
        }
        if(strArr.length - 1 - i < 3) {
            throw new DukeException("Invalid start and end dateTime format");
        }

        // format should be "yyyy/MM/dd HH:mm yyyy/MM/dd HH:mm"
        // alternativly can be "yyyy-MM-dd HH:mm HH:mm"
        String startDateStr = strArr[i + 1];
        String startTimeStr = strArr[i + 2];
        String endDateStr, endTimeStr;
        if(strArr.length - 1 - i == 3) {
            endDateStr = startDateStr;
            endTimeStr = strArr[i + 3];
        } else {
            endDateStr = strArr[i + 3];
            endTimeStr = strArr[i + 4];
        }


        try {
            LocalDateTime startDateTime = LocalDateTime
                    .parse(String.format("%s %s", startDateStr, startTimeStr),
                            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));

            LocalDateTime endDateTime = LocalDateTime
                    .parse(String.format("%s %s", endDateStr, endTimeStr),
                            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));

            return new LocalDateTime[] {startDateTime, endDateTime};
        } catch(Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    private static boolean isDateTimeDelim(String str) {
        return str.equals("/by") || str.equals("/at");
    }

    public static String encode(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        for(Task task : tasks) {
            sb.append(task.encode() + "\n");
        }
        return sb.toString();
    }

    public static List<Task> decode(String[] lines) {
        List<Task> tasks = new ArrayList<>();
        for (String line : lines) {
            String type = line.substring(0,1);
            switch (type) {
            case "T": {
                String[] strArr = line.split(" ");
                boolean isDone = Boolean.parseBoolean(strArr[1]);

                StringJoiner sj = new StringJoiner(" ");
                for(int i = 2; i < strArr.length; i++) {
                    sj.add(strArr[i]);
                }
                String description = sj.toString();

                tasks.add(new Task(description, isDone));
                break;
            }
            case "D": {
                String[] strArr = line.split(" ");
                boolean isDone = Boolean.parseBoolean(strArr[1]);

                StringJoiner descriptionSj = new StringJoiner(" ");
                StringJoiner dateTimeSj = new StringJoiner(" ");
                boolean isDateTimeStr = false;
                for(int i = 2; i < strArr.length; i++) {
                    String curr = strArr[i];
                    if(curr.equals("/by")) {
                        isDateTimeStr = true;
                        continue;
                    }
                    if(!isDateTimeStr) {
                        descriptionSj.add(curr);
                    } else {
                        dateTimeSj.add(curr);
                    }
                }
                String description = descriptionSj.toString();
                LocalDateTime dateTime = LocalDateTime
                        .parse(dateTimeSj.toString(),
                                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));

                tasks.add(new Deadline(description, isDone, dateTime));
                break;
            }
            case "E": {
                String[] strArr = line.split(" ");
                boolean isDone = Boolean.parseBoolean(strArr[1]);

                StringJoiner descriptionSj = new StringJoiner(" ");
                StringJoiner dateTimeSj = new StringJoiner(" ");
                boolean isDateTimeStr = false;
                for(int i = 2; i < strArr.length; i++) {
                    String curr = strArr[i];
                    if(curr.equals("/at")) {
                        isDateTimeStr = true;
                        continue;
                    }
                    if(!isDateTimeStr) {
                        descriptionSj.add(curr);
                    } else {

                        dateTimeSj.add(curr);
                    }
                }
                String description = descriptionSj.toString();
                String[] dateTimes = dateTimeSj.toString().split(" ");
                LocalDateTime startDateTime = LocalDateTime
                        .parse(String.format("%s %s", dateTimes[0], dateTimes[1]),
                                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
                LocalDateTime endDateTime = LocalDateTime
                        .parse(String.format("%s %s", dateTimes[2], dateTimes[3]),
                                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));

                tasks.add(new Event(description, isDone, startDateTime, endDateTime));
                break;
            }
            }
        }

        return tasks;
    }
}
