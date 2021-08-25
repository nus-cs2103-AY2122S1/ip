package myjournal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Scanner;

import myjournal.exception.EmptyDescriptionException;
import myjournal.exception.InvalidTaskNumberException;
import myjournal.exception.InvalidTypeException;
import myjournal.exception.MyJournalException;
import myjournal.task.Deadline;
import myjournal.task.Event;
import myjournal.task.Task;
import myjournal.task.Todo;

/**
 * Returns an object of Parser.
 *
 * @author felissafaustine
 */
public class Parser {
    /**
     * Parses the input from the user.
     *
     * @param line The current line parsed.
     * @param tasks The list of the tasks in MyJournal.
     */
    public void parse(Scanner line, TaskList tasks, Ui ui) {
        try {
            String firstWord = line.next();
            switch (firstWord) {
                case "done":
                    ui.doneTaskPrint(Parser.parseDone(line, tasks));
                    break;
                case "delete":
                    ui.removeTaskPrint(Parser.parseDelete(line, tasks));
                    break;
                case "list":
                    Parser.parseList(tasks);
                    break;
                case "todo":
                    tasks.addTask(Parser.parseTodo(line));
                    ui.taskAddPrint(tasks);
                    break;
                case "event":
                    tasks.addTask(Parser.parseEvent(line));
                    ui.taskAddPrint(tasks);
                    break;
                case "deadline":
                    tasks.addTask(Parser.parseDeadline(line));
                    ui.taskAddPrint(tasks);
                    break;
                default:
                    throw new InvalidTypeException("OOPS!!! Please put either todo/event/deadline!");
            }
        } catch (InvalidTypeException e) {
            System.out.println(e.toString());
        } catch (InvalidTaskNumberException e) {
            System.out.println(e.toString());
        } catch (EmptyDescriptionException e) {
            System.out.println(e.toString());
        } catch (DateTimeParseException exception) {
            System.out.println(exception.toString());
        }
    }

    /**
     * Parses user's input for the command "done".
     *
     * @param line The current line that is being parsed.
     * @param tasks The list of tasks.
     * @return A task which has been marked as done.
     */
    public static Task parseDone(Scanner line, TaskList tasks) {
        if (!line.hasNextInt()) {
            throw new InvalidTaskNumberException("OOPS!!! Please specify the task "
                    + "that needs to be marked as done!");
        }
        int index = line.nextInt() - 1;
        if (index >= tasks.getSize() || index < 0 || tasks.getTask(index) == null) {
            throw new InvalidTaskNumberException("OOPS!!! Please enter a valid task number!");
        }
        tasks.getTask(index).setState(true);
        return tasks.getTask(index);
    }

    /**
     * Parses user's input for the command "list".
     *
     * @param tasks The list of tasks.
     */
    public static void parseList(TaskList tasks) {
        if (tasks.getSize() == 0) {
            System.out.println("You have no task!");
        } else {
            for (int i = 0; i < tasks.getSize(); i++) {
                System.out.println((i + 1) + "." + tasks.getTask(i));
            }
        }
    }

    /**
     * Parses user's input for the command "delete".
     *
     * @param line The user's input.
     * @param tasks The list of tasks.
     * @return The task to be deleted.
     */
    public static Task parseDelete(Scanner line, TaskList tasks) {
        if (!line.hasNextInt()) {
            throw new InvalidTaskNumberException("OOPS!!! Please specify the task "
                    + "that needs to be deleted!");
        }
        int index = line.nextInt() - 1;
        if (index >= tasks.getSize() || index < 0 || tasks.getTask(index) == null) {
            throw new InvalidTaskNumberException("OOPS!!! Please enter a valid task number!");
        }
        Task temp = tasks.getTask(index);
        tasks.deleteTask(index);
        return temp;
    }

    /**
     * Returns the time or date of the task.
     *
     * @param line The user's input.
     * @return The String representation of the time or date of the task.
     * @throws DateTimeParseException An exception thrown if the date or time is invalid.
     */
    public static String getTimeDate(Scanner line) throws DateTimeParseException {
        String parsed = "";
        while (line.hasNext()) {
            String currWord = line.next();
            if (isDate(currWord)) {
                LocalDate date = LocalDate.parse(currWord);
                String month = date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
                String dateFormatted = date.getDayOfWeek().toString() + ", " + date.getDayOfMonth() + " " + month
                        + " " + date.getYear();
                parsed = parsed + " " + dateFormatted;
            } else if (isTime(currWord)) {
                LocalTime time = LocalTime.parse(currWord);
                String beforeOrAfterNoon = time.getHour() >= 12 ? "pm" : "am";
                int hour = time.getHour() >= 12
                        ? time.getHour() - 12
                        : time.getHour();
                int min = time.getMinute();
                String timeFormatted = (String.valueOf(hour).length() == 1 ? "0" + hour : hour)
                        + ":" + (min < 10 ? "0" + min : min) + beforeOrAfterNoon;
                parsed = parsed + " " + timeFormatted;
            } else {
                parsed = parsed + " " + currWord;
            }
        }
        return parsed;
    }

    /**
     * Returns whether the string is a date.
     *
     * @param string The string that may potentially be a date.
     * @return A boolean stating whether the string is a date.
     */
    public static boolean isDate(String string) {
        String year = string.substring(0, 4);
        String month = string.substring(5, 7);
        String day = string.substring(8, 10);
        return string.length() == 10 && string.charAt(4) == '-' && string.charAt(7) == '-'
                && isInteger(year) && isInteger(month) && isInteger(day);
    }

    /**
     * Returns whether the string is a time.
     *
     * @param string The string that may potentially be a time.
     * @return A boolean stating whether the string is a time.
     */
    public static boolean isTime(String string) {
        return string.length() == 5 && isInteger(string.substring(0,2)) && isInteger(string.substring(3,5))
                && string.charAt(2) == ':';
    }

    /**
     * Returns whether the string is an integer.
     *
     * @param string The string that may potentially be an integer.
     * @return A boolean stating whether the string is an integer.
     */
    public static boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Parses user's input for the command "Todo".
     *
     * @param line The user's input.
     * @return The Todo object.
     */
    public static Todo parseTodo(Scanner line) {
        String taskName= "";
        if (!line.hasNext()) {
            throw new EmptyDescriptionException("OOPS!!! Please specify the todo!!");
        }
        while (line.hasNext()) {
            String currWord = line.next();
            taskName = taskName + currWord + " ";
        }
        return new Todo(taskName);
    }

    /**
     * Parses user's input for the command "Event".
     *
     * @param line The user's input.
     * @return The Event object.
     */
    public static Event parseEvent(Scanner line) {
        String taskName= "";
        if (!line.hasNext()) {
            throw new EmptyDescriptionException("OOPS!!! Please specify the event!!");
        }
        while (line.hasNext()) {
            String currWord = line.next();
            if (currWord.charAt(0) == '/') {
                break;
            }
            taskName = taskName + currWord + " ";
        }
        return new Event(taskName, getTimeDate(line));
    }

    /**
     * Parses user's input for the command "Deadline".
     *
     * @param line The user's input.
     * @return The Deadline object.
     */
    public static Deadline parseDeadline(Scanner line) {
        String taskName = "";
        if (!line.hasNext()) {
            throw new EmptyDescriptionException("OOPS!!! Please specify the deadline!!");
        }
        while (line.hasNext()) {
            String currWord = line.next();
            if (currWord.charAt(0) == '/') {
                break;
            }
            taskName = taskName + currWord + " ";
        }
        return new Deadline(taskName, getTimeDate(line));
    }
}
