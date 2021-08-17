import java.util.Scanner;
import java.util.ArrayList;
import java.lang.String;
/**
 * The Duke class encapsulates the Duke project's chat-bot for CS2103T individual project 1.
 *
 * @author Chesterwongz
 */
public class Duke {
    private static final ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Prints the Duke logo.
     */
    private static void logo(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|";
        echo("Hello from\n" + logo);
    }

    /**
     * Prints our greeting.
     */
    private static void greet() {
        String greeting = "Hello! I'm Duke\n"
                + "What can I do for you?";
        echo(greeting);
    }

    /**
     * Prints our goodbye.
     */
    private static void bye() {
        String bye = "Bye! Hope to see you again soon!";
        echo(bye);
    }

    /**
     * Adds a task to the taskList.
     *
     * @param task
     */
    private static void addToList(Task task) {
        taskList.add(task);
        echo("Got it. I've added this task:\n  "
                + task + "\nNow you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Adds a To-do task to the taskList.
     *
     * @param input The String array containing the to-do description at index 1.
     * @throws DukeException
     */
    private static void addToDo(String[] input) throws DukeException {
        if (input.length > 1) {
            addToList(new ToDo(input[1]));
        } else {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty ☹");
        }
    }

    /**
     * Adds a deadline task to the taskList.
     *
     * @param input The String array containing the deadline description at index 1.
     * @throws DukeException
     */
    private static void addDeadline(String[] input) throws DukeException {
        if (input.length > 1) {
            String[] deadline = input[1].split("/", 2);
            if (deadline.length > 1 && deadline[1].length() > 3) {
                addToList(new Deadline(deadline[0], deadline[1].substring(3)));
            } else {
                throw new DukeException("OOPS!!! Please add the deadline with the right format ☹");
            }
        } else {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty ☹");
        }
    }

    /**
     * Adds a event task to the taskList.
     *
     * @param input The String array containing the event description at index 1.
     * @throws DukeException
     */
    private static void addEvent(String[] input) throws DukeException {
        if (input.length > 1) {
            String[] event = input[1].split("/", 2);
            if (event.length > 1 && event[1].length() > 3) {
                addToList(new Event(event[0], event[1].substring(3)));
            } else {
                throw new DukeException("OOPS!!! Please add the event with the right format ☹");
            }
        } else {
            throw new DukeException("OOPS!!! The description of a event cannot be empty ☹");
        }
    }

    /**
     * Marks task as done in taskList.
     *
     * @param input The String array with the index of the task to be done in input[1], if it is there.
     * @throws DukeException
     */
    private static void doTask(String[] input) throws DukeException {
        if (input.length < 2) {
            throw new DukeException("OOPS!!! Please input the task number to be marked as done ☹");
        }
        try {
            int taskID = Integer.parseInt(input[1]) - 1;
            if (0 <= taskID && taskID < taskList.size()) {
                taskList.get(taskID).markAsDone();
                echo("Nice! I've marked this task as done:\n  "
                        + taskList.get(taskID).toString());
            } else {
                throw new DukeException("OOPS!!! Please enter a valid task number ☹");

            }
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please enter a valid task number ☹");
        }
    }

    /**
     * Removes a task from the task list.
     *
     * @param input The String array with the index of the task to be removed in input[1], if it is there.
     * @throws DukeException
     */
    private static void deleteTask(String[] input) throws DukeException {
        if (input.length < 2) {
            throw new DukeException("OOPS!!! Please input the task number to be removed ☹");
        }
        try {
            int index = Integer.parseInt(input[1]) - 1;
            if (0 <= index && index < taskList.size()) {
                echo("Got it. I've removed this task:\n  "
                        + taskList.remove(index) + "\nNow you have " + taskList.size() + " tasks in the list.");
            } else {
                throw new DukeException("OOPS!!! Please enter a valid task number ☹");
            }
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please enter a valid task number ☹");
        }
    }

    /**
     * Frames the message with underscore lines and prints it.
     *
     * @param msg The String we want to frame.
     * @return The framed String.
     */
    private static void echo(String msg) {
        String line = "__________________________________________________________";
        System.out.println(line);
        System.out.println(msg);
        System.out.println(line);
        System.out.println();
    }
    /**
     * Prints out the todolist.
     */
    private static void printList() {
        String line = "__________________________________________________________";
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (Task task : taskList) {
            System.out.println((index++) + "." + task);
        }
        System.out.println(line);
        System.out.println();
    }

    public static void main(String[] args) {
        logo();
        greet();
        Scanner sc = new Scanner(System.in);
        boolean isExited = false;
        while (!isExited) {
            // String input
            try {
                String input = sc.nextLine();
                switch (input) {
                    case "bye":
                        isExited = true;
                        bye();
                        break;
                    case "list":
                        printList();
                        break;
                    default:
                        String[] split = input.split(" ", 2);
                        switch (split[0]) {
                            case "done":
                                doTask(split);
                                break;
                            case "todo":
                                addToDo(split);
                                break;
                            case "deadline":
                                addDeadline(split);
                                break;
                            case "event":
                                addEvent(split);
                                break;
                            case "delete":
                                deleteTask(split);
                                break;
                            default:
                                echo("OOPS!!! I'm sorry, but I don't know what that means ☹ ");
                        }
                }
            } catch (DukeException e) {
                echo(e.getMessage());
            }
        }
    }
}
