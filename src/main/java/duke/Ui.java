package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    public static String LINE = "____________________________________________________________";
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void print(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    public void printAdd(Task toAdd, int size) {
        String message = "Got it. I've added this task:\n" + "  " + toAdd
                + "\nNow you have " + size + " tasks in the list.";

        print(message);
    }

    public void print(TaskList tasks) {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + "." + tasks.taskToString(i));

        }
        System.out.println(LINE);
    }

    public String buildMessage(String message) {
        // to build error messages
        return LINE + "\n" + message + "\n" + LINE;
    }

    public String readInput() {
        return sc.nextLine();
    }

    public void welcome() {
        print("Hello! I'm duke.Duke\n"
                + " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "What can I do for you?");
    }

    public void exit() {
        print("Bye. Hope to see you again soon!");

        sc.close();
    }

}
