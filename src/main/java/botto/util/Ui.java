package botto.util;

import java.util.List;
import java.util.Scanner;

import botto.task.Task;

/**
 * This class deals with the Botto bot's interactions with the user
 */
public class Ui {
    private static final String BOT_NAME = "Botto";
    private static final String INDENTATION = "    ";

    private Scanner scanner;

    /**
     * Constructor for the bot's ui
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * return input inserted by the user
     *
     * @return input inserted by the user
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * print a divider line
     */
    public void showLine() {
        System.out.println(INDENTATION + "------------------------------");
    }

    /**
     * print the bot's welcome message
     */
    public void showWelcome() {
        showLine();

        String greet = INDENTATION + "Hello! I'm " + BOT_NAME + ".\n"
                + INDENTATION + "What can I do for you?";
        System.out.println(greet);

        showLine();
    }


    /**
     * print the user's tasks
     *
     * @param list list of tasks to be printed
     * @param header header message to be printed before printing the tasks
     */
    public void showTasks(List<Task> list, String header) {
        System.out.println(INDENTATION + header);

        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            System.out.println(INDENTATION + (i + 1) + ". " + task);
        }
    }

    /**
     * print a response when a new task is added
     *
     * @param task new task to be added
     * @param size total number of the user's tasks after addition
     */
    public void respondAdd(Task task, int size) {
        String response = INDENTATION + "Got it! I've added this task:\n"
                + INDENTATION + "  " + task + "\n"
                + INDENTATION + "Now you have " + size + " tasks in the list.";

        System.out.println(response);
    }

    /**
     * print a response when a tasks is deleted
     *
     * @param task deleted task
     * @param size total number of the user's tasks after deletion
     */
    public void respondDelete(Task task, int size) {
        String response = INDENTATION + "Noted. I've removed this task:\n"
                + INDENTATION + "  " + task + "\n"
                + INDENTATION + "Now you have " + size + " tasks in the list.";

        System.out.println(response);
    }

    /**
     * print a response when a task is marked as done
     *
     * @param task the task which is just marked as done
     */
    public void respondDone(Task task) {
        String response = INDENTATION + "Nice! I've marked this task as done:\n"
                + INDENTATION + "  " + task;

        System.out.println(response);;
    }

    /**
     * print a goodbye message
     */
    public void sayGoodBye() {
        System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
    }

    /**
     * print a error message
     *
     * @param message error message
     */
    public void showError(String message) {
        System.out.println(INDENTATION + message);
    }

}
