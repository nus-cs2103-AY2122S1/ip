package myjournal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import myjournal.exception.EmptyDescriptionException;
import myjournal.exception.InvalidCommandException;
import myjournal.exception.InvalidTaskNumberException;
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
    public String parse(Scanner line, TaskList tasks, Ui ui) {
        try {
            String firstWord = line.next();
            switch (firstWord) {
            case "done":
                return ui.doneTaskPrint(Parser.parseDone(line, tasks));
            case "delete":
                return ui.removeTaskPrint(Parser.parseDelete(line, tasks));
            case "find":
                return ui.findTaskPrint(Parser.parseFind(line, tasks));
            case "edit":
                return ui.editTaskPrint(Parser.parseEdit(line, tasks));
            case "list":
                return Parser.parseList(tasks);
            case "todo":
                tasks.addTask(Parser.parseTodo(line));
                return ui.taskAddPrint(tasks);
            case "event":
                tasks.addTask(Parser.parseEvent(line));
                return ui.taskAddPrint(tasks);
            case "deadline":
                tasks.addTask(Parser.parseDeadline(line));
                return ui.taskAddPrint(tasks);
            default:
                throw new InvalidCommandException("OOPS!!! Please enter a valid command!");
            }
        } catch (MyJournalException | DateTimeParseException e) {
            return e.toString();
        }
    }

    /**
     * Parses user's input for the command "find".
     *
     * @param line The current line that is being parsed.
     * @param tasks The list of tasks.
     * @return A task which has been marked as done.
     */
    public static TaskList parseFind(Scanner line, TaskList tasks) {
        assert tasks != null : "TaskList should not be null";
        if (!line.hasNext()) {
            throw new EmptyDescriptionException("OOPS!!! Please tell me which word you want to find!!");
        }
        String find = line.next();
        if (line.hasNext()) {
            throw new InvalidCommandException("Please enter only one word!!");
        }
        TaskList newList = new TaskList();
        for (int i = 0; i < tasks.getSize(); i++) {
            Task currTask = tasks.getTask(i);
            Scanner taskName = new Scanner(currTask.getTaskName());
            while (taskName.hasNext()) {
                if (taskName.next().equals(find)) {
                    newList.addTask(currTask);
                    break;
                }
            }
        }
        return newList;
    }

    /**
     * Parses user's input for the command "done".
     *
     * @param line The current line that is being parsed.
     * @param tasks The list of tasks.
     * @return A task which has been marked as done.
     */
    public static Task parseDone(Scanner line, TaskList tasks) {
        assert tasks != null : "TaskList should not be null";
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
     * Parses user's input for the command "edit".
     *
     * @param line The current line that is being parsed.
     * @param tasks The list of tasks.
     * @return A task which has been edited.
     */
    public static Task parseEdit(Scanner line, TaskList tasks) {
        assert tasks != null : "TaskList should not be null";
        if (!line.hasNext()) {
            throw new InvalidTaskNumberException("OOPS!!! Please specify the task "
                    + "that needs to be edited!");
        }
        String taskToBeEdited = line.nextLine();
        String[] arrEdit = taskToBeEdited.split(" ");
        if (!isInteger(arrEdit[1])) {
            throw new InvalidTaskNumberException("OOPS!!! Please specify the task number of the task "
                    + "that needs to be edited!");
        }
        int index = Integer.parseInt(arrEdit[1]) - 1;
        if (index >= tasks.getSize() || index < 0 || tasks.getTask(index) == null) {
            throw new InvalidTaskNumberException("OOPS!!! Please enter a valid task number!");
        }
        if (arrEdit.length < 4) {
            throw new InvalidCommandException("Please specify what you want to edit.");
        }
        String infoToBeEdited = arrEdit[2];
        if (!(infoToBeEdited.equals("time") || infoToBeEdited.equals("description"))) {
            throw new InvalidCommandException("Please specify whether you want to edit time or description.");
        }
        return edit(infoToBeEdited, index, arrEdit, tasks);
    }

    /**
     * Edits the time or description of a task.
     *
     * @param infoToBeEdited The new information.
     * @param index The index of the task that needs to be edited.
     * @param arrEdit The user input.
     * @param tasks The list of tasks.
     * @return The edited task.
     */
    public static Task edit(String infoToBeEdited, int index, String[] arrEdit, TaskList tasks) {
        String newInfo = "";
        for (int i = 0; i < arrEdit.length - 3; i++) {
            newInfo = newInfo + arrEdit[i + 3] + " ";
        }
        if (infoToBeEdited.equals("time")) {
            if (tasks.getTask(index) instanceof Todo) {
                throw new InvalidCommandException("Todo does not have a time description! "
                        + "Try editing the time of an Event or Deadline instead:)");
            }
            Scanner time = new Scanner(newInfo);
            tasks.getTask(index).setTime(parseTimeDate(time));
        } else {
            tasks.getTask(index).setTaskName(newInfo);
        }
        return tasks.getTask(index);
    }

    /**
     * Parses user's input for the command "list".
     *
     * @param tasks The list of tasks.
     */
    public static String parseList(TaskList tasks) {
        assert tasks != null : "TaskList should not be null";
        if (tasks.getSize() == 0) {
            return "You have no task!";
        } else {
            String s = "Here are your tasks:\n";
            for (int i = 0; i < tasks.getSize(); i++) {
                s = s + (i + 1) + "." + tasks.getTask(i) + "\n";
            }
            return s;
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
        assert tasks != null : "TaskList should not be null";
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
    public static String parseTimeDate(Scanner line) throws DateTimeParseException {
        String parsed = "";
        while (line.hasNext()) {
            String currWord = line.next();
            if (isDate(currWord)) {
                LocalDate date = LocalDate.parse(currWord);
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
                String dateFormatted = dateFormatter.format(date);
                parsed = parsed + " " + dateFormatted;
            } else if (isTime(currWord)) {
                LocalTime time = LocalTime.parse(currWord);
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
                String timeFormatted = timeFormatter.format(time);
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
        if (string.length() == 10) {
            String year = string.substring(0, 4);
            String month = string.substring(5, 7);
            String day = string.substring(8, 10);
            return string.length() == 10 && string.charAt(4) == '-' && string.charAt(7) == '-'
                    && isInteger(year) && isInteger(month) && isInteger(day);
        }
        return false;
    }

    /**
     * Returns whether the string is a time.
     *
     * @param string The string that may potentially be a time.
     * @return A boolean stating whether the string is a time.
     */
    public static boolean isTime(String string) {
        return string.length() == 5 && isInteger(string.substring(0, 2)) && isInteger(string.substring(3, 5))
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
        String taskName = "";
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
        String taskName = "";
        if (!line.hasNext()) {
            throw new EmptyDescriptionException("OOPS!!! Please specify the event!!");
        }
        while (line.hasNext()) {
            String currWord = line.next();
            if (currWord.equals("/at")) {
                break;
            }
            taskName = taskName + currWord + " ";
        }
        return new Event(taskName, parseTimeDate(line));
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
            if (currWord.equals("/by")) {
                break;
            }
            taskName = taskName + currWord + " ";
        }
        return new Deadline(taskName, parseTimeDate(line));
    }
}
