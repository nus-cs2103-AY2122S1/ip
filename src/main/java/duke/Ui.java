package duke;

import duke.tasks.Task;

/**
 * Class for the ui responsible for interactions with the user
 *
 */
public class Ui {
    /**
     * Constructor that initializes ui
     *
     */
    public Ui(){}

    /**
     * Returns the start message
     *
     * @return Duke's String response
     */
    public String startMessage() {
        return ("It's wednesday my dudes\n"
                + "\nAHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH\n");
    }

    /**
     * Returns the exit message
     *
     * @return Duke's String response
     */
    public String byeMessage() {
        return ("bye");
    }

    /**
     * Returns message stating that task has been added
     *
     * @param taskList The tasklist that the task has been added to
     * @param task The task added
     * @return Duke's String response
     */
    public String addedMessage(TaskList taskList, Task task) {
        return ("Got it. I've added this task:\n"
                + "\t"
                + task.toString()
                + "\n"
                + "Now you have "
                + taskList.size()
                + " tasks in your list\n");
    }

    /**
     * Returns message stating that task has been deleted
     *
     * @param taskList The tasklist that the task has been deleted from
     * @param task The task deleted
     * @return Duke's String response
     */
    public String deleteMessage(TaskList taskList, Task task) {
        return ("Noted. I've removed this task:\n"
                + "\t"
                + task.toString()
                + "\n"
                + "Now you have "
                + taskList.size()
                + " tasks in your list\n");
    }

    /**
     * Returns message stating that task has been set as done
     *
     * @param task The task set as done
     * @return Duke's String response
     */
    public String doneMessage(Task task) {
        return ("Nice! I've marked this task as done: \n"
                + "\t"
                + task.toString()
                + "\n");
    }

    /**
     * Returns all tasks
     *
     * @param taskList The list of tasks to be printed
     * @return Duke's String response
     */
    public String listTasks(TaskList taskList) {
        return (taskList.toString());
    }

    /**
     * Returns the list of tasks matching input string
     *
     * @param taskList List of tasks matching input string
     * @return Duke's String response
     */
    public String listFoundTasks(TaskList taskList) {
        return ("Here are your matching entries!\n"
                + taskList.toString());
    }

    /**
     * Returns file load error message
     *
     * @return Duke's String response
     */
    public String loadErrorMessage() {
        return ("File could not be read.\n");
    }

    /**
     * Returns error message
     *
     * @param error Error whose message needs to be printed
     * @return Duke's String response
     */
    public String errorMessage(DukeException error) {
        return (error.getMessage());
    }
}
