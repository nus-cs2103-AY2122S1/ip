package duke.parser;

import duke.exception.InvalidDescriptionException;
import duke.exception.InvalidInputException;
import duke.exception.InvalidTimeException;
import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Represents a Parser object that deals with making sense of the user commands.
 *
 * @author: James Kua
 * @version: Duke-Level-8
 */
public class Parser {

    /**
     * Accepts only the specified following specific commands.
     */
    public enum Keyword {
        TODO, EVENT, DEADLINE, LIST, DONE, DELETE, BYE, FIND, HELP
    }

    /** TaskList object that stores tasks */
    private static TaskList tasks;
    /** Storage object that saves the TaskList. */
    private Storage storage;
    /** Ui object that deals with user commands. */
    private Ui ui;

    /**
     * Constructor for a Parser object.
     *
     * @param tasks TaskList containing the list of tasks.
     * @param storage Storage that stores the data.
     */
    public Parser(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = new Ui();
    }

    private String getFirstWord(String input) {
        String[] arr = input.split(" ", 2);
        String firstWord = arr[0];
        return firstWord;
    }

    private String getSecondWord(String input) {
        String[] arr = input.split(" ", 2);
        String secondWord = arr[1].strip();
        return secondWord;
    }

    private Keyword getKeyword(String input) {
        Keyword keyword = Keyword.valueOf(getFirstWord(input).toUpperCase());
        return keyword;
    }

    private String addComplete(Task t) {
        tasks.add(t);
        storage.saveData(tasks);
        return "Got it. I've added this task: " + t.toString() + '\n' + tasks.printTaskNumber(tasks);
    }

    private String addToDo(String description) {
        return addComplete(new Todo(description));
    }

    private String addDeadline(String description, String time) throws InvalidInputException {
        String[] split = time.split(" ", 2);
        LocalDate date;
        LocalTime timing;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
            date = LocalDate.parse(split[0], formatter);
        } catch (DateTimeParseException e) {
            return "Format should be in yyyy-mm-dd!";
        }
        if (split.length == 1) {
            return addComplete(new Deadline(description, date));
        } else {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                timing = LocalTime.parse(split[1], formatter);
            } catch (DateTimeParseException e) {
                return "Format should be in HH:mm";
            }
            return addComplete(new Deadline(description, date, timing));
        }
    }

    private String addEvent(String description, String time) throws InvalidInputException {
        String[] split = time.split(" ", 2);
        LocalDate date;
        LocalTime timing;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
            date = LocalDate.parse(split[0], formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Format should be in yyyy-mm-dd!");
        }
        if (split.length == 1) {
            return addComplete(new Event(description, date));
        } else {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                timing = LocalTime.parse(split[1], formatter);
            } catch (DateTimeParseException e) {
                return "Format should be in HH:mm";
            }
            return addComplete(new Event(description, date, timing));
        }
    }

    private String printTasksWithKeyword(String keyword) {
        TaskList listWithKeyword = new TaskList();
        for (Task task : tasks.getTaskList()) {
            if (task.toString().contains(keyword)) {
                listWithKeyword.add(task);
            }
        }
        if (listWithKeyword.size() < 1) {
            return "There are no tasks that matches this keyword!";
        } else {
            return TaskList.printItemList(listWithKeyword);
        }
    }

    /**
     * Parses the command based on the user's input to execute the corresponding action.
     * Returns false when the user inputs 'bye', proceeds to break out of the program.
     *
     * @return a boolean on whether to exit the program.
     */
    public String parseCommand(String input) {

        try {
            Keyword keyword = getKeyword(input);

            switch (keyword) {
            case HELP:
                return ui.showSupportedCommands();
            case BYE:
                return "Bye!";
            case LIST:
                return tasks.printItemList(tasks);
            case DONE:
                return tasks.markTaskDone(input);
            case DELETE:
                return tasks.deleteTask(input);
            case TODO:
                if (input.substring(5).length() < 1) {
                    throw new InvalidDescriptionException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                return addToDo(input.substring(5));
            case DEADLINE:
                String[] arr = input.split("\\(");
                if (getSecondWord(arr[1]).length() < 1 || !getFirstWord(arr[1]).equals("by:")) {
                    throw new InvalidTimeException("☹ OOPS!!! Please enter a suitable deadline!");
                }
                String description = getSecondWord(arr[0]);
                String timing = getSecondWord(arr[1]).substring(0, getSecondWord(arr[1]).length() - 1);
                return addDeadline(description, timing);
            case EVENT:
                String[] arr_event = input.split("\\(");
                if (getSecondWord(arr_event[1]).length() < 1 || !getFirstWord(arr_event[1]).equals("at:")) {
                    throw new InvalidTimeException("☹ OOPS!!! Please enter a suitable event timing!");
                }
                String eventDescription = getSecondWord(arr_event[0]);
                String eventTiming = getSecondWord(arr_event[1]).substring(0, getSecondWord(
                        arr_event[1]).length() - 1);
                return addEvent(eventDescription, eventTiming);
            case FIND:
                if (getSecondWord(input).length() < 1) {
                    throw new InvalidDescriptionException("☹ OOPS!!! Please enter a suitable keyword to find!");
                }
                String findKeyword = getSecondWord(input);
                return printTasksWithKeyword(findKeyword);
            default:
                return null;
            }
        } catch (InvalidTimeException | InvalidDescriptionException | InvalidInputException e) {
            return e.getMessage();
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            return "☹ OOPS!!! Please include an appropriate description/time!";
        } catch (IllegalArgumentException e) {
            return "☹ OOPS!!! Unable to recognise command, please try again!";
        }
    }
}
