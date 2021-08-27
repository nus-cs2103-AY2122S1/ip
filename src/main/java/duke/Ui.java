package duke;

import duke.task.Task;

/**
 * Deals with interactions with the user
 */
public class Ui {

    /**
     * Greets user when programme is run
     */
    public void welcome() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    /**
     * Bids user goodbye when programme is exited
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Shows error message for issue with loading of data
     */
    public void showLoadingError() {
        System.out.println("idk man im sorry");
    }

    /**
     * Shows user task has been added to list
     *
     * @param task task to be added
     * @param size current number of tasks
     */
    public void showTaskAdded(Task task, int size) {
        String t = size == 1 ? "task" : "tasks";
        System.out.printf("Got it. I've added this task:\n%s\nNow you have %d %s in the list.\n", task, size, t);
    }

    /**
     * Shows user task has been completed
     *
     * @param task completed task
     */
    public void showTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Shows user task has been deleted
     *
     * @param task task to be deleted
     * @param size current number of tasks
     */
    public void showTaskDeleted(Task task, int size) {
        String t = size == 1 ? "task" : "tasks";
        System.out.printf("Noted.I've removed this task:\n%s\nNow you have %d %s in the list\n", task, size, t);
    }


    /**
     * Lists required tasks in the list
     *
     * @param list  list of tasks
     * @param cmd determine which message to be shown
     */
    public void showTaskList(TaskList list, String cmd) {
        String msg = cmd.equals("list")
                ? "Here are the tasks in your list:"
                : "Here are the matching tasks in your list:";
        System.out.println(msg);
        for (int i = 0; i < list.getSize(); i++) {
            Task s = list.getTask(i);
            System.out.printf("%d.%s%n", i + 1, s);
        }
    }
}
