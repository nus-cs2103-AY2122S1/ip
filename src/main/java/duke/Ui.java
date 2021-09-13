package duke;

/**
 * Ui class provides methods for user interaction
 */
public class Ui {
    private static final String line = "_______________________________________________________\n";

    /**
     * Shows a welcome text
     *
     * @return a String representing welcome text
     */
    public String showWelcome() {
        return line + " Hello! I'm Duke\n" + " What can I do for you?\n" + line;
    }

    /**
     * Shows a farewell text
     *
     * @return a String representing farewell text
     */
    public String showBye() {
        return line + "Bye. Hope to see you again soon!\n" + line;
    }

    /**
     * Shows a response when user uses the 'list' command
     *
     * @param list a String representing the list
     * @return a String representing the list with lines
     */
    public String list(String list) {
        return line + list + line;
    }

    /**
     * Shows a response when user uses the 'done' command
     *
     * @param t takes in a task that user marked as done
     * @return a String representing the done task
     */
    public String done(Task t) {
        return line + "Nice! I've marked this task as done:\n" + t + "\n" + line;
    }

    /**
     * Shows a response when user uses the 'delete' command
     *
     * @param tasks takes in a TaskList
     * @param delete takes in a Task representing the deleted task
     * @return a String representing the deleted task
     */
    public String delete(TaskList tasks, Task delete) {
        return line + "Noted. I've removed this task:\n" + delete
                + "\nNow you have " + tasks.size() + " tasks in the list.\n" + line;
    }

    /**
     * Shows error message when index is out of bound
     *
     * @return a String representing the error message
     */
    public String showNoSuchTaskError() {
        return "\n" + line
                + "\nOOPS!!! No such task.\n" + line;
    }


    /**
     * Shows a response when user uses the 'todo' command
     *
     * @param tasks takes in a TaskList
     * @param todo takes in a Task that user added as todo
     * @return a String representing the todo Task
     */
    public String todo(TaskList tasks, Task todo) {
        return line + "Got it. I've added this task:\n" + todo
                + "\nNow you have " + tasks.size() + " tasks in the list.\n" + line;
    }

    /**
     * Shows a response when user uses the 'deadline' command
     *
     * @param tasks takes in a TaskList
     * @param deadline takes in a Task that user added as deadline
     * @return a String representing the deadline task
     */
    public String deadline(TaskList tasks, Task deadline) {
        return line + "Got it. I've added this task:\n" + deadline
                + "\nNow you have " + tasks.size() + " tasks in the list.\n" + line;
    }

    /**
     * Shows an error message for 'deadline' command if date is not in yyyy-mm-dd format
     *
     * @return a String representing the error message
     */
    public String showDeadlineError() {
        return "\n" + line
                + "\nOOPS!!! The date must be in the format YYYY-MM-DD\n" + line;
    }

    /**
     * Shows a response when user uses the 'event' command
     *
     * @param tasks takes in a TaskList
     * @param event takes in a Task that user added as event
     * @return a String representing the event task
     */
    public String event(TaskList tasks, Task event) {
        return line + "Got it. I've added this task:\n" + event
                + "\nNow you have " + tasks.size() + " tasks in the list.\n" + line;
    }

    /**
     * Shows a response when user uses the 'after' command
     *
     * @param tasks takes in a TaskList
     * @param doAfter takes in a Task that user added as DoAfter
     * @return a String representing the DoAfter task
     */
    public String after(TaskList tasks, Task doAfter) {
        return line + "Got it. I've added this task:\n" + doAfter
                + "\nNow you have " + tasks.size() + " tasks in the list.\n" + line;
    }

    /**
     * Shows a respone when user uses the 'find' command
     *
     * @param findList takes in a String representing the results of find
     * @return a String representing the task found by find
     */
    public String find(String findList) {
        return line + "Here are the matching tasks in your list:" + findList + line;
    }

    /**
     * Shows an error message when description of task is empty
     *
     * @return a String representing the error message
     */
    public String emptyDescriptionError() {
        return "\n" + line + "\nOOPS!!! The description cannot be empty.\n" + line;
    }

    /**
     * Shows an error message when user command is invalid
     *
     * @return a String representing the error message
     */
    public String defaultError() {
        return "\n" + line
                + "\nOOPS!!! I'm sorry, but I don't know what that means :-(.\n" + line;
    }
}
