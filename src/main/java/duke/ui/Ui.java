package duke.ui;

import duke.tasklist.TaskList;

/**
 * Represents an Ui class that is responsible for the interaction with user.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */
public class Ui {

    /**
     * Returns a welcome message from chat bot.
     *
     * @return String message to welcome user.
     */
    public String welcome() {
        String welcome = "Duke: Hello! I'm Duke, a bot to help you track tasks. "
                + "Enter 'help' to find out more about the available command lines!";
        return welcome;
    }

    /**
     * Returns a goodbye message when user enter exit command.
     *
     * @return String of the goodbye message.
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a message when to-do task is added.
     *
     * @param addedTask Task added.
     * @param size Size of current list.
     * @return String message of the added task.
     */
    public String showAddTodo(String addedTask, String size) {
        return "Got it. I've added this task: \n" + addedTask + "\nNow you have "
                + size + " tasks in the list.";
    }

    /**
     * Returns a message when deadline task is added.
     *
     * @param addedTask Task added.
     * @param size Size of current list.
     * @return String message of the added task.
     */
    public String showAddDeadline(String addedTask, String size) {
        return "Got it. I've added this task: \n" + addedTask + "\nNow you have "
                + size + " tasks in the list.";
    }

    /**
     * Returns a message when event task is added.
     *
     * @param addedTask Task added.
     * @param size Size of current list.
     * @return String message of the added task.
     */
    public String showAddEvent(String addedTask, String size) {
        return "Got it. I've added this task: \n" + addedTask + "\nNow you have "
                + size + " tasks in the list.";
    }

    /**
     * Returns a message when task is deleted.
     *
     * @param deletedTask Task deleted.
     * @param size Size of current list.
     * @return String message of the deleted task.
     */
    public String showDelete(String deletedTask, String size) {
        return "Noted. I've removed this task: \n" + deletedTask
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Returns a list of matching tasks.
     *
     * @param keyword Keyword to search.
     * @param tasks Current list of tasks.
     * @return String message of the matching tasks.
     */
    public String showMatchList(String keyword, TaskList tasks) {
        String result = "Here are the matching tasks in your list:";
        int count = 1;
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.getTask(i).contains(keyword)) {
                result += "\n" + count + "." + tasks.getTask(i);
                count += 1;
            }
        }
        return result;
    }

    /**
     * Returns a list of current tasks.
     *
     * @param tasks Current list of tasks.
     * @return String message of current list of tasks
     */
    public String showList(TaskList tasks) {
        int count = 1;
        String str = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.getSize(); i++) {
            str += "\n" + count + "." + tasks.getTask(i);
            count += 1;
        }
        return str;
    }

    /**
     * Returns help message to guide user in navigating the bot.
     *
     * @return String message to help user navigate the bot.
     */
    public String showHelp() {
        return "This bot help you track tasks. Use the following format for command:\n"
                + "1. Add to-do task: todo <description>\n"
                + "2. Add deadline task: deadline <description> /by <yyyy-mm-ddTHH:mm>\n"
                + "3. Add event task: event <description> /from <yyyy-mm-ddTHH:mm> /to <yyyy-mm-ddTHH:mm>\n"
                + "4. Mark task done: done <integer>\n"
                + "5. Delete task: delete <integer>\n"
                + "6. Find tasks with keyword: find <keyword>\n"
                + "7. List all tasks: list\n"
                + "8. Help: help\n"
                + "9. Exit: bye";
    }
}
