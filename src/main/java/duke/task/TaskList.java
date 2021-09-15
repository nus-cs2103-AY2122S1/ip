package duke.task;

import java.util.ArrayList;
import java.util.Arrays;

import duke.exception.DukeDuplicateTaskException;

/**
 * Represents a list for all tasks, akin to array lists.
 */
public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();

    /**
     * Constructor for TaskList.
     *
     * @param tasks varargs of tasks to be added to the list
     */
    public TaskList(Task... tasks) {
        this.list.addAll(Arrays.asList(tasks));
    }


    /**
     * Add a task to the list.
     *
     * @param task the task to be added
     */
    public void add(Task task) throws DukeDuplicateTaskException {
        for (Task t : this.list) {
            if (task.equals(t)) {
                throw new DukeDuplicateTaskException();
            }
        }
        this.list.add(task);
    }


    /**
     * Delete a task from the list.
     *
     * @param taskNum the index of the task to be deleted
     */
    public void delete(int taskNum) {
        this.list.remove(taskNum - 1);
    }


    /**
     * Obtain the number of items in the list.
     *
     * @return the length of the list of task
     */
    public int getLength() {
        return this.list.size();
    }


    /**
     * Obtain the task in the list at specific index.
     *
     * @param taskNum the index of the task to be found out
     * @return the task at that index
     */
    public Task get(int taskNum) {
        return this.list.get(taskNum - 1);
    }


    /**
     * Generate a task list containing tasks that contains the keyword
     *
     * @param keyword the keyword to be searched on
     * @return the task list containing all filtered tasks
     */
    public TaskList filter(String keyword) {
        return new TaskList(this.list.stream()
                .filter(t -> t.description.contains(keyword))
                .toArray(Task[]::new)
        );
    }

    /**
     * Find out whether the list of task is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.list.size() == 0;
    }
}
