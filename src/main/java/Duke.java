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
     * Adds item to the todolist.
     * @param task
     */
    private static void addToList(Task task) {
        taskList[listIndex++] = task;
        echo("Got it. I've added this task:\n  " + task + "\nNow you have " + listIndex + " tasks in the list.");
    }
    /**
     * Marks task as done in taskList.
     * @param input The input string.
     */
    private static void doTask(String input) {
        int taskID = Integer.parseInt(input);
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
    }
    /**
     * Frames the message with underscore lines and prints it.
     *
     * @param msg The String we want to frame.
     * @return The framed String.
     */
    private static void echo(String msg) {
        String line = "________________________________";
        System.out.println(line);
        System.out.println(msg);
        System.out.println(line);
        System.out.println();
    }
    /**
     * Prints out the todolist.
     */
    private static void printList() {
        String line = "________________________________";
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
            String input = sc.nextLine();
            switch(input) {
                case "bye":
                    isExited = true;
                    bye();
                    break;
                case "list":
                    printList();
                    break;
                default:
                    String[] split = input.split(" ", 2);
                    switch(split[0]) {
                        case "done":
                            if (split.length > 1) {
                                doTask(split[1]);
                                break;
                            }
                        case "todo":
                            if (split.length > 1) {
                                addToList(new ToDo(split[1]));
                                break;
                            }
                        case "deadline":
                            if (split.length > 1) {
                                String[] deadline = split[1].split("/", 2);
                                addToList(new Deadline(deadline[0], deadline[1].substring(3)));
                                break;
                            }
                        case "event":
                            if (split.length > 1) {
                                String[] event = split[1].split("/", 2);
                                addToList(new Event(event[0], event[1].substring(3)));
                                break;
                            }
                        default:
                            echo("OOPS! Something went wrong x.x");
                    }
            }
        }
    }
}
