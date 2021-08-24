package duke;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Parser {
    private Scanner scan;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private String arr[];
    private String input = "";

    Parser(Scanner scan, Storage storage, TaskList taskList, Ui ui) {
        this.scan = scan;
        this.storage = storage;
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * A method to check if a string is an integer or not
     *
     * @param s A string from the user input
     * @return True if it is an integer, false otherwise
     */
    public static boolean isNumeric(String s) {
        try {
            int d = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public void parse() {
        storage.readFile(taskList);
        do {
            try {
                input = scan.nextLine();
                arr = input.split(" ");
                if (input.equals("bye")) {
                    ui.printBye();
                } else if (arr[0].equals("done")) {
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
                } else if (arr[0].equals("delete")) {
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
                } else if (arr[0].equals("todo")) {
                    if (arr.length < 2) {
                        throw new EmptyDescriptionException("Missing description / date");
                    }
                    taskList.addTask(new Todo(TaskList.getDescription(arr)));
                    ui.printAddTask(taskList);
                } else if (arr[0].equals("deadline")) {
                    if (arr.length < 2) {
                        throw new EmptyDescriptionException("Missing description / date");
                    }
                    taskList.addTask(new Deadline(TaskList.getDescription(arr), TaskList.getDeadline(arr)));
                    ui.printAddTask(taskList);
                } else if (arr[0].equals("event")) {
                    if (arr.length < 2) {
                        throw new EmptyDescriptionException("Missing description / date");
                    }
                    taskList.addTask(new Event(TaskList.getDescription(arr), TaskList.getDeadline(arr)));
                    ui.printAddTask(taskList);
                } else if (input.equals("list")) {
                    ui.displayList(taskList);
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
}
