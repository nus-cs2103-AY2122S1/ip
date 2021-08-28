package duke;

import duke.task.Task;

import java.util.Collection;

/**
 * Handles Duke's User Interface.
 */
public class Ui {

    private static final String DIVIDER = "------------------------------------------";
    private static final String LOGO = " ____        _        \n"
                                     + "|  _ \\ _   _| | _____ \n"
                                     + "| | | | | | | |/ / _ \\\n"
                                     + "| |_| | |_| |   <  __/\n"
                                     + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Prints a welcome message.
     */
    public static void welcomeMessage() {
        System.out.println("\nHello buddy! I am");
        System.out.println(LOGO);
        System.out.println("What can I do for you?");
    }

    /**
     * Prints a goodbye message.
     */
    public static void goodbyeMessage() {
        System.out.println("Bye. Hope to see you soon!");
    }

    /**
     * Prints a section divider.
     */
    public static void printDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Prints a given String.
     *
     * @param text String to print
     */
    public static void print(String text) {
        System.out.println(text);
    }

    /**
     * Pretty prints a given collection of tasks.
     *
     * @param tasks given collection of tasks
     */
    public static void printTasks(Collection<Task> tasks) {
        int i = 0;
        for (Task t : tasks) {
            System.out.printf("%d. %s\n", i + 1, t);
            i++;
        }
    }
}
