package com.duke.parser;

import com.duke.ui.UserInterface;
import com.duke.task.TaskList;
import com.duke.exception.DukeException;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static final String[] months = new String[]{"JANUARY", "FEBRUARY", "MARCH", "APRIL",
            "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER",
            "OCTOBER", "NOVEMBER", "DECEMBER"};
    private static final int OFFSET = 7;

    public Parser() {}

    private final static UserInterface userInterface = new UserInterface();

    public boolean firstCommandParser(String command, Scanner scanner) {
        TaskList task = new TaskList();

        switch (command) {
            case "bye":
                userInterface.exit();
                return true;
            case "done":
                String stringIndex = scanner.next();
                try {
                    int index = Integer.parseInt(stringIndex) - 1;
                    if (task.markDone(index)) {
                        userInterface.taskComplete(TaskList.getTask(index));
                    }
                    break;
                } catch (NumberFormatException e) {
                    userInterface.integerInputWarning();
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
                    if (deletedTask == null) break;
                    task.delete(index2);
                    userInterface.taskDeleted(deletedTask);
                    break;
                } catch (NumberFormatException e) {
                    userInterface.integerInputWarning();
                    break;
                }
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

    public static LocalDate findDate(String input) {
        String regex = "(\\d{4}-\\d{2}-\\d{2})"; // Regex to find date of the form yyyy-mm-dd
        Matcher m = Pattern.compile(regex).matcher(input);
        if (m.find()) {
            return LocalDate.parse(m.group(1));
        }
        return null;
    }

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
            String[] timeRange = sb.toString().split("-");
            return timeRange;
        } else {
            return null;
        }
    }

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
            userInterface.nullFunction();
        }
    }

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
            userInterface.nullFunction();
        }
    }

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
            return task.addExisting(taskChar, taskStatus, description.substring(0, startIndex), stringBuilder.toString().trim());
        }
    }
}
