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
     * Returns help message
     *
     * @return Duke's help message
     */
    public String helpMessage() {
        return ("How to use Duke:\n\n"
            + "1. TODO: Enter todo and your task description after the todo\n"
            + "\t(eg: todo read book)\n"
            + "2. DEADLINE: Enter deadline, your task description after, and the date of the deadline after /by command"
            + "\n\t(eg: deadline return book /by 12-01-2021 12:00)\n"
            + "3. EVENT: Enter event, your task description after, and the date of the event after /at command\n"
            + "\t(eg: event attend team meeting /at 12-01-2021 12:00)\n"
            + "4. LIST: Enter list to list out all tasks\n"
            + "5. DONE: Enter done and the task number after to mark a task as done\n"
            + "6. FIND: Enter find and a specific key word to find tasks that match the keyword\n"
            + "7. DELETE: Enter delete and a the task number to delete task from the list\n"
            + "\nNOTE: Dates should be entered in dd-MM-yyyy HH:mm format\n"
            + "\nIt's wednesday my dudes\nAHHHHHHHHHHHHHHHHHHHH\n");
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
