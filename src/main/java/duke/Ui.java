package duke;

import java.util.Scanner;

/**
 * Deals with interactions with the user.
 *
 * @author felix-ong
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello, I am Duke!\nHow may I help you?");
    }

    public void showList() {
        System.out.println("Here are the tasks in your list:");
    }

    public void printTaskInList(Task task, int index) {
        System.out.printf(" %d. %s%n", index + 1, task);
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showLoadingError() {
        System.out.println("X_X Dude failed to load the list of tasks!");
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }

    public void showFound() {
        System.out.println("Here are the matching tasks in your list:");
    }

    /**
     * Exits Duke.
     */
    public void exit() {
        System.out.println("Bye! Feel free to ask me for help again anytime!");
        this.scanner.close();
    }
}
