package duke;

import java.util.List;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private final TaskList taskList;

    /**
     * Constructor for Ui.
     *
     * @param taskList The task list to be displayed.
     */
    public Ui(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Shows upon exiting the program.
     */
    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays the task list.
     *
     * @return task display.
     */
    public String displayTasks() {
        String tasks = "Here are the tasks in your list:\n";

        for (int i = 0; i < this.taskList.size(); i++) {
            tasks += (i + 1) + ". " + taskList.get(i).toString() + "\n";
        }
        return tasks;
    }

    /**
     * Notifies about the newly added task.
     *
     * @param task The new task added.
     * @return Added task.
     */
    public String showAddedTask(Task task) {
        String output = "Added: " + task.toString();
        System.out.println(output);
        return output + "\n"
                + "Now you have " + this.taskList.size() + " task"
                + ((this.taskList.size() <= 1) ? "" : "s") + " in the list";
    }

    /**
     * Notifies about the newly deleted task.
     *
     * @param toBeDeleted The task to be deleted.
     * @return Deleted task.
     */
    public String showDeletedTask(Task toBeDeleted) {
        return "Noted. I've removed this task:\n"
                + toBeDeleted + "\n"
                + "Now you have " + this.taskList.size()
                + " task" + ((this.taskList.size() <= 1) ? "" : "s") + " in the list";
    }

    /**
     * Notifies about the task being marked as done.
     *
     * @param markedTask The task marked as done.
     * @return Marked tasks
     */
    public String showMarkedAsDone(Task markedTask) {
        return " Nice! I've marked this task as done:\n" + markedTask;
    }

    /**
     * Show a list of tasks found.
     *
     * @param taskList A list of tasks.
     * @return found tasks.
     */
    public String showFoundTask(List<Task> taskList) {
        if (taskList.isEmpty()) {
            return "OOPS, I couldn't find anything in your task list.";
        } else {
            String result = "Here are the matching tasks in your list:\n";

            for (int i = 0; i < taskList.size(); i++) {
                result += (i + 1) + ". " + taskList.get(i).toString() + "\n";
            }
            return result;
        }
    }
}
