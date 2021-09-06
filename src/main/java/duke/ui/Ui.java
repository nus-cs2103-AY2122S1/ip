package duke.ui;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    /**
     * Constructor for a UI.
     */
    public Ui() {}

    /**
     * Displays UI when a task has been added.
     *
     * @param taskList The TaskList.
     * @param task The Task that has been added.
     * @return String representation of addUi.
     */
    public String displayAddUi(TaskList taskList, Task task) {
        return "Got it. I've added this task:\n" + "  " + task + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Displays UI when a task has been marked as done.
     *
     * @param task The Task that has been marked as done.
     * @return String representation of doneUi.
     */
    public String displayDoneUi(Task task) {
        if (task != null) {
            return "Nice! I've marked this task as done:\n  " + task;
        } else {
            return "This task has already been done.";
        }
    }

    /**
     * Displays UI when a task has been deleted.
     *
     * @param taskList The TaskList.
     * @param task The Task that has been deleted.
     * @return String representation of deleteUi.
     */
    public String displayDeleteUi(TaskList taskList, Task task) {
        assert task != null : "Task should not be null";

        return "Noted. I've removed this task:\n  " + task + "\nNow you have "
                + taskList.size() + " tasks in the list.";
    }

    /**
     * Displays UI when a command to display TaskList has been given.
     *
     * @param taskList The TaskList.
     * @return String representation of listUi.
     */
    public String displayListUi(TaskList taskList) {
        if (taskList.isEmpty()) {
            return "You do not have any outstanding task. Yay!";
        } else {
            return "Here are the tasks in your list:\n" + taskList;
        }
    }

    /**
     * Displays UI when a command to exit Duke has been given.
     *
     * @return String representation of exitUi.
     */
    public String displayExitUi() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays UI when an unknown command has been given.
     *
     * @return String representation of unknownUi.
     */
    public String displayUnknownUi() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    /**
     * Displays UI when a command to find a keyword has been given.
     *
     * @param relatedList The list of related tasks found in TaskList.
     * @return String representation of findUi.
     */
    public String displayFindUi(ArrayList<Task> relatedList) {
        if (relatedList.isEmpty()) {
            return "Unfortunately, there's no task that matches your keyword :-(";
        } else {
            String header = "Here are the matching tasks in your list:\n";

            String list = "";
            int size = relatedList.size();

            for (int i = 0; i < size - 1; i++) {
                list += String.format("%d.%s\n", i + 1, relatedList.get(i));
            }
            list += String.format("%d.%s", size, relatedList.get(size - 1));

            return header + list;
        }

    }

    /**
     * Displays UI when a `DukeException` is thrown.
     *
     * @param e The DukeException thrown.
     * @return String representation of the `DukeException`.
     */
    public String displayDukeExceptionMessage(DukeException e) {
        return e.getMessage();
    }

    /**
     * Greets the user.
     *
     * @return A simple greeting.
     */
    public String greetNewUser() {
        return "No previous data found.\nLet's start a new To-Do List!\n\nHello! I'm Duke\nWhat can I do for you?";
    }

    /**
     * Greets existing user.
     *
     * @return A greeting for existing user.
     */
    public String greetExistingUser(TaskList taskList) {
        return "Welcome back! :-)\nAs far as I can recall...\n" + displayListUi(taskList);
    }
}
