package duke;

import duke.tasks.Task;

import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints a horizontal line.
     */
    public void printHorizontalLine() {
        System.out.println("----------------------------------------------------");
    }

    /**
     * Prints a message to show users the number of tasks there are in the list.
     *
     * @param index index of items, i.e. the number of tasks
     */
    public void printTaskNum(int index) {
        System.out.printf("\nNow you have %d tasks in the list.\n", index);
    }

    /**
     * Prints a message to show the task that has been successfully added to the list.
     *
     * @param task task added to items list
     */
    public void printAddTask(Task task) {
        System.out.println("Got it. I've added this task:\n" + task);
    }

    /**
     * Prints the welcome message when the bot is first called.
     */
    public void printWelcomeMessage() {
        String logo = " ______       ____      __\n"
                + "|   _   \\    /    \\    |  |\n"
                + "|  |_|  /   /  /\\  \\   |  |\n"
                + "|  |_|  \\  /  ____  \\  |  |\n"
                + "|_______/ /__/    \\__\\ |__|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Bai.\n" +
                "What can I do for you?");
        printHorizontalLine();

    }

    public void printExitMessage() {
        System.out.println("====================================================\n" +
                "Goodbai. Hope to see you again soon! （ ● ___ ●.）" +
                "\n====================================================");
    }

    public void printTaskAdded(Task task, int size) {
        printHorizontalLine();
        printAddTask(task);
        printTaskNum(size);
        printHorizontalLine();
    }

    public String readCommand() {
        String input = sc.nextLine();

        while (input.trim().isEmpty()) {
            input = sc.nextLine();
        }

        return input;
    }

    public void printError(String err) {
        printHorizontalLine();
        System.out.println(err);
        printHorizontalLine();
    }
}
