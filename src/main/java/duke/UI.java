package duke;

import duke.task.Task;

import java.util.Scanner;

public class UI {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static final String DIVIDER = "____________________________________________________________\n";

    private Scanner sc = new Scanner(System.in);

    public void showWelcome() {
        System.out.println("Hello from\n" + LOGO);
        showLine();
        System.out.println("Hello I'm Duke\nWhat can I do for you?\n");
        showLine();
    }

    public void showLine() {
        System.out.print(DIVIDER);
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public void showDone(Task task) {
        System.out.printf("Nice! I've marked this task as done:\n%s\n", task);
    }

    public void showList() {
        System.out.println("Here are the tasks in your list:");
    }

    public void showDelete(Task task, int size) {
        System.out.printf("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list\n", task, size);
    }

    public void showFind() {
        System.out.println("Here are the matching tasks in your list:");
    }

    public void showAdd(Task task, int size) {
        System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list\n", task, size);
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public String readCommand() {
        return sc.nextLine();
    }
}
