package duke;

import java.util.Scanner;

public class Ui {

    private static final String divider = "____________________________________________________________";

    private static final String logo =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    private final Scanner scanner = new Scanner(System.in);

    public void greet() {
        System.out.println("Hello from\n" + logo +"\n");
        System.out.println("What can I do for you?");
        showLine();
    }

    public void goodBye() {
        scanner.close();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    public void showLine() {
        System.out.println(divider);
    }

    public void taskCreatedMessage(Task task, TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showError(String message) {
        System.out.println(message);
    }
}
