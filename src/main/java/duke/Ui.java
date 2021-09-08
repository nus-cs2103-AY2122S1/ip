package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    private boolean isExit;
    private String response;

    private static final String DIVIDER = "-------------------------------------";
    private static final String LS = System.lineSeparator();
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke \n" + "What can I do for you?";

    /**
     * Constructs a Ui Object.
     */
    public Ui() {
        isExit = false;
    }

    /** Shows message(s) to the user in CLI
     *
     * Inspired by addressbook-level 2 Ui.
     */
    public void showToUser(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }

    /**
     * Prints a standard line break.
     */
    public void showDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Prints welcome message statement to the terminal and returns the message.
     */
    public String showWelcomeMessage() {
        showToUser(
                DIVIDER,
                WELCOME_MESSAGE,
                DIVIDER
        );
        return WELCOME_MESSAGE;
    }

    /**
     * Checks if program has exited.
     * @return boolean isExit.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Prints goodbye statement.
     *
     * Changes isExit to true.
     */
    public String sendGoodbye() {
        String exitMessage = "Bye. Hope to see you again soon!";
        showDivider();
        System.out.println(exitMessage);
        showDivider();
        this.isExit = true;
        return exitMessage;
    }

    /**
     * Prints response after successfully adding task.
     *
     * @param task Added Task.
     */
    public void sendAddTask(Task task) {
        String acknowledgement = "Got it. I've added this task:";
        String taskAdded = task.toString();

        showDivider();
        System.out.println(acknowledgement);
        System.out.println(taskAdded);
        showDivider();

        response = acknowledgement + taskAdded;
    }

    /**
     * Prints done task statement.
     *
     * @param task Completed Task.
     */
    public void sendDone(Task task) {
        showDivider();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
        showDivider();
    }

    /**
     * Prints deleted task statement.
     *
     * @param task Deleted Task.
     */
    public void sendDeleted(Task task) {
        showDivider();
        System.out.println("Noted. I've removed this task:\n" + task.toString());
        showDivider();
    }

    /**
     * Prints matched Tasks.
     *
     * @param tasks ArrayList of matched tasks.
     */
    public void sendMatchedTasks(ArrayList<Task> tasks) {
        showDivider();
        int count = 1;

        if (tasks.size() == 0) {
            System.out.println("No matches!");
        } else {
            for (Task t : tasks) {
                System.out.println(count + ". " + t.toString());
                count += 1;
            }
        }
        showDivider();
    }

    /**
     * Prints all tasks statement.
     *
     * @param tasks Entire
     */
    public void enumTasks(ArrayList<Task> tasks) {
        showDivider();
        int count = 1;

        for (Task t : tasks) {
            System.out.println(count + ". " + t.toString());
            count += 1;
        }
        showDivider();
    }

    /**
     * Prints error message.
     *
     * @param s
     */
    public void showError(String s) {
        System.err.println(s);
    }

    public String getResponse() {
        return response;
    }
}
