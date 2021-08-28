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
     * Greets the user.
     */
    public void greet() {
        String greet = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greet);
    }

    /**
     * Shows upon exiting the program.
     */
    public void bye() {
        System.out.println("___________________________________________________");
        String byeCommand = "Bye. Hope to see you again soon!";
        System.out.println(byeCommand);
        System.out.println("___________________________________________________\n");
    }

    /**
     * Displays the task list.
     */
    public void displayTasks() {
        System.out.println("___________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i).toString());
        }
        System.out.println("___________________________________________________\n");
    }

    /**
     * Notifies about the newly added task.
     *
     * @param task The new task added.
     */
    public void showAddedTask(Task task) {
        String output = "Added: " + task.toString();
        System.out.println("___________________________________________________");
        System.out.println(output);
        System.out.println("Now you have " + this.taskList.size() + " task"
                + ((this.taskList.size() <= 1) ? "" : "s") + " in the list");
        System.out.println("___________________________________________________\n");
    }

    /**
     * Notifies about the newly deleted task.
     *
     * @param toBeDeleted The task to be deleted.
     */
    public void showDeletedTask(Task toBeDeleted) {
        System.out.println("___________________________________________________");
        System.out.println(" Noted. I've removed this task:");
        System.out.println(toBeDeleted);
        System.out.println("Now you have " + this.taskList.size()
                + " task" + ((this.taskList.size() <= 1) ? "" : "s") + " in the list");
        System.out.println("___________________________________________________\n");
    }

    /**
     * Notifies about the task being marked as done.
     *
     * @param markedTask The task marked as done.
     */
    public void showMarkedAsDone(Task markedTask) {
        System.out.println("___________________________________________________");
        System.out.println(" Nice! I've marked this task as done:\n" + markedTask);
        System.out.println("___________________________________________________\n");
    }

    /**
     * Show a list of tasks found.
     *
     * @param taskList A list of tasks.
     */
    public void showFoundTask(List<Task> taskList) {
        if (taskList.isEmpty()) {
            System.out.println("___________________________________________________");
            System.out.println("OOPS, I couldn't find anything in your task list.");
            System.out.println("___________________________________________________\n");
        } else {
            System.out.println("___________________________________________________");
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + ". " + taskList.get(i).toString());
            }
            System.out.println("___________________________________________________\n");
        }
    }
}
