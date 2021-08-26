package yoyo.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the size of the TaskList.
     *
     * @return size of the TaskList.
     */
    public int size() {
        return tasks.size();
    }


    /**
     * Adds new Task to TaskList
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns Task at specified index.
     *
     * @param index Index of Task to be obtained.
     * @return Task at specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }


    /**
     * Removes Task at specified index.
     *
     * @param index Index of Task to be obtained.
     * @return Task at specified index.
     */
    public void remove(int index) {
        tasks.remove(index);
    }

    @Override
    public boolean equals(Object o) {
        @SuppressWarnings("unchecked")
        TaskList otherTaskList = (TaskList) o;
        return this.tasks.equals(otherTaskList.tasks);
    }


}
