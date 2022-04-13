package duke;

public class Ui {
    /**
     * Print the greet expressions
     */
    public static String greet() {
        return "Hello, I'm Duke. What can I do for you?";
    }

    /**
     * Add task to taskList
     *
     * @param taskList a list of tasks
     * @param task the task want to add to the list
     */
    public static String addTask(TaskList taskList, Task task) {
        String line1 = "Got it. I've added this task:\n";
        String line2 =  task.toString() + "\n";
        String line3 = "Now you have " + taskList.size() + " tasks in the list.\n";
        return line1 + line2 + line3;
    }

    /**
     * Handle bye category input
     */
    public static String bye() {
        return  "Bye. Hope to see you again soon!\n";
    }

    /**
     * Handle list category input
     *
     * @param taskList a list of tasks
     */
    public static String list(TaskList taskList) {
        String str = "";
        String newStr;
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            newStr =  (i + 1) + ". " + task.toString() + "\n";
            str = str + newStr;
        }
        return str;
    }

    /**
     * Handle done category input
     * @param task the task that was completed
     */
    public static String done(Task task) {
        return "Nice! I've marked this task as done: \n" + task.toString();
    }

    /**
     * Delete a task from the list
     *
     * @param taskList a list of tasks
     * @param task the task that want to be deleted from the list
     */
    public static String delete(TaskList taskList, Task task) {
        String line1 = "Got it. I've removed this task:\n";
        String line2 = task.toString() + "\n";
        String line3 = "Now you have " + taskList.size() + " tasks in the list.\n";
        return line1 + line2 + line3;
    }

    public static String update(Task task) {
        String line1 = "Got it. I've updated this task:\n";
        String line2 = task.toString() + "\n";
        return line1 + line2;
    }

    public static String showError(String s) {
        return s;
    }
}
