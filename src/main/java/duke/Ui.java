package duke;

import duke.tasks.Task;

/**
 * This class represents a User Interface.
 * It deals with user interactions like reading user input and displaying messages to the user.
 */
public class Ui {
    /**
     * Constructs a Ui object.
     */
    public Ui() {
    }

    /**
     * Returns loading error message when tasks cannot be loaded from Storage.
     */
    public String showLoadingError() {
        return "There was a problem loading saved tasks.";
    }

    /**
     * Returns message to show when Duke chatbot is first started.
     */
    public String showWelcome() {
        return "Hello...\nWhat do you want?\n";
    }

    /**
     * Returns message to show when Duke chatbot is exited.
     */
    public String showBye() {
        return "Whatever...";
    }

    /**
     * Returns message to show when DukeException is thrown.
     *
     * @param message Error message to be shown.
     */
    public String showError(String message) {
        return "Error. " + message;
    }

    /**
     * Returns message to show when task is marked as done.
     *
     * @param task Task that is marked as done.
     */
    public String showMarkDone(Task task) {
        return "I've marked this task as done:\n\t" + task;
    }

    /**
     * Returns message to show when task is deleted.
     *
     * @param task Task that was deleted.
     * @param tasks Updated TaskList that does not contain deleted task.
     */
    public String showDelete(Task task, TaskList tasks) {
        return "Noted. I've removed this task:\n\t" + task + "\n" + printTasksCount(tasks);
    }

    /**
     * Returns message showing number of tasks in TaskList.
     *
     * @param tasks TaskList of tasks whose number of tasks is to be displayed.
     */
    String printTasksCount(TaskList tasks) {
        return "Now you have " + tasks.getLength() + " tasks in the list.";
    }

    /**
     * Returns message to show when a task is added to a TaskList.
     *
     * @param tasks Updated TaskList of tasks containing added task.
     * @param task Task that was added to the TaskList.
     */
    public String showAddTask(TaskList tasks, Task task) {
        return "Got it. I've added this task:\n\t" + task + "\n" + printTasksCount(tasks);
    }

    /**
     * Returns message showing edited task.
     *
     * @param taskIndex Index of Task that was edited.
     * @param task Edited Task.
     * @return message showing edited task.
     */
    public String showEditedTask(int taskIndex, Task task) {
        return "Task " + (taskIndex + 1) + " has been successfully edited to:\n\t" + task;
    }
}
