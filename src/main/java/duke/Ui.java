package duke;

import duke.task.*;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private static final String LINE = "     __________________________________________________\n"; // 5 spaces, 50 dashes
    private static final String INDENT = "     "; // 5 spaces
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void printWelcome() {
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greeting = LINE
            + INDENT + "Hello! I'm Duke\n"
            + INDENT + "What can I do for you?\n"
            + LINE;
        System.out.println(greeting);
    }

    public static String readInput() {
        return SCANNER.nextLine();
    }

    public static void printList(List<Task> listToPrint) {
        System.out.println(LINE + INDENT + "Here are the tasks in your list:");
        for (int i = 0; i < listToPrint.size(); i++) {
            Task currTask = listToPrint.get(i);
            System.out.println(INDENT + (i + 1) + "." + currTask.toString());
        }
        System.out.println(LINE);
    }

    public static void printError(String message) {
        System.out.println(LINE + INDENT + message + LINE);
    }

    public static void printDoneTask(String taskString) {
        System.out.println(LINE + INDENT + "Nice! I've marked this task as done:\n"
            + INDENT + INDENT + taskString + "\n"
            + LINE);
    }

    public static void printDeleteTask(String taskString, int taskNo) {
        System.out.println(LINE + INDENT + "Noted. I've removed this task:\n"
            + INDENT + INDENT + taskString + "\n"
            + INDENT + String.format("Now you have %d tasks in the list.\n", taskNo)
            + LINE);
    }

    public static void printAddTask(String taskString, int taskNo) {
        System.out.println(LINE + INDENT + "Got it. I've added this task:\n"
            + INDENT + INDENT + taskString + "\n"
            + INDENT + String.format("Now you have %d tasks in the list.\n", taskNo)
            + LINE);
    }

    public static void printFarewell() {
        System.out.println(LINE + INDENT + "Bye. Hope to see you again soon!\n" + LINE);
    }
}
