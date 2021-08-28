package duke;

import java.util.Scanner;

/**
 * Class responsible for interactions with the user
 */
public class UI {
    /** Prints welcome message **/
    public static void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Stay on track with Duke!\n"
                + "How can I help you?");
    }

    /** Prints goodbye message **/
    public static void bye() {
        System.out.println("¡Adiós! See you soon!");
    }

    /** Asks user for input **/
    public static String readCommand() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your command:");
        return sc.nextLine();
    }

    /** Prints a message for when there is an unknown DukeException **/
    public static void dukeException() {
        System.out.println("There seems to be an error with Duke."
                + "Please try again!");
    }

    /**
     * Prints a message when a task is marked as done
     * @param taskNumber Number of the task in TaskList
     */
    public static void done(int taskNumber) {
        System.out.printf("Solid work! I've marked task %d as done.%n", taskNumber);
    }

    /**
     * Prints a message when a new task is added to TaskList
     * @param taskType String representing the type of task
     */
    public static void added(String taskType) {
        System.out.printf("Great! I've added your %s. Enter 'list' to see your tasks!%n", taskType);
    }

    /**
     * Prints a message when a task is deleted from TaskList
     * @param taskNumber Number of task in TaskList
     */
    public static void delete(int taskNumber) {
        System.out.printf("Got it! I've removed task %d.%n", taskNumber);
    }

    /**
     * Prints a message to show the number of current tasks
     * @param numberOfTasks Number of current tasks
     */
    public static void numberOfTasks(int numberOfTasks) {
        System.out.printf("You currently have %d tasks.%n", numberOfTasks);
    }
}
