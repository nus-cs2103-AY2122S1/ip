package saber;

import saber.task.Task;

import java.util.ArrayList;

/**
 * A class to represent the TaskList used to store tasks when Saber application is still running
 */
public class TaskList {
    protected ArrayList<Task> taskList;

    /**
     * A constructor to TaskList object which takes in an ArrayList of tasks
     * @param taskList the taskList (an ArrayList) that will be used to store the tasks
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * A function to add a task to the TaskList
     * @param task the task that will be added
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * A function to delete a task given its index
     * @param taskIndex the index of the task that will be deleted
     */
    public void delete(int taskIndex) {
        taskList.remove(taskIndex);
    }

    /**
     * A function to get the size of the TaskList
     * @return size of the tasklist
     */
    public int size() {
        return taskList.size();
    }

    /**
     * A function to get a task given its index
     * @param index the index of the task that will be returned
     * @return the Task specified
     */
    public Task get(int index) {
        return taskList.get(index);
    }
}
