package duke;

import java.util.Scanner;

public class Ui {

    private Scanner input = new Scanner(System.in);

    /**
     * Show welcome message
     */
    public String showWelcome() {
        String message = "Hello! I'm Duke\n" + "What can I do for you?";
        return message;
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
    public String showLoadingError() {
        String message = "No task list file found! Creating a new file for you (:";
        return message;
    }

    /**
     * Show task added
     *
     * @param task
     * @param size
     */
    public String showAddTask(Task task, int size) {
        String message = "Got it. I've added this task:\n" + task + "\nNow you have " + size + " tasks in the list.";
        return message;
    }

    /**
     * Show task deleted
     *
     * @param task
     * @param size
     */
    public String showDeleteTask(Task task, int size) {
        String message = "Noted. I've removed this task:\n" + task + "\nNow you have " + size + " tasks in the list.";
        return message;
    }

    /**
     * Show task marked as done
     *
     * @param task
     */
    public String showDoneTask(Task task) {
        String message = "Nice! I've marked this task as done:\n" + task;
        return message;
    }

    /**
     * Show task list
     *
     * @param tasks
     */
    public String showTaskList(TaskList tasks) {
        String message = tasks.toString();
        return message;
    }

    /**
     * Show goodbye message
     */
    public String showBye() {
        String message = "Bye. Hope to see you again soon!";
        return message;
    }

    /**
     * Show error message
     *
     * @param message
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Show list of searched tasks
     *
     * @param taskList
     */
    public String showFindTask(String taskList) {
        String message = "Here are the matching tasks in your list:\n" + taskList;
        return message;
    }
}
