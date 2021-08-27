package duke.parser;

import duke.ui.UserInterface;
import duke.task.TaskList;
import duke.exception.DukeException;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is responsible for the parsing of all user inputs, as well as file
 * contents back into tasks during the initial loading of file.
 */
public class Parser {

    private static final String[] months = new String[] { "JANUARY", "FEBRUARY", "MARCH",
        "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER",
        "DECEMBER" };
    private static final int OFFSET = 7;

    /**
     * A default constructor for the Parser class.
     */
    public Parser() {}

    private final static UserInterface USER_INTERFACE = new UserInterface();

    /**
     * This method is responsible for detecting what the user wants. It allows the
     * program to respond based on the first input word by the user.
     *
     * @param command the input provided by the user through the command line
     * @param scanner the scanner that reads in the user's input
     * @return true if there is a request to terminate execution and false otherwise
     */
    public boolean firstCommandParser(String command, Scanner scanner) {
        TaskList task = new TaskList();

        switch (command) {
        case "bye":
            USER_INTERFACE.exit();
            return true;
        case "done":
            String stringIndex = scanner.next();
            try {
                int index = Integer.parseInt(stringIndex) - 1;
                if (task.markDone(index)) {
                    USER_INTERFACE.taskComplete(TaskList.getTask(index));
                }
                break;
            } catch (NumberFormatException e) {
                USER_INTERFACE.integerInputWarning();
                break;
            }
        case "list":
            TaskList.displayList();
            break;
        case "delete":
            try {
                String stringIndex2 = scanner.next();
                int index2 = Integer.parseInt(stringIndex2) - 1;
                TaskList deletedTask = TaskList.getTask(index2);
                if (deletedTask == null) {
                    break;
                }
                task.delete(index2);
                USER_INTERFACE.taskDeleted(deletedTask);
                break;
            } catch (NumberFormatException e) {
                USER_INTERFACE.integerInputWarning();
                break;
            }
        case "find":
            String searchWord = scanner.next();
            USER_INTERFACE.printArrayList(TaskList.findMatching(searchWord));
            break;
        default:
            String remaining = command.concat(" " + scanner.nextLine());
            try {
                task.add(remaining);
                break;
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
        return false;
    }

    /**
     * This method detects the format of a yyyy-mm-dd date in a sentence
     * and extracts it out for use.
     *
     * @param input the input string that is typically the timeline given by the user
     * @return LocalDate which represents the date in a more readable format
     */
    public static LocalDate findDate(String input) {
        String regex = "(\\d{4}-\\d{2}-\\d{2})"; // Regex to find date of the form yyyy-mm-dd
        Matcher m = Pattern.compile(regex).matcher(input);
        if (m.find()) {
            return LocalDate.parse(m.group(1));
        }
        return null;
    }

    /**
     * This method detects the format of a 4 integer timing in a sentence
     * and extracts it out for use.
     *
     * @param input the input string that is typically the timeline given by the user
     * @return the timing inputted by the user in String format
     */
    public static String findTime(String input) {
        StringBuilder sb = new StringBuilder();
        int index = input.length() - 1;
        for (int i = 0; i < 4; i++) {
            sb.append(input.charAt(index));
            index--;
        }
        sb.reverse();
        String regex = "^\\d{4}$";
        Matcher m = Pattern.compile(regex).matcher(sb.toString());
        if (m.find()) {
            return sb.toString();
        } else {
            return null;
        }
    }

    /**
     * This method detects the format of a 4 integer to 4 integer
     * range timing in a sentence.
     *
     * @param input the input string that is typically the timeline given by the user
     * @return the time range inputted by the user, contained in a size 2 array
     */
    public static String[] findTimeRange(String input) {
        StringBuilder sb = new StringBuilder();
        int index = input.length() - 1;
        for (int i = 0; i < 9; i++) {
            sb.append(input.charAt(index));
            index--;
        }
        sb.reverse();
        String regex = "^\\d{4}-\\d{4}$";
        Matcher m = Pattern.compile(regex).matcher(sb.toString());
        if (m.find()) {
            return sb.toString().split("-");
        } else {
            return null;
        }
    }

    /**
     * This method converts the String input 24-hour time format
     * into a 12-hour format with A.M. and P.M.
     *
     * @param input 24-hour time format
     * @return 12-hour time format
     */
    public static String convertTime(String input) {
        double time = Double.parseDouble(input);
        String postfix;
        String prefix;
        time = time % 2400;
        if (time < 1300) {
            postfix = time >= 1200
                    ? "PM"
                    : "AM";
            time = time / 100.0;
        } else {
            postfix = "PM";
            time = (time - 1200.0) / 100.0;
        }
        prefix = String.format("%.2f", time);
        return prefix + " " + postfix;
    }

    /**
     * Assigns the reference variables of LocalDate and String to its respective
     * values based on the input from the file storage. This method is specific
     * to parsing Deadline tasks from the file storage.
     *
     * @param input the timing description originally provided by the user
     * @param ld LocalDate reference variable that the method is to assign
     * @param deadlineTiming String reference variable that the method is to assign
     */
    public static void parseDeadlineTime(String input, LocalDate ld, String deadlineTiming) {
        String[] parsedTime = input.split(" ");
        try {
            String timeFormat = parsedTime[2] + "-"
                    + Parser.matchMonth(parsedTime[1]) + "-"
                    + (parsedTime[0].length() == 1 ? "0" + parsedTime[0] : parsedTime[0]);
            if (parsedTime.length > 3) {
                double timing = Double.parseDouble(parsedTime[3]) * 100
                        + (parsedTime[4].equals("PM") ? 1200 : 0);
                int flattenedTiming = (int) timing;
                deadlineTiming = Integer.toString(flattenedTiming);
            }
            ld = LocalDate.parse(timeFormat);
        } catch (ArrayIndexOutOfBoundsException e) {
            USER_INTERFACE.nullFunction();
        }
    }

    /**
     * Assigns the reference variables of LocalDate and 2 Strings to its respective
     * values based on the input from the file storage. This method is specific
     * to parsing Event tasks from the file storage.
     *
     * @param input the timing description originally provided by the user
     * @param ld LocalDate reference variable that the method is to assign
     * @param startTime String reference variable that the method is to assign
     * @param endTime String reference variable that the method is to assign
     */
    public static void parseEventTime(String input, LocalDate ld, String startTime, String endTime) {
        String[] parsedTime = input.split(" ");
        try {
            String timeFormat = parsedTime[2] + "-"
                    + Parser.matchMonth(parsedTime[1]) + "-"
                    + (parsedTime[0].length() == 1 ? "0" + parsedTime[0] : parsedTime[0]);
            if (parsedTime.length > 3) {
                double timingStart = Double.parseDouble(parsedTime[3]) * 100
                        + (parsedTime[4].equals("PM") ? 1200 : 0);
                double timingEnd = Double.parseDouble(parsedTime[6]) * 100
                        + (parsedTime[7].equals("PM") ? 1200 : 0);
                int flattenedTimingStart = (int) timingStart;
                int flattenedTimingEnd = (int) timingEnd;
                startTime = Integer.toString(flattenedTimingStart);
                endTime = Integer.toString(flattenedTimingEnd);
            }
            ld = LocalDate.parse(timeFormat);
        } catch (ArrayIndexOutOfBoundsException e) {
            USER_INTERFACE.nullFunction();
        }
    }

    /**
     * Matches the input month in String to its month in numbers.
     *
     * @param month String month such as May
     * @return month in numbers, in String format, such as 05
     */
    private static String matchMonth(String month) {
        int index = 0;
        for (int i = 0; i < months.length; i++) {
            if (month.equals(months[i])) {
                index = i + 1;
                break;
            }
        }
        return index < 10
                ? "0" + index
                : Integer.toString(index);
    }

    /**
     * This method is responsible for the parsing of contents from
     * the file storage during the initial execution of the program.
     * This method breaks down the information to be provided to the
     * task class to create the respective tasks.
     *
     * @param line a line of content from the storage file
     * @return a task which is created in the task class based on the given parameters
     */
    public static TaskList parseFromFile(String line) {
        TaskList task = new TaskList();
        char taskChar = line.charAt(1);
        char taskStatus = line.charAt(4);
        String description = line.substring(7);
        if (taskChar == 'T') {
            return task.addExisting(taskChar, taskStatus, description, null);
        } else {
            int lastIndex = line.length() - 2;
            int startIndex = 0;
            StringBuilder stringBuilder = new StringBuilder();
            if (line.charAt(lastIndex + 1) == ')') {
                for (int i = lastIndex; i > 0; i--) {
                    if (line.charAt(i) == '(') {
                        startIndex = i - OFFSET; // Offset characteristics
                        break;
                    }
                    stringBuilder.append(line.charAt(i));
                }
            } else {
                for (int i = lastIndex + 1; i > 0; i--) {
                    if (line.charAt(i) == '(') {
                        startIndex = i - OFFSET;
                        break;
                    }
                    stringBuilder.append(line.charAt(i));
                }
            }
            stringBuilder.reverse();
            return task.addExisting(taskChar, taskStatus, description.substring(0, startIndex),
                    stringBuilder.toString().trim());
        }
    }
}
