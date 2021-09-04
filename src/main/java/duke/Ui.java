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
        assert (task != null);
        String addedNotification = "Added: " + task.toString();
        String taskSize = Integer.toString(this.taskList.size());
        boolean isLessThanTwoTasks = this.taskList.size() <= 1;
        String fullNotification = addedNotification + "\n"
                + "Now you have " + taskSize + " task"
                + (isLessThanTwoTasks ? "" : "s") + " in the list";
        return fullNotification;
    }

    /**
     * Notifies about the newly deleted task.
     *
     * @param toBeDeleted The task to be deleted.
     * @return Deleted task.
     */
    public String showDeletedTask(Task toBeDeleted) {
        assert (toBeDeleted != null);
        boolean isLessThanTwoTasks = this.taskList.size() <= 1;
        String fullNotification = "Noted. I've removed this task:\n"
                + toBeDeleted + "\n"
                + "Now you have " + this.taskList.size()
                + " task" + (isLessThanTwoTasks ? "" : "s") + " in the list";
        return fullNotification;
    }

    /**
     * Notifies about the task being marked as done.
     *
     * @param markedTask The task marked as done.
     * @return Marked tasks
     */
    public String showMarkedAsDone(Task markedTask) {
        assert (markedTask != null);
        String fullNotification = " Nice! I've marked this task as done:\n" + markedTask;
        return fullNotification;
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
            StringBuilder fullNotification = new StringBuilder("Here are the matching tasks in your list:\n");

            for (int i = 0; i < taskList.size(); i++) {
                fullNotification.append(i + 1).append(". ").append(taskList.get(i).toString()).append("\n");
            }
            return fullNotification.toString();
        }
    }

    /**
     * Shows help.
     * @return Help page.
     */
    public String showHelp() {
        String helpPage = "Available commands: \n"
                + "list: show a list of tasks" + "\n"
                + "done x: Mark a task at index x as done" + "\n"
                + "deadline: create a deadline task" + "\n"
                + "event: create an event task" + "\n"
                + "todo: create a todo task" + "\n"
                + "delete x: delete a task at index x" + "\n"
                + "bye: exit the program" + "\n"
                + "find: find tasks with keyword entries" + "\n"
                + "help: show help page" + "\n";
        return helpPage;
    }
}
