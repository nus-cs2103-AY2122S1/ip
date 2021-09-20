package duke.gui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Storage;

/**
 * Class that encapsulates user interaction.
 */
public class Ui {
    /**
     * Displays the welcome message.
     * @return The welcome message.
     */
    public static String showWelcome() {
        String welcome = "Hi Dumbo! I'm Timothy Q. Mouse,\nWhat can I do for you?";
        try {
            Storage.load();
        } catch (DukeException e) {
            return "Corrupted file, new task list created.\n" + welcome;
        }

        return welcome;
    }

    /**
     * Display task done message.
     * @param task The completed task.
     * @return The task done message.
     */
    public String showTaskDone(Task task) {
        return "Nice! I've marked this task as done:\n" + task.toString();
    }

    /**
     * Displays add task message.
     * @param tasks The list of tasks to add task to.
     * @param task The added task.
     * @return The add task message.
     */
    public String showAddTask(TaskList tasks, Task task) {
        int taskNo = tasks.getSize();
        String t = taskNo == 1 ? " task " : " tasks ";
        return "Got it. I've added this task:\n"
                + task.toString()
                + "\nNow you have "
                + taskNo
                + t
                + "in the list.";
    }

    /**
     * Displays delete task message.
     * @param task The deleted task.
     * @param tasksLeft Number of tasks left.
     * @param t "task" or "tasks"
     * @return The delete task message.
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
     * @return The list of tasks on the specific date.
     */
    public String showTasksOnDate(LocalDate date, int num, List<Task> tasks) {
        String d = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        StringBuilder s = new StringBuilder(num == 0
                ? "You have 0 tasks occurring on " + d + ".\n"
                : num == 1
                ? "You have 1 task occurring on " + d + ":\n"
                : "You have " + num + " tasks occurring on " + d + ":\n");
        for (Task task : tasks) {
            s.append(task).append("\n");
        }
        return s.toString();
    }

    /**
     * Displays all the tasks in the list.
     * @param tasks List of the tasks.
     * @return The tasks in the list.
     */
    public String printList(TaskList tasks) throws DukeException {
        return "Here are the tasks in your list:\n"
                + tasks.saveList();
    }

    /**
     * Displays tasks in the list that matches a keyword.
     * @param tasks The list of matching tasks.
     * @return The tasks that match the keyword.
     */
    public String showMatchingTasks(TaskList tasks) throws DukeException {
        return "Here are the matching tasks in your list:\n"
                + tasks.saveList();
    }

    /**
     * Displays all the accepted commands.
     * @return The list of accepted commands.
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
                + "edit <n or d> <task number> /to <new name or date>: changes the name or date of the task.\n"
                + "bye: quits the program.";
    }

    /**
     * Displays the farewell message.
     * @return The farewell message.
     */
    public String showFarewell() {
        return "Bye little dude! Remember, the very things that hold you down are gonna carry you up and up and up!";
    }

    /**
     * Displays the duplicate task message.
     * @return The duplicate task message.
     */
    public String showDuplicate() {
        return "This task already exists!";
    }

    /**
     * Displays the task changed message.
     * @param task The task that was changed.
     * @return The task changed message.
     */
    public String showChangedTask(Task task) {
        return "Okay! I've updated your task to \n" + task;
    }
}
