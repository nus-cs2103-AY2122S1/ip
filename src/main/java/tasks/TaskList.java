package tasks;

import duke.Storage;
import duke.Ui;

import java.util.ArrayList;
import java.util.Locale;

/**
 * A class that abstracts a list of tasks set by the user. It also contains methods
 * that pertain to updating the list of tasks.
 */
public class TaskList {

    /** An arraylist that contains the tasks set by the user. */
    private final ArrayList<Task> taskArrayList = new ArrayList<>();
    /** The number of uncompleted tasks in the task list */
    private int uncompletedTasks = 0;
    /** The total number of tasks in the task list. */
    private int totalTasks = 0;

    /**
     * Adds a task to the task list. Prints a message to tell the user that the task has been added.
     *
     * @param task The task to be added to the task list.
     */
    public String addTask(Task task) {
        String message;
        this.taskArrayList.add(task);
        this.uncompletedTasks++;
        totalTasks++;
        message = "Got it! This task has been added:\n" + task + "\n" + this.getTaskListStatus();
        this.saveTaskList();
        return message;
    }

    /**
     * Adds a previously saved task to the task list. This method should only be used when loading a task from
     * memory. No message is shown to the user when a task is added. Do not use this method to add a newly
     * inputted task from the user to the taskList.
     *
     * @param task The previously saved task to add to the taskList.
     * @returns The message that should be shown to the user after saving a task.
     */
    public void addSavedTask(Task task) {
        this.taskArrayList.add(task);
        if (!task.isDone()) {
            this.uncompletedTasks++;
        }
        this.totalTasks++;
    }

    /**
     * Removes a task in the task list based on the index given.
     *
     * @param index The index of the task list. Note that the index provided starts from 1. So the
     *              index 1 represents the first task in the taskList ArrayList.
     * @return The message that should be shown to the user after removing a task.
     */
    public String removeTask(int index) {
        String message;
        if (index > this.taskArrayList.size() || index <= 0) {
            message = "A task could not be removed because it does not exist.\n";
            return message;
        }
        Task task = this.taskArrayList.get(index - 1);
        this.taskArrayList.remove(index - 1);
        if (!task.isDone()) {
            uncompletedTasks--;
        }
        totalTasks--;
        message = "Understood. I've removed this task:\n" + task + "\n";
        this.saveTaskList();
        return message;
    }

    /**
     * Marks a task in the task list as completed based on the index given.
     *
     * @param index The index of the task list. Note that the index provided starts from 1.
     *              So the index 1 represents the first task in the taskList ArrayList.
     * @return The message that should be shown to the user after a task has been marked as done.
     */
    public String markTaskAsCompleted(int index) {
        if (index > this.taskArrayList.size() || index <= 0) {
            return "Index " + index + " does not exist.\n";
        }
        if (this.taskArrayList.get(index - 1).isDone()) {
            return "This task at index " + index + " has already been completed.\n";
        }
        String message;
        this.taskArrayList.get(index - 1).setAsFinished();
        this.uncompletedTasks--;
        message = "congratulations! This task has been completed:\n"
                + this.taskArrayList.get(index - 1) + "\n";
        this.saveTaskList();
        return message;
    }

    /**
     * Gets the task list for the user to view. If there are no tasks in the taskList,
     * return a message indicating that the taskList is empty.
     *
     * @return The contents of the taskList or a message indicating that it is empty.
     */
    public String listHistory() {
        StringBuilder message = new StringBuilder(Ui.DASHES);
        if (this.taskArrayList.isEmpty()) {
            message.append("There are no tasks in your task list.\n");
            message.append(Ui.DASHES);
            return message.toString();
        }
        message.append("Here are the tasks in your list:\n");
        for (int i = 0; i < this.taskArrayList.size(); i++) {
            message.append(i + 1).append(". ").append(this.taskArrayList.get(i)).append("\n");
        }
        message.append(Ui.DASHES);
        return message.toString();
    }

    public ArrayList<Task> findTask(String searchKeyword) {
        ArrayList<Task> searchList = new ArrayList<>();
        for (Task task : this.taskArrayList) {
            if (task.getTaskName().toLowerCase().contains(searchKeyword.toLowerCase())) {
                searchList.add(task);
            }
        }
        return searchList;
    }

    /**
     * Saves the current task list to local storage in a file called taskList.txt.
     */
    private void saveTaskList() {
        if (!Storage.saveTaskList(this.taskArrayList)) {
            System.out.println("Oops!! An error occurred while trying to save your new task.");
        }
    }

    /**
     * Returns a String describing the total number of tasks and uncompleted tasks in the
     * taskList.
     *
     * @return The String describing the taskList status.
     */
    public String getTaskListStatus() {
        return "You currently have " + this.totalTasks + " tasks in your list with "
                + this.uncompletedTasks + " uncompleted tasks remaining.\n";
    }

}
