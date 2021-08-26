package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Stores and retrieves Tasks.
 *
 * @author Chang-CH
 */
public class TaskList {

    /**
     * Class constuctor.
     */
    private final ArrayList<Task> tasks;
    public TaskList() {
        tasks = new ArrayList<>();
    }


    /**
     * Adds a task to the task list.
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * String representation of the tasklist.
     *
     * @return String containing all tasks separated by a newline character.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : tasks) {
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
        return tasks.size();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index Index of the task to be retrieved.
     * @return Task at the index.
     */
    public Task getIndex(int index) {
        return tasks.get(index);
    }

    /**
     * Removes the task at the specified index.
     *
     * @param index Index of the task to be removed.
     */
    public void removeIndex(int index) {
        tasks.remove(index);
    }

    /**
     * Finds all tasks with description matching the regex.
     * @param regex Regex to match to.
     * @return TaskList containing all matching tasks.
     */
    public TaskList findTasks(String regex) {
        TaskList result = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(regex)) {
                result.add(task);
            }
        }
        return result;
    }
}
