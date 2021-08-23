package duke.ui;

import java.util.Scanner;

/**
 * Represents a user interface (command prompt line) of chat bot.
 */
public class Ui {
    /**
     * Displays large logo and welcome message.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greet = "____________________________________________________________\n"
                     + "Hello! I'm Duke\n"
                     + "What can I do for you?\n"
                     + "____________________________________________________________";
        System.out.println(greet);
    }

    /**
     * Displays loading error message.
     */
    public void showLoadingError() {
        System.out.println("An error occurred when loading file.");
    }

    /**
     * Displays goodbye message.
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays error message.
     */
    public void showError(String message) {
        System.out.println("☹ OOPS!!! " + message);
    }

    /**
     * Displays a line.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Returns user input of command line.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Displays new command added message.
     */
    public void showAdded(String tasks, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks);
        System.out.printf("Now you have %d tasks in the list.%n", size);
    }
    public void showDelete(String tasks, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks);
        System.out.printf("Now you have %d tasks in the list.%n", size);
    }

    public void showFind(String tasks) {
        System.out.println("Here are the matching tasks in your list:");
        System.out.println(tasks);
    }
    public void showList(String tasks) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(tasks);
    }

    public void showDone(String tasks) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks);
    }
}
