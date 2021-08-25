package myjournal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Scanner;
import myjournal.exception.*;
import myjournal.task.*;

public class Parser {
    private Ui ui;

    public void parse(Scanner line, TaskList tasks) {
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

    public static void parseList(TaskList tasks) {
        if (tasks.getSize() == 0) {
            System.out.println("You have no task!");
        } else {
            for (int i = 0; i < tasks.getSize(); i++) {
                System.out.println((i + 1) + "." + tasks.getTask(i));
            }
        }
    }

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
                int hour = time.getHour() >= 12 ? time.getHour() - 12 : time.getHour();
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

    public static boolean isDate(String string) {
        String year = string.substring(0, 4);
        String month = string.substring(5, 7);
        String day = string.substring(8, 10);
        return string.length() == 10 && string.charAt(4) == '-' && string.charAt(7) == '-'
                && isInteger(year) && isInteger(month) && isInteger(day);
    }

    public static boolean isTime(String string) {
        return string.length() == 5 && isInteger(string.substring(0,2))
                && isInteger(string.substring(3,5)) && string.charAt(2) == ':';
    }

    public static boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

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
