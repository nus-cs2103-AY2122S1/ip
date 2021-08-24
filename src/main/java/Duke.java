import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

import java.time.LocalDate;


/**
 * Project Duke
 *
 * @author Willy Angga Prawira
 */

public class Duke {

    private TaskList taskList;
    private Storage storage;

    Duke(String filepath) {
        taskList = new TaskList(new ArrayList<Task>());
        storage = new Storage(filepath);
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

    public void run() {
        storage.readFile(taskList);
        String input = "";
        String[] arr;
        Scanner scan = new Scanner(System.in);
        System.out.println(
                "Hello! I'm Amped :) \n"
                        + "Type: \n"
                        + "1. A task (todo/deadline/event) followed by description to add tasks \n"
                        + "   e.g \"deadline submit homework /by Sunday 12 pm\" \n"
                        + "2. \"list\" to see the list of tasks \n"
                        + "3. \"done [number]\" to mark a particular task as done \n"
                        + "4. \"delete [number]\" to delete a particular task \n"
                        + "5. \"bye\" to exit"
        );
        do {
            try {
                input = scan.nextLine();
                arr = input.split(" ");
                if (input.equals("bye")) {
                    System.out.println("Good Bye. Have a nice day!");
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
                        System.out.println("Nice! I've marked this task as done: ");
                        taskList.markAsDone(parseInt(arr[1]) - 1);
                        System.out.println(taskList.getTask(parseInt(arr[1]) - 1).toString());
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
                        System.out.println("Noted. I've removed this task: ");
                        System.out.println(taskList.getTask(parseInt(arr[1]) - 1).toString());
                        taskList.removeTask(parseInt(arr[1]) - 1);
                    }
                } else if (arr[0].equals("todo")) {
                    if (arr.length < 2) {
                        throw new EmptyDescriptionException("Missing description / date");
                    }
                    taskList.addTask(new Todo(TaskList.getDescription(arr)));
                    taskList.printAddTask();
                } else if (arr[0].equals("deadline")) {
                    if (arr.length < 2) {
                        throw new EmptyDescriptionException("Missing description / date");
                    }
                    taskList.addTask(new Deadline(TaskList.getDescription(arr), TaskList.getDeadline(arr)));
                    taskList.printAddTask();
                } else if (arr[0].equals("event")) {
                    if (arr.length < 2) {
                        throw new EmptyDescriptionException("Missing description / date");
                    }
                    taskList.addTask(new Event(TaskList.getDescription(arr), TaskList.getDeadline(arr)));
                    taskList.printAddTask();
                } else if (input.equals("list")) {
                    taskList.displayList();
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

    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }
}
