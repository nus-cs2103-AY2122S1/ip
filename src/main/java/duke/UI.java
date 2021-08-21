package duke;

import duke.task.Task;

import java.util.Collection;

public class UI {

    private static final String DIVIDER = "------------------------------------------";
    private static final String LOGO = " ____        _        \n"
                                     + "|  _ \\ _   _| | _____ \n"
                                     + "| | | | | | | |/ / _ \\\n"
                                     + "| |_| | |_| |   <  __/\n"
                                     + "|____/ \\__,_|_|\\_\\___|\n";

    public static void welcomeMessage() {
        System.out.println("\nHello buddy! I am");
        System.out.println(LOGO);
        System.out.println("What can I do for you?");
    }

    public static void goodbyeMessage() {
        System.out.println("Bye. Hope to see you soon!");
    }

    public static void printDivider() {
        System.out.println(DIVIDER);
    }

    public static void print(String text) {
        System.out.println(text);
    }

    public static void printTasks(Collection<Task> tasks) {
        int i = 0;
        for (Task t: tasks) {
            System.out.printf("%d. %s\n", i + 1, t);
            i++;
        }
    }
}
