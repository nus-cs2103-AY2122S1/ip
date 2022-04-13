package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class that handles the responses given back to the user.
 */
public class Ui {

    /**
     * Gets exit message.
     *
     * @return the exit message
     */
    public String getExitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Gets loading error message.
     *
     * @return the loading error message
     */
    public String getLoadingErrorMessage() {
        return "There is a problem loading saved data.";
    }

    /**
     * Gets add task message.
     *
     * @return the add message
     */
    public String getAddTaskMessage(Task task, int numOfTasks) {
        assert numOfTasks >= 1;
        return "Got it. I've added this task:\n  "
                + task.toString() + "\nNow you have " + numOfTasks
                + " task(s) in the list.";
    }

    /**
     * Gets delete task message.
     *
     * @return the delete message
     */
    public String getDeleteTaskMessage(Task task, int numOfTasks) {
        assert numOfTasks >= 0;
        return "Noted. I've removed this task:\n  "
                + task.toString() + "\nNow you have " + numOfTasks
                + " task(s) in the list.";
    }

    /**
     * Gets list of tasks
     *
     * @return the list of tasks
     */
    public String getTaskList(TaskList tasks) {
        if (tasks.numOfTasks() == 0) {
            return "There is no task in your list";
        } else {
            return "Here are the tasks in your list:" + tasks.toString();
        }
    }

    /**
     * Gets tasks that match the input
     *
     * @return the matched tasks
     */
    public String getFoundTasks(TaskList tasks) {
        if (tasks.numOfTasks() == 0) {
            return "There is no matching task";
        } else {
            return "Here are the matching tasks in your list:" + tasks.toString();
        }
    }

    /**
     * Gets finished tasks
     *
     * @return the finished tasks
     */
    public String getTasksDone(Task task) {
        return "Nice! I've marked this task as done:\n  "
                + task.toString();
    }

    /**
     * Gets tasks to do after a specific referenced task.
     *
     * @param tasks the TaskList containing all existing tasks
     * @param refTask the referenced task
     * @return the tasks to do after the referenced task
     */
    public String getTasksAfterTask(TaskList tasks, Task refTask) {
        String listOfTasksToDoAfter = tasks.numOfTasks() == 0
                ? "\nYou have no task to begin"
                : "\nYou need to begin the following tasks:" + tasks.toString();
        return "After this task:\n  " + refTask.toString() + listOfTasksToDoAfter;
    }

    /**
     * Gets tasks to do after a specific referenced date and time.
     *
     * @param tasks the TaskList containing all existing tasks
     * @param refDateTime the referenced date and time
     * @return the tasks to do after the referenced date and time
     */
    public String getTasksAfterDateTime(TaskList tasks, LocalDateTime refDateTime) {
        String listOfTasksToDoAfter = tasks.numOfTasks() == 0
                ? "\nYou have no task to begin"
                : "\nYou need to begin the following tasks:" + tasks.toString();
        return "After:\n  " + refDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                + listOfTasksToDoAfter;
    }

    /**
     * Gets the response when setting a task after a task.
     *
     * @param taskAfter the Task to do after
     * @param taskBefore the task to do before
     * @return the response
     */
    public String getSetAfterTaskMessage(Task taskAfter, Task taskBefore) {
        return "Got it! I have set this task:\n  "
                + taskAfter.toString()
                + "\nAfter this task:\n  "
                + taskBefore.toString();
    }

    /**
     * Gets the response when setting a task after a date and time.
     *
     * @param task the Task to do after the date and time
     * @param refDateTime the referenced date and time
     * @return the response
     */
    public String getSetAfterDateTimeMessage(Task task, LocalDateTime refDateTime) {
        return "Got it! I have set this task:\n  "
                + task.toString()
                + "\nAfter "
                + refDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    /**
     * Gets error message
     *
     * @return the error message
     */
    public String getErrorMessage(String s) {
        return s;
    }
}
