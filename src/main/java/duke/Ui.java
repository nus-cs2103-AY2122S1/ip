package duke;

import java.util.Scanner;

public class Ui {

    private Scanner input = new Scanner(System.in);

    /**
     * Show welcome message
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Get user input
     * @return user input command
     */
    public String readCommand() {
        String userInput = input.nextLine();
        return userInput;
    }

    /**
     * Show error loading task file
     */
    public void showLoadingError() {
        System.out.println("No task list file found! Creating a new file for you (:");
    }

    /**
     * Show task added
     *
     * @param task
     * @param size
     */
    public void showAddTask(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Show task deleted
     *
     * @param task
     * @param size
     */
    public void showDeleteTask(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Show task marked as done
     *
     * @param task
     */
    public void showDoneTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Show task list
     *
     * @param tasks
     */
    public void showTaskList(TaskList tasks) {
        System.out.println(tasks);
    }

    /**
     * Show goodbye message
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Show error message
     *
     * @param message
     */
    public void showError(String message) {
        System.out.println(message);
    }
}
