package duke.ui;

import duke.tasklist.TaskList;

import java.util.Scanner;

public class Ui {

    private static final Scanner scanner = new Scanner(System.in);

    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a line.
     */
    public void printLine() {
        System.out.println("__________________________________________________________");
    }

    /**
     * Prints the greeting message.
     */
    public void greetUser() {
        printLine();
        System.out.println("Greetings! I am Duke.");
        System.out.println("How can I assist you?");
        printLine();
    }

    /**
     * Prints the farewell message.
     */
    public void farewellUser() {
        printLine();
        System.out.println("Bye! See you soon!");
        printLine();
    }

    public void printsMessage(String message) {
        System.out.println(message);
    }

    public void printTasks(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getAllTasks().size(); i++) {
            System.out.printf("%d. %s%n", i + 1, taskList.getAllTasks().get(i));
        }
    }
}
