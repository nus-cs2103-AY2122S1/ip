package data;

import task.Task;

import java.util.ArrayList;

/**
 * Class that deals with updating the ArrayList of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a new task into the ArrayList.
     *
     * @param newTask The task to be added
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Deletes a task from the ArrayList.
     *
     * @param oldTaskIndex The task to be deleted
     */
    public void deleteTask(int oldTaskIndex) {
        tasks.remove(oldTaskIndex);
    }

    /**
     * Marks a task in the ArrayList as done.
     *
     * @param index Index of the task
     */
    public void markTaskAsDone(int index) {
        tasks.get(index).markAsDone();
    }

    /**
     * Gets the size of the ArrayList.
     *
     * @return The length of the ArrayList
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Gets the Task at the specified index.
     *
     * @param index Index of the Task in the ArrayList
     * @return Task at the specified index
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Filters through the TaskList and returns a new TaskList with tasks containing the keyword.
     *
     * @param keyword Keyword to be searched by
     * @return A new TaskList of tasks that contain the keyword.
     */
    public TaskList searchByKeyword(String keyword) {
        TaskList filteredTaskList = new TaskList();
        for (Task task : this.tasks) {
            if (task.toString().contains(keyword)) {
                filteredTaskList.addTask(task);
            }
        }
        return filteredTaskList;
    }
}
