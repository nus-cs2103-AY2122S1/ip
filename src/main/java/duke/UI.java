package duke;

import duke.task.Task;

/**
 * Deals with interactions with the user.
 */
public class UI {
    /**
     * Returns the welcome message when the program starts.
     *
     * @return Welcome message.
     */
    public String welcomeResponse() {
        return "Hello I'm Duke\nWhat can I do for you?\n";
    }

    /**
     * Returns the message after executing an exit command.
     *
     * @return Exit command response.
     */
    public String exitResponse() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Returns the message after executing a done command.
     *
     * @param task The task that was marked as done.
     * @return Done command response.
     */
    public String doneResponse(Task task) {
        return String.format("Nice! I've marked this task as done:\n%s\n", task);
    }

    /**
     * Returns the message after executing a list command.
     *
     * @param list String of the list of tasks.
     * @return List command response.
     */
    public String listResponse(String list) {
        return "Here are the tasks in your list:\n" + list;
    }

    /**
     * Returns the message after executing a delete command.
     *
     * @param task The task that was deleted.
     * @param size Updated size of the list.
     * @return Delete command response.
     */
    public String deleteResponse(Task task, int size) {
        return String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list\n", task, size);
    }

    /**
     * Returns the message after executing a find command.
     *
     * @param list String of the list of tasks containing the keyword.
     * @return Find command response.
     */
    public String findResponse(String list) {
        return "Here are the matching tasks in your list:\n" + list;
    }

    /**
     * Returns the message after executing an add command.
     *
     * @param task The task that was added.
     * @param size Updated size of the list.
     * @return Add command response.
     */
    public String addResposne(Task task, int size) {
        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list\n", task, size);
    }

    /**
     * Returns the message after executing an update command.
     *
     * @param task The task that was added.
     * @return Update command response.
     */
    public String updateResponse(Task task) {
        return String.format("Got it. I've updated the task to be:\n%s\n", task);
    }

    /**
     * Prints the error message.
     *
     * @param message Error message.
     */
    public void showError(String message) {
        System.out.println(message);
    }
}
