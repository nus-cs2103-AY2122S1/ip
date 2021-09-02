package duke;

/**
 * Class that is in charge of interacting with user.
 */
public class Ui {

    /**
     * Says welcome to the user when they open duke bot.
     */
    public void greet() {
        System.out.println("Hello! I'm duke! What can I do for you?");
    }

    /**
     * Says GoodBye to the user when they leave.
     */
    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints out the error.
     *
     * @param errorMessage ErrorMessage throws by Duke.
     */
    public String showError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Prints all tasks in TaskList.
     *
     * @param tasks TaskList that is going to be printed.
     */
    public String listAllTasks(TaskList tasks) {
        String result = new String();
        for (int i = 0; i < tasks.size(); i++) {
            result += (i + 1) + "." + tasks.get(i).toString() + "\n";
        }
        return result;
    }

    /**
     * Notifies users that certain task has been marked as done.
     *
     * @param task The task that's going to be marked as done.
     */
    public String markAsDone(Task task) {
        return "Nice! I've marked this task as done:\n"
                + task.toString();
    }

    /**
     * Notifies users that certain tasks has been added.
     *
     * @param task         Task that has been added.
     * @param taskListSize Size of the updated taskList.
     */
    public String addTask(Task task, int taskListSize) {
        return "Got it. I've added this task:\n"
                + task.toString()
                + "\nNow you have "
                + taskListSize + " tasks in the list.";
    }

    /**
     * Notifies the user that certain task has been deleted.
     *
     * @param task         Task that has been deleted.
     * @param taskListSize Size of the updated taskList.
     */
    public String deleteTask(Task task, int taskListSize) {
        return "Successfully deleted task"
                + task.toString()
                + "\nNow you have" + taskListSize + " tasks in the list.";
    }
}
