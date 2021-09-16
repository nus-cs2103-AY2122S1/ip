package duke;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Encapsulates a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Creates the list.
     *
     * @param prevTasks the list of saved tasks from save file if any.
     */
    public TaskList(ArrayList<Task> prevTasks) {
        this.list = prevTasks;
    }

    /**
     * Add a task to this task list.
     *
     * @param newTask the task to be added.
     */
    public void add(Task newTask) {
        list.add(newTask);
        Collections.sort(list);
    }

    public Task delete(int index) {
        return list.remove(index);
    }

    public void forEach(Consumer<? super Task> action) {
        list.forEach(action);
    }

    /**
     * Marks the task at a specific index as complete.
     *
     * @param index the position of the task to be marked as complete.
     * @return the completed task.
     */
    public Task completeTask(int index) {
        list.get(index).complete();
        return list.get(index);
    }

    /**
     * Provides the number of tasks on this list.
     *
     * @return size of the list.
     */
    public int size() {
        return list.size();
    }

    public Task get(int index) {
        return list.get(index);
    }

    /**
     * Gets all tasks that contains the given string.
     *
     * @param query the string to be matched.
     * @return ArrayList of tasks that matches the query.
     */
    public ArrayList<Task> findMatches(String query) {
        List<Task> tasks = this.list.stream()
                .filter(task -> task.description.contains(query))
                .collect(Collectors.toList());

        return new ArrayList<>(tasks);
    }
}
