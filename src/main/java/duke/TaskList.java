package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Stores and retrieves Tasks.
 *
 * @author Chang-CH
 */
public class TaskList {
    ArrayList<Task> tList;

    /**
     * Class constuctor.
     */
    public TaskList() {
        tList = new ArrayList<>();
    }


    /**
     * Adds a task to the task list.
     * @param task Task to be added.
     */
    public void add(Task task) {
        tList.add(task);
    }

    /**
     * String representation of the tasklist.
     *
     * @return String containing all tasks separated by a newline character.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : tList) {
            if (stringBuilder.length() != 0) {
                stringBuilder.append("\n");
            }
            String save = task.saveString();
            System.out.println(save);
            stringBuilder.append(save);
        }
        return stringBuilder.toString();
    }

    /**
     * Returns the size of the list.
     *
     * @return List size.
     */
    public int getSize() {
        return tList.size();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index Index of the task to be retrieved.
     * @return Task at the index.
     */
    public Task getIndex(int index) {
        return tList.get(index);
    }

    /**
     * Removes the task at the specified index.
     *
     * @param index Index of the task to be removed.
     */
    public void removeIndex(int index) {
        tList.remove(index);
    }
}
