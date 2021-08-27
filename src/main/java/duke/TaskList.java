package duke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import duke.task.Task;

/**
 * Contains the task list.
 */
public class TaskList {
    private List<Task> tasks = new ArrayList<>();

    /**
     * Constructor of TaskList.
     *
     * @param tasks List of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructor of TaskList.
     * Set the task list to default empty list.
     *
     */
    public TaskList() {
    }

    /**
     * Get the task list contained.
     *
     * @return Task list.
     */
    public List<Task> getList() {
        return this.tasks;
    }

    /**
     * Add a task to the list.
     *
     * @param t Task to be added.
     */
    public void add(Task t) {
        tasks.add(t);
        System.out.println("Ok~ I've added the task:\n" + t.toString());
    }

    /**
     * Delete a task with index given.
     *
     * @param index Index of the task to be deleted
     */
    public void delete(int index) throws IndexOutOfBoundsException{
        Task deleted = tasks.get(index);
        tasks.remove(index);
        System.out.println("Ok~ I've deleted the task:\n" + deleted.toString());
    }

    /**
     * Display all the tasks in the list.
     */
    public void display() {
        System.out.println("Here are your tasks ~ OwO");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    /**
     * Get the specific task from the list.
     *
     * @param index Index of the task.
     * @return Task object with that index.
     */
    public Task get(int index) throws IndexOutOfBoundsException {
        return tasks.get(index);
    }

    public TaskList filterByKeyword(String keyword) {
        List<Task> filtered = new ArrayList<>();
        for (Task curr : tasks) {
            String des = curr.getDescription();
            String[] words = des.split("\\s");
            if (Arrays.asList(words).contains(keyword)) {
                filtered.add(curr);
            }
        }
        return new TaskList(filtered);
    }
}