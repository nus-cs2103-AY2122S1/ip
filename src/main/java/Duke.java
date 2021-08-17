import java.util.Scanner;
import java.lang.String;
/**
 * The Duke class encapsulates the Duke project's chat-bot for CS2103T individual project 1.
 *
 * @author Chesterwongz
 */
public class Duke {
    private final static int MAX_LIST_LENGTH = 100;
    private static final Task[] taskList = new Task[MAX_LIST_LENGTH];
    private static int listIndex = 0;
    private static boolean isInputBye(String input) {
        return input.compareToIgnoreCase("bye") == 0;
    }
    private static boolean isInputList(String input) {
        return input.compareToIgnoreCase("list") == 0;
    }
    private static boolean isInputDone(String input) {
        return input.startsWith("done ");
    }

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
     * @param task
     */
    private static void addToList(Task task) {
        taskList[listIndex++] = task;
        echo("Got it. I've added this task:\n  " + task + "\nNow you have " + listIndex + " tasks in the list.");
    }

    /**
     * Adds a To-do task to the taskList.
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
     * @param input The input string.
     * @throws DukeException
     */
    private static void doTask(String[] input) throws DukeException {
        if (input.length < 2) {
            throw new DukeException("OOPS!!! Please input the task number to be marked as done!");
        }
        try {
            int taskID = Integer.parseInt(input[1]);
            if (taskID < 1 || taskID > listIndex) {
                throw new DukeException("OOPS!!! Please enter a valid task number!");
            }
            String msg;
            int taskIndex = taskID - 1;
            if (taskList[taskIndex] != null) {
                taskList[taskIndex].markAsDone();
                msg = "Nice! I've marked this task as done:\n  "
                        + taskList[taskIndex].toString();
            } else {
                msg = "OOPS!!! Task " + taskID + " doesn't exist!";
            }
            echo(msg);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please enter a valid task number!");
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
        for (int index = 0; index < listIndex; index++) {
            System.out.println((index + 1) + "." + taskList[index]);
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
