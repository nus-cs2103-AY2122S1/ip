package duke;

public class Ui {
    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Duke.");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }

    public static void divider() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Add task to taskList
     *t
     * @param taskList a list of tasks
     * @param task the task want to add to the list
     */
    public static void addTask(TaskList taskList, Task task) {
        System.out.println("    Got it. I've added this task:");
        System.out.println("     " + task.toString());
        System.out.println("    Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Handle bye category input
     */
    public static void bye(){
        System.out.println("    Bye. Hope to see you again soon!");
    }

    /**
     * Handle list category input
     * @param taskList a list of tasks
     */
    public static void list(TaskList taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println("    " + (i + 1) + ". " + task.toString());
        }
    }

    /**
     * Handle done category input
     * @param task the task that was completed
     */
    public static void done(Task task) {
        System.out.println("    Nice! I've marked this task as done: ");
        System.out.println("    " + task.toString());
    }

    /**
     * Delete a task from the list
     * @param taskList a list of tasks
     * @param task the task that want to be deleted from the list
     */
    public static void delete(TaskList taskList, Task task) {
        System.out.println("    Got it. I've removed this task:");
        System.out.println("     " + task.toString());
        System.out.println("    Now you have " + taskList.size() + " tasks in the list.");
    }
}
