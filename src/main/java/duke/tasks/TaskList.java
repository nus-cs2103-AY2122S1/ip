package duke.tasks;

import java.util.ArrayList;

/**
 * TaskList class used to represent the list of tasks in Duke.
 * Contains methods that
 * (i) adds a task.
 * (ii) removes a task.
 * (iii) gets the taskList.
 */
public class TaskList {
    private final ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Adds a task into the list of tasks.
     *
     * @param t Task object that is to be added.
     */
    public void add(Task t) {
        taskList.add(t);
        return;
    }

    /**
     * Removes a task from the list of tasks.
     *
     * @param num int object that represents index of the task in the taskList that is to be removed.
     */
    public void remove(int num) {
        taskList.remove(num - 1);
        return;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }
}
