package duke.tasks;

import java.util.ArrayList;

/**
 * Class that represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Constructor to make a task list.
     *
     * @param list ArrayList of Tasks.
     */
    public TaskList(ArrayList<Task> list) {
        taskList = list;
    }

    /**
     * Find the number of Tasks.
     *
     * @return
     */
    public int length() {
        return taskList.size();
    }

    /**
     * Add another Task to the list.
     *
     * @param task A Task object.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Retrieve a Task from the TaskList given an index.
     *
     * @param index Position of Task needed in the list.
     * @return Task object of index given.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Remove Task from list.
     *
     * @param index Position of Task that has to be deleted.
     */
    public void deleteTask(int index) {
        taskList.remove(index);
    }

    /**
     * Return an ArrayList version of the task list.
     *
     * @return A TaskList.
     */
    public ArrayList<Task> toArrayList() {
        return taskList;
    }

    @Override
    public String toString() {
        StringBuilder op = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            op
                    .append(i + 1)
                    .append(". ")
                    .append(taskList.get(i).toString())
                    .append("\n");
        }
        return "Here are the tasks in your list:\n" + op;
    }
}
