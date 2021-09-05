package duke;

import java.util.Scanner;

/**
 * Represents a user interface that outputs message to user.
 */
public class Ui {

    public static String getUserInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static void printDukeLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void printNewUserWelcomeMessage() {
        System.out.println("Hello! I'm Duke");
    }

    public static void printExistingUserWelcomeMessage() {
        System.out.println("Hello! Welcome back!");
    }

    public static void printLeftoverTaskMessage(TaskList taskList) {
        System.out.println("These are the task(s) you have left off:");
        taskList.list();
    }

    public static void printInputRequestMessage() {
        System.out.println("What can I do for you?");
    }

    public static void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printInvalidCommandMessage() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

}
