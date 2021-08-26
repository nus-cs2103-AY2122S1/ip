package lawbringer;

import java.util.List;

/**
 * This class handles all the messages the Lawbringer bot will show to the user.
 */
public class Ui {

    public Ui() {
    }

    /**
     * Prints a welcome message when the user first runs the bot.
     */
    protected void showWelcomeMessage() {
        String message = "Hello! I'm Lawbringer!\n" +
                "What can i do for you?";
        System.out.println(message);
    }

    /**
     * Prints a bye message when the user exits the bot.
     */
    protected void showByeMessage() {
        String message = "Bye. Hope to see you again soon!";
        System.out.println(message);
    }

    /**
     * Prints a message when a to-do is added.
     *
     * @param todo  The task added.
     * @param tasks List of tasks.
     */
    protected void showTodoMessage(ToDo todo, List<Task> tasks) {
        String message = "Got it. I've added this task:\n" + "  " +
                todo + "\nNow you have " + tasks.size() +
                " task(s) in the list.";
        System.out.println(message);
    }

    /**
     * Prints a message when a deadline is added.
     *
     * @param deadline The task added.
     * @param tasks    List of tasks.
     */
    protected void showDeadlineMessage(Deadline deadline, List<Task> tasks) {
        String message = "Got it. I've added this task:\n" + "  " +
                deadline + "\nNow you have " + tasks.size() +
                " task(s) in the list.";
        System.out.println(message);
    }

    /**
     * Prints a message when an event is added.
     *
     * @param event The task added.
     * @param tasks List of tasks.
     */
    protected void showEventMessage(Event event, List<Task> tasks) {
        String message = "Got it. I've added this task:\n" + "  " +
                event + "\nNow you have " + tasks.size() +
                " task(s) in the list.";
        System.out.println(message);
    }

    /**
     * Marks a task as done.
     *
     * @param task The task to be marked as done.
     */
    protected void showDoneMessage(Task task) {
        String message = "Nice! I've marked this task as done:\n" +
                "  " + task.toString();
        System.out.println(message);
    }

    /**
     * Deletes a task.
     *
     * @param task  The task to be deleted.
     * @param tasks List of tasks.
     */
    protected void showDeleteMessage(Task task, List<Task> tasks) {
        String message = "Noted. I've removed this task:\n" +
                "  " + task + "\nNow you have " + tasks.size() +
                " task(s) in the list.";
        System.out.println(message);
    }
}
