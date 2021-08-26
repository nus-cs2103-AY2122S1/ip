package duke;

import java.util.Scanner;

public class UI {

    private final Scanner in;

    public UI(Scanner in) {
        this.in = in;
    }

    public String getUserInput() {
        return in.nextLine();
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you today?");
    }

    public void showException(Exception e) {
        System.out.println(e.getMessage());
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showTasks(TaskList tasks) {
        for (int i = 0; i < tasks.numberOfTasks(); i++) {
            System.out.printf("%d. %s%n", i + 1, tasks.getTask(i));
        }
    }

    public void goodBye() {
        System.out.println("Bye.");
    }

}
