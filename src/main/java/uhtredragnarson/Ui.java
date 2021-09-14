package uhtredragnarson;

import java.util.List;

/**
 * This class handles all the messages the UhtredRagnarson bot will show to the user.
 */
public class Ui {

    public Ui() {
    }

    /**
     * Returns a welcome message when the user first runs the bot.
     *
     * @return The String message.
     */
    protected String showWelcomeMessage() {
        return "Hello! My name is Uhtred Ragnarson, son of Uhtred, of Bebbanburg!\n"
                + "What can i do for you?\n"
                + "\n(type 'help' for a full list of commands and instructions on how to use them)";
    }

    /**
     * Returns a message when a to-do is added.
     *
     * @param todo  The task added.
     * @param tasks List of tasks.
     * @return The String message.
     */
    protected String showTodoMessage(ToDo todo, List<Task> tasks) {
        return "Got it. I've added this task:\n" + "  "
                + todo + "\nNow you have " + tasks.size()
                + " task(s) in the list.";
    }

    /**
     * Returns a message when a deadline is added.
     *
     * @param deadline The task added.
     * @param tasks    List of tasks.
     * @return The String message.
     */
    protected String showDeadlineMessage(Deadline deadline, List<Task> tasks) {
        return "Got it. I've added this task:\n" + "  "
                + deadline + "\nNow you have " + tasks.size()
                + " task(s) in the list.";
    }

    /**
     * Returns a message when an event is added.
     *
     * @param event The task added.
     * @param tasks List of tasks.
     * @return The String message.
     */
    protected String showEventMessage(Event event, List<Task> tasks) {
        return "Got it. I've added this task:\n" + "  "
                + event + "\nNow you have " + tasks.size()
                + " task(s) in the list.";
    }

    /**
     * Marks a task as done.
     *
     * @param task The task to be marked as done.
     * @return The String message.
     */
    protected String showDoneMessage(Task task) {
        return "Nice! I've marked this task as done:\n"
                + "  " + task.toString();
    }

    /**
     * Deletes a task.
     *
     * @param task  The task to be deleted.
     * @param tasks List of tasks.
     * @return The String message.
     */
    protected String showDeleteMessage(Task task, List<Task> tasks) {
        return "Noted. I've removed this task:\n"
                + "  " + task + "\nNow you have " + tasks.size()
                + " task(s) in the list.";
    }

    /**
     * Prints the matching tasks based on the given keyword.
     *
     * @param tasks List of tasks.
     * @return The String message.
     */
    protected String showMatchingTasks(List<Task> tasks) {
        if (tasks.size() == 0) {
            return "I can't find any matching tasks in your list! :(";
        }
        StringBuilder result = new StringBuilder("Here are the matching tasks in your list:\n");
        int counter = 1;
        for (Task task : tasks) {
            result.append(counter).append(".").append(task.toString()).append("\n");
            counter++;
        }
        return result.toString();
    }

    protected String showHelpMessage() {
        return "Uhtred is a chat bot that helps you organise your tasks. You can add "
                + "different types of tasks such as todos, deadlines and events.\n"
                + "To add todos, type 'todo (description)'\n"
                + "To add deadlines, type 'deadline (description) /by (date in the format of yyyy-mm-dd) "
                + "(time in the format hh:mm)'\n"
                + "To add events, type 'event (description) /at (date in the format of yyyy-mm-dd) "
                + "(time in the format hh:mm) to (time in the format hh:mm)'\n"
                + "To list your tasks, type 'list'\n"
                + "To mark tasks as done, type 'done (task number as of list)'\n"
                + "To delete tasks, type 'delete (task number as of list)'\n"
                + "To find matching tasks based on a keyword, type 'find (keyword)'";
    }
}
