package duke.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks
 */
public class TaskList {

    /** ArrayList of tasks */
    private ArrayList<Task> tasks;

    /**
     * TaskList constructor.
     *
     * @param taskList ArrayList of tasks to store in TaskList.
     */
    public TaskList(ArrayList<Task> taskList) {
        tasks = taskList;
    }

    /**
     * Returns an empty TaskList.
     *
     * @return Empty TaskList.
     */
    public static TaskList emptyTaskList() {
        return new TaskList(new ArrayList<Task>());
    }

    /**
     * Adds given task to list of tasks.
     *
     * @param t Task to add.
     * @return True if task is added, false otherwise.
     */
    public boolean addTask(Task t) {
        return tasks.add(t);
    }

    /**
     * Returns task at given index in list of tasks.
     *
     * @param index Index of task to get.
     * @return Task at given index in list of tasks.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns task at given index in list of tasks and removes it.
     *
     * @param index Index of task to retrieve and remove.
     * @return Task at given index in list of tasks.
     */
    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    /** Returns number of tasks in list */
    public int size() {
        return tasks.size();
    }

    @Override
    public String toString() {
        String listOfTasks = "\n";
        for (int i = 0; i < tasks.size(); i++) {
            listOfTasks = listOfTasks + String.format("\t%d. %s\n", i + 1, tasks.get(i));
        }

        System.out.println(listOfTasks);
        return listOfTasks;
    }
}
