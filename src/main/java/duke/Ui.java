package duke;

import duke.task.Task;
import exception.DukeException;

/**
 * Deals with interaction with the user
 */
public class Ui {
    /**
     * Greets the user
     */

    public void greet() {
        System.out.println("Hello! I'm Duke!\nWhat can I do for you?\n");
    }

    /**
     * Says bye to the user
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again!");
    }

    /**
     * Shows the user the duke error message
     *
     * @param e the exception
     */
    public void showErrorMessage(DukeException e) {
        System.out.println(e.getMessage());
    }


    /**
     * Informs the user that there are no tasks in the list
     */

    public void showNoTaskMessage() {
        System.out.println("There are no tasks!");
    }


    /**
     * Shows the user the current task list
     *
     * @param taskList the list of the tasks
     */

    public void showTaskList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < taskList.size() + 1; i++) {
            System.out.printf("  %d.%s%n", i, taskList.get(i));
        }
    }

    /**
     * Informs the user that the task is deleted
     *
     * @param deletedTask the deleted task
     * @param taskListSize the number of tasks in list after deletion
     */
    public void showDeleteMessage(Task deletedTask, int taskListSize) {
        System.out.printf("Noted. I've removed this task:\n  %s\nNow you have %d task(s) in the list.%n",
                deletedTask, taskListSize);
    }

    /**
     * Informs the user that the task is added
     *
     * @param newTask the task to add to list
     * @param taskListSize the number of tasks in list after adding new task
     */
    public void showAddTaskMessage(Task newTask, int taskListSize) {
        System.out.printf("Got it. I've added this task:\n  %s\nNow you have %d task(s) in the list.%n",
                newTask, taskListSize);
    }

    /**
     * Informs the user that the task is marked as done
     *
     * @param doneTask the task after marking as done
     */
    public void showDoneMessage(Task doneTask) {
        System.out.println("Nice! I've marked this task as done:\n  " + doneTask);
    }
}
