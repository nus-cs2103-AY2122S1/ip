package duke;

import static java.lang.Integer.parseInt;

import java.time.format.DateTimeParseException;

/**
 * Represents the main logic of the code when the user enters an input.
 */
public class Parser {
    private TaskList taskList;
    private Ui ui;
    private String[] arr;

    /**
     * A constructor to create a parser object.
     *
     * @param taskList A list that keeps track of all tasks.
     * @param ui An object that perform tasks related to interactions with user.
     */
    Parser(TaskList taskList, Ui ui) {
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
     * Converts the user input.
     * @return A response given an input from the user.
     */
    public String parse(String input) {
        do {
            try {
                arr = input.split(" ");
                switch (arr[0]) {
                case "bye":
                    return ui.printBye();
                case "done":
                    return parseDone(arr);
                case "delete":
                    return parseDelete(arr);
                case "todo":
                    return parseTodo(arr);
                case "deadline":
                    return parseDeadline(arr);
                case "event":
                    return parseEvent(arr);
                case "list":
                    return parseList();
                case "find":
                    return parseFind(arr);
                case "search":
                    return parseSearch(arr);
                default:
                    throw new InvalidCommandException("Command Not Found");
                }
            } catch (DukeException | DateTimeParseException e) {
                return e.toString();
            }
        } while (!input.equals("bye"));
    }

    /**
     * Deals with the user input when the user types "done".
     *
     * @param arr Array of strings from the user input.
     * @return A response when the user types done.
     * @throws InvalidCommandException If the following input is not valid.
     */
    public String parseDone(String[] arr) throws InvalidCommandException,
            InvalidValueException {
        assert arr.length == 2 : "Please enter [done] [number]";
        return check(arr);
    }

    /**
     * Deals with the user input when the user types "delete".
     *
     * @param arr Array of strings from the user input.
     * @return A response when the user types delete.
     * @throws InvalidCommandException If the following input is not valid.
     * @throws InvalidValueException If the following input is not a valid number.
     */
    public String parseDelete(String[] arr) throws InvalidCommandException,
            InvalidValueException {
        assert arr.length == 2 : "Please enter [delete] [number]";
        return check(arr);
    }

    /**
     * Deals with the user input when the user types "todo".
     *
     * @param arr Array of strings from the user input.
     * @return A response when the user types todo.
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
     * @return A response when the user types deadline.
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
     * @return A response when the user types event.
     * @throws EmptyDescriptionException If the next input is missing.
     */
    public String parseEvent(String[] arr) throws EmptyDescriptionException {
        if (arr.length < 2) {
            throw new EmptyDescriptionException("Missing description / date");
        }
        taskList.addTask(new Event(TaskList.getDescription(arr), TaskList.getDeadline(arr)));
        return ui.printAddTask(taskList);
    }

    /**
     * Deals with the user input when the user types "list".
     * @return A response when the user types list.
     */
    public String parseList() {
        return ui.displayList(taskList);
    }

    /**
     * Deals with the user input when the user types "find".
     *
     * @param arr Array of strings from the user input.
     * @return A response when the user types find.
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

    /**
     * Deals with the user input when the user types "find".
     *
     * @param arr Array of strings from the user input.
     * @return A string containing a list of tasks that matches the keyword.
     * @throws InvalidCommandException If the following input is not valid.
     */
    public String parseSearch(String[] arr) throws InvalidCommandException {
        if (arr.length == 1) {
            throw new InvalidCommandException("Please specify a keyword you want to search");
        } else if (taskList.getSize() == 0) {
            throw new InvalidCommandException("You have not added any task!");
        } else if (arr.length > 2) {
            throw new InvalidCommandException("Please enter a single keyword!");
        } else {
            return taskList.searchTask(arr[1]);
        }
    }

    /**
     * Checks for invalid inputs for parseDone and parseDelete.
     * @param arr An input from the user.
     * @return An appropriate response for done or delete.
     */
    public String check(String[] arr) {
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
            if (arr[0].equals("done")) {
                taskList.markAsDone(parseInt(arr[1]) - 1);
                String str = ui.printDone() + "\n";
                str += ui.printCurrentTask(taskList, parseInt(arr[1]) - 1) + "\n";
                return str;
            } else {
                String str = ui.printRemove() + "\n";
                str += ui.printCurrentTask(taskList, parseInt(arr[1]) - 1) + "\n";
                taskList.removeTask(parseInt(arr[1]) - 1);
                str += "Now you have " + taskList.getSize() + " tasks in the list. \n";
                return str;
            }
        }
    }
}
