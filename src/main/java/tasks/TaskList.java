package tasks;

import duke.Storage;

import java.util.ArrayList;

/**
 * A class that abstracts a list of tasks set by the user.
 */
public class TaskList {

    /** An arraylist that contains the tasks set by the user. */
    private final ArrayList<Task> taskArrayList = new ArrayList<>();
    /** The number of uncompleted tasks in the task list */
    private int uncompletedTasks = 0;
    /** The total number of tasks in the task list. */
    private int totalTasks = 0;

    public TaskList() {}

    /**
     * Adds a task to the task list. Prints a message to tell the user that the task has been added.
     *
     * @param task The task to be added to the task list.
     */
    public void addTask(Task task) {
        this.taskArrayList.add(task);
        this.uncompletedTasks++;
        totalTasks++;
        System.out.println("Got it! This task has been added:");
        System.out.println(task);
        this.printTaskListStatus();
        this.saveTaskList();
    }

    /**
     * Adds a previously saved task to the task list. This method should only be used when loading a task from
     * memory. No message is shown to the user when a task is added. Do not use this method to add a newly
     * inputted task from the user to the taskList.
     *
     * @param task The previously saved task to add to the taskList.
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
     * @param index he index of the task list. Note that the index provided starts from 1. So the index 1 represents
     *              the first task in the taskList ArrayList.
     */
    public void removeTask(int index) {
        if (index > this.taskArrayList.size() || index <= 0) {
            System.out.println("This entry does not exist.\n");
            return;
        }
        System.out.println("Understood. I've removed this task:");
        Task task = this.taskArrayList.get(index - 1);
        this.taskArrayList.remove(index - 1);
        System.out.println(task);
        if (!task.isDone()) {
            uncompletedTasks--;
        }
        totalTasks--;
        this.printTaskListStatus();
        this.saveTaskList();
    }

    /**
     * Marks a task in the task list as completed based on the index given.
     * @param index The index of the task list. Note that the index provided starts from 1. So the index 1 represents
     *              the first task in the taskList ArrayList.
     */
    public void markTaskAsCompleted(int index) {
        if (index > this.taskArrayList.size() || index <= 0) {
            System.out.println("This entry does not exist.\n");
            return;
        }
        if (this.taskArrayList.get(index - 1).isDone()) {
            System.out.println("This task has already been completed.\n");
            return;
        }
        System.out.println("congratulations! This task has been completed:");
        this.taskArrayList.get(index - 1).setAsFinished();
        this.uncompletedTasks--;
        System.out.println(this.taskArrayList.get(index - 1));
        this.printTaskListStatus();
        this.saveTaskList();
    }

    /**
     * Prints the task list for the user to view. If there are no tasks in the taskList,
     * print message indicating that the taskList is empty.
     */
    public void listHistory() {
        System.out.println("-----------------------------------------------------------");
        if (this.taskArrayList.isEmpty()) {
            System.out.println("There are no tasks in your task list.");
            System.out.println("-----------------------------------------------------------");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.taskArrayList.size(); i++) {
            System.out.println((i + 1) + ". " + this.taskArrayList.get(i));
        }
        System.out.println("-----------------------------------------------------------");
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
     * Prints a message that tells the user the number of tasks in his list along with
     * the number of uncompleted tasks.
     */
    private void printTaskListStatus() {
        System.out.println("You currently have " + this.totalTasks + " tasks in your list with "
                + this.uncompletedTasks + " uncompleted tasks remaining.\n");
    }

}
