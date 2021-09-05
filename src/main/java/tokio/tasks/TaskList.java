package tokio.tasks;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Contains the list of tasks and methods for the list.
 * Methods include adding tasks and deleting tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor used when task list needs to be created.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor used when task list already exists.
     *
     * @param tasks Task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds newTask into the current task list.
     *
     * @param newTask Task to be added.
     * @throws IOException If new task cannot be added.
     */
    public void addTask(Task newTask) throws IOException {
        tasks.add(newTask);
    }

    /**
     * Gets the task using the index specified.
     *
     * @param index Index of task in task list.
     * @return Task with specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Gets the size of a task list.
     *
     * @return Size of task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Removes task using the index specified.
     *
     * @param index Index of task in task list.
     */
    public void deleteTask(int index) {
        if (index < tasks.size()) {
            tasks.remove(index);
        }
    }

    /**
     * Marks task using the index specified as done.
     *
     * @param index Index of task in task list
     */
    public void doneTask(int index) {
        if (index < tasks.size()) {
            tasks.get(index).setDone();
        }
    }

    /**
     * Finds tasks from taskList using keyword.
     *
     * @param keyword User input to search for.
     * @return TaskList only containing items with specified keyword.
     */
    public TaskList findTask(String keyword) {
        ArrayList<Task> resultTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            if (currTask.toString().contains(keyword)) {
                resultTasks.add(currTask);
            }
        }
        return new TaskList(resultTasks);
    }
}
