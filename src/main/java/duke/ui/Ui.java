package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Class that deals with interactions with the user
 * such as reading user commands and displaying messages
 * to the user.
 */
public class Ui {

    public Ui() {
    }

    /**
     * Method that prints the welcome message
     */
    public String showWelcome() {
        return "Hello! I'm Duke\n What can I do for you?";
    }

    /**
     * Method that prints out the list of tasks
     * @param tasks the list of tasks
     */
    public String listTasks(TaskList tasks) {
        StringBuilder res = new StringBuilder("Here are the tasks in your list:\n");

        for (int i = 0; i < tasks.getSize(); i++) {
            Task currTask = tasks.get(i);
            res.append(i + 1).append(".").append(currTask).append("\n");
        }
        return res.toString();
    }

    /**
     * Method that lists the tasks that contains the word entered by user
     * @param tasks the list of tasks that contains the word entered by user
     */
    public String listFoundTasks(TaskList tasks) {
        StringBuilder res = new StringBuilder("Here are the matching tasks in your list:\n");

        for (int i = 0; i < tasks.getSize(); i++) {
            Task currTask = tasks.get(i);
            res.append(i + 1).append(".").append(currTask).append("\n");
        }
        return res.toString();
    }

    /**
     * Method that prints out the goodbye message
     */
    public String stopMethod() {
        return "Bye! Hope to see you again soon\n";
    }

    /**
     * Method that prints out the task marked as done and
     * total number of tasks when user marks it as done.
     * @param task the task to be marked as done
     */
    public String doneTask(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Method that prints out the task that aas added
     * @param task the task that is added
     */
    public String addTask(Task task, TaskList tasks) {
        return "Got it. I have added this task:\n"
                + task + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Method that prints out the task that the user deleted.
     * @param task the task that is deleted
     */
    public String deleteTask(Task task, TaskList tasks) {
        return "Got it. I have removed this task:\n"
                + task + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.";
    }
}
