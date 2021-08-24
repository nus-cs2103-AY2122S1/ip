package duke.ui;

import duke.tasklist.TaskList;

import java.util.Scanner;

/**
 * Deals with the interaction with the user.
 */
public class Ui {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Reads the text inputted by the user.
     * @return A String representing the user input.
     */
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

    /**
     * Prints the message inputted.
     * @param message The message to be outputted.
     */
    public void printsMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints the tasks in the task list.
     * @param taskList The tasks that will be printed.
     */
    public void printTasks(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getAllTasks().size(); i++) {
            System.out.printf("%d. %s%n", i + 1, taskList.getAllTasks().get(i));
        }
    }
}
