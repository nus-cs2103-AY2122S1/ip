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
    public void parse() {
        do {
            try {
                input = scan.nextLine();
                arr = input.split(" ");
                if (input.equals("bye")) {
                    break;
                } else if (arr[0].equals("done")) {
                    parseDone(arr);
                } else if (arr[0].equals("delete")) {
                    parseDelete(arr);
                } else if (arr[0].equals("todo")) {
                    parseTodo(arr);
                } else if (arr[0].equals("deadline")) {
                    parseDeadline(arr);
                } else if (arr[0].equals("event")) {
                    parseEvent(arr);
                } else if (input.equals("list")) {
                    parseList();
                } else if (arr[0].equals("find")) {
                    parseFind(arr);
                } else {
                    throw new InvalidCommandException("Command not Found");
                }
                storage.saveFile(taskList);
            } catch (InvalidCommandException e) {
                System.out.println(e.toString());
            } catch (EmptyDescriptionException e) {
                System.out.println(e.toString());
            } catch (InvalidValueException e) {
                System.out.println(e.toString());
            } catch (DateTimeParseException e) {
                System.out.println(e.getMessage());
            }
        } while (!input.equals("bye"));
    }

    /**
     * Deals with the user input when the user types "done".
     *
     * @param arr Array of strings from the user input.
     * @throws InvalidCommandException If the following input is not valid.
     */
    public void parseDone(String[] arr) throws InvalidCommandException {
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
            ui.printDone();
            taskList.markAsDone(parseInt(arr[1]) - 1);
            ui.printCurrentTask(taskList, parseInt(arr[1]) - 1);
        }
    }

    /**
     * Deals with the user input when the user types "delete".
     *
     * @param arr Array of strings from the user input.
     * @throws InvalidCommandException If the following input is not valid.
     * @throws InvalidValueException If the following input is not a valid number.
     */
    public void parseDelete(String[] arr) throws InvalidCommandException,
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
            ui.printRemove();
            ui.printCurrentTask(taskList, parseInt(arr[1]) - 1);
            taskList.removeTask(parseInt(arr[1]) - 1);
        }
    }

    /**
     * Deals with the user input when the user types "todo".
     *
     * @param arr Array of strings from the user input.
     * @throws EmptyDescriptionException If the next input is missing.
     */
    public void parseTodo(String[] arr) throws EmptyDescriptionException {
        if (arr.length < 2) {
            throw new EmptyDescriptionException("Missing description / date");
        }
        taskList.addTask(new Todo(TaskList.getDescription(arr)));
        ui.printAddTask(taskList);
    }

    /**
     * Deals with the user input when the user types "deadline".
     *
     * @param arr Array of strings from the user input.
     * @throws EmptyDescriptionException If the next input is missing.
     */
    public void parseDeadline(String[] arr) throws EmptyDescriptionException {
        if (arr.length < 2) {
            throw new EmptyDescriptionException("Missing description / date");
        }
        taskList.addTask(new Deadline(TaskList.getDescription(arr), TaskList.getDeadline(arr)));
        ui.printAddTask(taskList);
    }

    /**
     * Deals with the user input when the user types "event".
     *
     * @param arr Array of strings from the user input.
     * @throws EmptyDescriptionException If the next input is missing.
     */
    public void parseEvent(String[] arr) throws EmptyDescriptionException {
        if (arr.length < 2) {
            throw new EmptyDescriptionException("Missing description / date");
        }
        taskList.addTask(new Event(TaskList.getDescription(arr), TaskList.getDeadline(arr)));
        ui.printAddTask(taskList);
    }

    /**
     * Deals with the user input when the user types "list".
     */
    public void parseList() {
        ui.displayList(taskList);
    }

    /**
     * Deals with the user input when the user types "find".
     *
     * @param arr Array of strings from the user input.
     * @throws InvalidCommandException If the following input is not valid.
     */
    public void parseFind(String[] arr) throws InvalidCommandException {
        if (arr.length == 1) {
            throw new InvalidCommandException("Please specify a task you want to search");
        } else if (taskList.getSize() == 0) {
            throw new InvalidCommandException("You have not added any task!");
        } else {
            taskList.findTask(arr[1]);
        }
    }
}
