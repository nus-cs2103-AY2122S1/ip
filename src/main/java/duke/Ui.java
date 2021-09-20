package duke;
import java.util.Scanner;

import duke.Task.*;

/**
 * Class as interface to display and receive commands from user
 */
public class Ui {
    /**
     * Displays start greeting to user
     */
    public static void displayGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello, What Can I do for you ?\n -------------------------------");
    }

    /**
     * Takes in input string of the user
     * @return user input as string
     */
    public static String takeInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        userInput = scanner.nextLine();
        return userInput;
    }

    public static void displayQuitMessage() {
        System.out.println("Duke : Bye, Hope to see you again soon !");
    }

    public static void displayTaskList(TaskList tasks) {
        System.out.println(tasks.formattedToString());
        Ui.displayLineBreak();

    }

    /**
     * Displays if task is marked as done succesfully
     * @param doneTask task that is marked successfully
     */

    public static void displayMarkedTaskAsDone(Task doneTask) {
        System.out.println("Nice! I've marked this task as done: \n" + doneTask);
        Ui.displayLineBreak();
    }

    /**
     * Displays if task is deleted succesfully
     * @param removedTask task that is deleted successfully
     */

    public static void displaySuccessfulRemoval(Task removedTask) {
        System.out.println("Successfully removed task : " + removedTask);
    }

    /**
     * Displays error message from an occuring exception
     * @param e Exception that occured
     */

    public static void displayErrorMessage(Exception e) {
        System.out.println("Error: " + e.getMessage());
    }

    public static void displayLineBreak() {
        System.out.println("\n ----------------------------------");
    }
}
