package duke.gui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Class that encapsulates user interaction.
 */
public class Ui {
    /**
     * Displays the welcome message.
     */
    public static String showWelcome() {
        return "Hello! I'm Timothy Q. Mouse,\n"
                + "What can I do for you?";
    }

    /**
     * Display task done message.
     * @param task The completed task.
     */
    public String showTaskDone(Task task) {
        return "Nice! I've marked this task as done:\n" + task.toString();
    }

    /**
     * Displays add task message.
     * @param task The added task.
     * @param taskNo Task number.
     * @param t "task" or "tasks".
     */
    public String showAddTask(Task task, int taskNo, String t) {
        return "Got it. I've added this task:\n"
                + task.toString()
                + "\n"
                + "Now you have "
                + taskNo
                + t
                + "in the list.";
    }

    /**
     * Displays delete task message.
     * @param task The deleted task.
     * @param tasksLeft Number of tasks left.
     * @param t "task" or "tasks"
     */
    public String showDeleteTask(Task task, int tasksLeft, String t) {
        return "Noted. I've removed this task:\n"
                + task.toString()
                + "\n"
                + "Now you have "
                + tasksLeft
                + t
                + "in the list.";
    }

    /**
     * Displays tasks on a specified date.
     * @param date Specified date.
     * @param num Number of tasks.
     * @param tasks List of tasks occurring on that date.
     */
    public String showTasksOnDate(LocalDate date, int num, List<Task> tasks) {
        String d = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String s = num == 0
                ? "You have 0 tasks occurring on " + d + ".\n"
                : num == 1
                ? "You have 1 task occurring on " + d + ":\n"
                : "You have " + num + " tasks occurring on " + d + ":\n";
        for (Task task : tasks) {
            s += task + "\n";
        }
        return s;
    }

    /**
     * Displays all the tasks in the list.
     * @param tasks List of the tasks.
     */
    public String printList(TaskList tasks) {
        return "Here are the tasks in your list:\n"
                + tasks.saveList();
    }

    /**
     * Displays tasks in the list that matches a keyword.
     * @param tasks The list of matching tasks.
     */
    public String showMatchingTasks(TaskList tasks) {
        return "Here are the matching tasks in your list:\n"
                + tasks.saveList();
    }

    /**
     * Displays all the accepted commands.
     */
    public String showHelp() {
        return "Here is the list of commands I can understand:\n"
                + "list: displays your task list.\n"
                + "todo <task>: adds a todo to your task list.\n"
                + "deadline <task> /by <yyyy-mm-dd>: adds a deadline to your task list.\n"
                + "event <task> /at <yyyy-mm-dd>: adds an event to your tasks list.\n"
                + "done <task number>: marks the specific task as done.\n"
                + "delete <task number>: deletes the specific task from your task list.\n"
                + "occurring on <yyyy-mm-dd>: displays tasks occurring on the specified day.\n"
                + "find <keyword>: displays tasks that contain the keyword.\n"
                + "bye: quits the program.";
    }

    /**
     * Displays the farewell message.
     */
    public String showFarewell() {
        return "Bye dude! Hope to see you again soon!";
    }
}
