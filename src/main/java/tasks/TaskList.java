package tasks;

import duke.Storage;
import duke.Ui;

import java.util.ArrayList;

/**
 * A class that abstracts a list of tasks set by the user. It also contains methods
 * that pertain to updating the list of tasks.
 */
public class TaskList {

    /** An arraylist that contains the tasks set by the user. */
    private final ArrayList<Task> tasks = new ArrayList<>();

    /** The number of uncompleted tasks in the task list */
    private int numOfUncompletedTasks = 0;

    /** The total number of tasks in the task list. */
    private int totalNumOfTasks = 0;

    /**
     * Adds a task to the task list and return a message to tell the user that the task has been added.
     *
     * @param task The task to be added to the task list.
     * @return The message telling the user that the task has been added.
     */
    public String addTask(Task task) {
        assert task != null : "Attempting to add a null task";
        this.tasks.add(task);
        this.numOfUncompletedTasks++;
        totalNumOfTasks++;
        String message = "Got it! This task has been added:\n" + task + "\n" + this.getTaskListStatus();
        this.saveTaskList();
        return message;
    }

    /**
     * Adds a previously saved task to the task list. This method should only be used when loading a task from
     * memory. No message is shown to the user when a task is added. Do not use this method to add a newly
     * inputted task from the user to the taskList.
     *
     * @param task The previously saved task to add to the taskList.
     */
    public void addSavedTaskFromStorage(Task task) {
        assert task != null : "Error while loading tasks from storage";
        this.tasks.add(task);
        if (!task.isDone()) {
            this.numOfUncompletedTasks++;
        }
        this.totalNumOfTasks++;
    }

    /**
     * Removes a task in the task list based on the index given.
     *
     * @param index The index of the task list. Note that the index provided starts from 1. So the
     *              index 1 represents the first task in the taskList ArrayList.
     * @param removedTasks An arrayList containing the removed tasks. Needed so that the
     *                     undo functionality can work well.
     * @return The message that should be shown to the user after removing a task.
     */
    public String removeTask(int index, ArrayList<Task> removedTasks) {
        if (index > this.tasks.size() || index <= 0) {
            removedTasks.add(null);
            return "A task could not be removed because it does not exist.\n";
        }

        Task task = this.tasks.get(index - 1);
        assert task != null : "Attempting to remove a null task";
        this.tasks.remove(index - 1);
        removedTasks.add(task);

        if (!task.isDone()) {
            numOfUncompletedTasks--;
        }

        totalNumOfTasks--;
        String message = "Understood. I've removed this task:\n" + task + "\n";
        this.saveTaskList();
        return message;
    }

    /**
     * Marks a task in the task list as completed based on the index given.
     *
     * @param index The index of the task list. Note that the index provided starts from 1.
     *              So the index 1 represents the first task in the taskList ArrayList.
     * @param doneTasks A arrayList to store thr index of the task that was mark as done. Needed
     *                  for command undo functionality.
     * @return The message that should be shown to the user after a task has been marked as done.
     */
    public String markTaskAsDone(int index, ArrayList<Integer> doneTasks) {
        if (index > this.tasks.size() || index <= 0) {
            return "Index " + index + " does not exist.\n";
        }
        if (this.tasks.get(index - 1).isDone()) {
            return "This task at index " + index + " has already been completed.\n";
        }

        Task task = this.tasks.get(index - 1);
        assert task != null : "Attempting to edit a null task.";
        task.markAsDone();
        doneTasks.add(index);
        this.numOfUncompletedTasks--;

        String message = "congratulations! This task has been completed:\n"
                + task + "\n";
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

        if (this.tasks.isEmpty()) {
            message.append("There are no tasks in your task list.\n");
            message.append(Ui.DASHES);
            return message.toString();
        }

        message.append("Here are the tasks in your list:\n");

        for (int i = 0; i < this.tasks.size(); i++) {
            message.append(i + 1).append(". ").append(this.tasks.get(i)).append("\n");
        }

        message.append(Ui.DASHES);
        return message.toString();
    }

    /**
     * Returns the tasks in the taskList that has a name that matches the
     * search keyword.
     *
     * @param searchKeyword A search keyword that is provided by the user.
     * @return An arrayList containing the tasks that contains the search keyword.
     */
    public ArrayList<Task> findTask(String searchKeyword) {
        ArrayList<Task> searchList = new ArrayList<>();
        assert searchKeyword != null : "Search term is null";
        for (Task task : this.tasks) {
            if (task.getTaskName().toLowerCase().contains(searchKeyword.toLowerCase())) {
                searchList.add(task);
            }
        }
        return searchList;
    }

    /**
     * Adds a task to the tasks array list at the specified index.
     *
     * @param task The task to be added.
     * @param index The index to add the task in the arrayList. The index is 1-based.
     */
    public void insertTaskAt(Task task, int index) {
        this.tasks.add(index - 1, task);
        if (task.isDone()) {
            this.numOfUncompletedTasks++;
        }
        this.totalNumOfTasks++;
    }

    /**
     * Saves the current task list to local storage in a file called taskList.txt.
     */
    private void saveTaskList() {
        if (!Storage.saveTaskList(this.tasks)) {
            System.out.println("Oops!! An error occurred while trying to save your new task.");
        }
    }

    /**
     * Reverts the task at the given index to undone. This is used in conjunction with the undo
     * command.
     *
     * @param index The index of the task to be marked as undone in the taskList. The index is
     *              1-based.
     */
    public void revertTaskToUndone(int index) {
        this.tasks.get(index - 1).revertToUndone();
        this.numOfUncompletedTasks--;
    }

    /**
     * Returns a String describing the total number of tasks and uncompleted tasks in the
     * taskList.
     *
     * @return The String describing the taskList status.
     */
    public String getTaskListStatus() {
        return "You currently have " + this.totalNumOfTasks + " tasks in your list with "
                + this.numOfUncompletedTasks + " uncompleted tasks remaining.\n";
    }

    public int getTotalNumOfTasks() {
        return this.totalNumOfTasks;
    }
}
