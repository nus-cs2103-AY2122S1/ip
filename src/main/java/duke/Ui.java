package duke;
import java.util.Scanner;

import duke.Task.*;

public class Ui {
    public static void displayGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello, What Can I do for you ?\n -------------------------------");
    }
    public static String takeInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        userInput = scanner.nextLine();
        return userInput;
    }
    public static void displayQuitMessage() {
        System.out.println("Duke : Bye, Hope to see you again soon !");
    }
    public static void displayTaskList(TaskList tasks) {
        System.out.println(tasks.formattedToString());
        Ui.displayLineBreak();

    }
    public static void displayMarkedTaskAsDone(Task doneTask) {
        System.out.println("Nice! I've marked this task as done: \n" + doneTask);
        Ui.displayLineBreak();
    }
    public static void displaySuccessfulRemoval(Task removedTask) {
        System.out.println("Successfully removed task : " + removedTask);
    }
    public static void displayErrorMessage(Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
    public static void displayLineBreak() {
        System.out.println("\n ----------------------------------");
    }
}
