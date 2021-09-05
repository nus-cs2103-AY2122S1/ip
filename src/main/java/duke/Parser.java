package duke;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * Represents the main logic of the code when the user enters an input.
 */
public class Parser {
    private Scanner scan;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private String arr[];
    private String input = "";

    /**
     * A constructor to create a parser object.
     *
     * @param scan A scanner that takes in the input from the user.
     * @param storage A storage that deals with loading and saving files.
     * @param taskList A list that keeps track of all tasks.
     * @param ui An object that perform tasks related to interactions with user.
     */
    Parser(Scanner scan, Storage storage, TaskList taskList, Ui ui) {
        this.scan = scan;
        this.storage = storage;
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Checks if a string is an integer or not.
     *
     * @param s A string from the user input.
     * @return True if it is an integer, false otherwise.
     */
    public static boolean isNumeric(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Makes sense of the user input.
     */
    public String parse(String input) {
        do {
            try {
                arr = input.split(" ");
                if (input.equals("bye")) {
                    return ui.printBye();
                } else if (arr[0].equals("done")) {
                    return parseDone(arr);
                } else if (arr[0].equals("delete")) {
                    return parseDelete(arr);
                } else if (arr[0].equals("todo")) {
                    return parseTodo(arr);
                } else if (arr[0].equals("deadline")) {
                    return parseDeadline(arr);
                } else if (arr[0].equals("event")) {
                    return parseEvent(arr);
                } else if (input.equals("list")) {
                    return parseList();
                } else if (arr[0].equals("find")) {
                    return parseFind(arr);
                } else {
                    return "Command not Found";
                }
            } catch (DukeException e) {
                return e.toString();
            } catch (DateTimeParseException e) {
                return e.getMessage();
            }
        } while (!input.equals("bye"));
    }

    /**
     * Deals with the user input when the user types "done".
     *
     * @param arr Array of strings from the user input.
     * @throws InvalidCommandException If the following input is not valid.
     */
    public String parseDone(String[] arr) throws InvalidCommandException {
        if (arr.length == 1) {
            throw new InvalidCommandException("Please specify a number");
        } else if (!isNumeric(arr[1])) {
            throw new InvalidCommandException("Please enter a number");
        } else if (taskList.getSize() == 0) {
            throw new InvalidCommandException("You have not added any task!");
        } else if ((Integer.parseInt(arr[1]) > taskList.getSize()
                || Integer.parseInt(arr[1]) <= 0)) {
            throw new InvalidValueException("Enter a valid number!");
        } else {
            taskList.markAsDone(parseInt(arr[1]) - 1);
            String str = ui.printDone() + "\n";
            str += ui.printCurrentTask(taskList, parseInt(arr[1]) - 1) + "\n";
            return str;
        }
    }

    /**
     * Deals with the user input when the user types "delete".
     *
     * @param arr Array of strings from the user input.
     * @throws InvalidCommandException If the following input is not valid.
     * @throws InvalidValueException If the following input is not a valid number.
     */
    public String parseDelete(String[] arr) throws InvalidCommandException,
            InvalidValueException {
        if (arr.length == 1) {
            throw new InvalidCommandException("Please specify a number");
        } else if (!isNumeric(arr[1])) {
            throw new InvalidCommandException("Please enter a number");
        } else if (taskList.getSize() == 0) {
            throw new InvalidCommandException("You have not added any task!");
        } else if ((Integer.parseInt(arr[1]) > taskList.getSize()
                || Integer.parseInt(arr[1]) <= 0)) {
            throw new InvalidValueException("Enter a valid number!");
        } else {
            String str = ui.printRemove() + "\n";
            str += ui.printCurrentTask(taskList, parseInt(arr[1]) - 1) + "\n";
            taskList.removeTask(parseInt(arr[1]) - 1);
            return str;
        }
    }

    /**
     * Deals with the user input when the user types "todo".
     *
     * @param arr Array of strings from the user input.
     * @throws EmptyDescriptionException If the next input is missing.
     */
    public String parseTodo(String[] arr) throws EmptyDescriptionException {
        if (arr.length < 2) {
            throw new EmptyDescriptionException("Missing description / date");
        }
        taskList.addTask(new Todo(TaskList.getDescription(arr)));
        return ui.printAddTask(taskList);
    }

    /**
     * Deals with the user input when the user types "deadline".
     *
     * @param arr Array of strings from the user input.
     * @throws EmptyDescriptionException If the next input is missing.
     */
    public String parseDeadline(String[] arr) throws EmptyDescriptionException {
        if (arr.length < 2) {
            throw new EmptyDescriptionException("Missing description / date");
        }
        taskList.addTask(new Deadline(TaskList.getDescription(arr), TaskList.getDeadline(arr)));
        return ui.printAddTask(taskList);
    }

    /**
     * Deals with the user input when the user types "event".
     *
     * @param arr Array of strings from the user input.
     * @throws EmptyDescriptionException If the next input is missing.
     */
    public String parseEvent(String[] arr) throws EmptyDescriptionException {
        String str = "";
        if (arr.length < 2) {
            throw new EmptyDescriptionException("Missing description / date");
        }
        taskList.addTask(new Event(TaskList.getDescription(arr), TaskList.getDeadline(arr)));
        return ui.printAddTask(taskList);
    }

    /**
     * Deals with the user input when the user types "list".
     */
    public String parseList() {
        return ui.displayList(taskList);
    }

    /**
     * Deals with the user input when the user types "find".
     *
     * @param arr Array of strings from the user input.
     * @throws InvalidCommandException If the following input is not valid.
     */
    public String parseFind(String[] arr) throws InvalidCommandException {
        if (arr.length == 1) {
            throw new InvalidCommandException("Please specify a task you want to search");
        } else if (taskList.getSize() == 0) {
            throw new InvalidCommandException("You have not added any task!");
        } else if (arr.length > 2) {
            throw new InvalidCommandException("Please enter a single keyword!");
        } else {
            return taskList.findTask(arr[1]);
        }
    }
}
