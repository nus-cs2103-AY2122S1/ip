package duke;

import duke.task.Task;

import java.util.Scanner;

/**
 * Represents the Ui of Duke.
 */
public class Ui {
    public static String LINE = "____________________________________________________________";
    private final Scanner sc;

    /**
     * Constructor of Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints message in same style as other outputs of Duke.
     *
     * @param message Message to be printed in the same style as other
     *                outputs of Duke.
     */
    public void print(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    /**
     * Prints add message in same style as other outputs of Duke whenever
     * a new Task has been added.
     *
     * @param toAdd Task to be added.
     * @param size Size of TaskList.
     */
    public void printAdd(Task toAdd, int size) {
        String message = "Got it. I've added this task:\n" + "  " + toAdd + "\nNow you have " + size + " tasks in the list.";
        print(message);
    }

    /**
     * Prints out TaskList in a form of a list for users to see.
     *
     * @param ls TaskList to be printed.
     */
    public void print(TaskList ls) {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < ls.getSize() ; i++) {
            System.out.println((i + 1) + "." + ls.taskToString(i));
        }
        System.out.println(LINE);
    }

    /**
     * Builds message in same form as other outputs of Duke.
     *
     * @param message Message to be built in the same form as other outputs.
     * @return Message which has been built in the same form as other outputs.
     */
    public String buildMessage(String message) {
        // to build error messages
        return LINE + "\n" + message + "\n" + LINE;
    }

    /**
     * Reads next line of user's input.
     *
     * @return String representation of user's input.
     */
    public String readInput() {
        return sc.nextLine();
    }

    /**
     * Ui to welcome user to Duke.
     */
    public void welcome() {
        print("Hello! I'm duke.Duke\n"
                + " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "What can I do for you?");
    }

    /**
     * Ui to say bye to user and closes scanner.
     */
    public void exit() {
        print("Bye. Hope to see you again soon!");
        sc.close();
    }

}
