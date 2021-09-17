package duke.task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import duke.storage.Storage;

public class TaskList {
    private static final String EMPTY_LIST_MESSAGE = "Hooray! You have no tasks currently.";
    private static final String CANNOT_FIND_TASK_MESSAGE = "Cannot find task with number %d.";
    private final Storage<Task> storage;
    private final List<Task> list;

    /**
     * Constructs a new TaskList.
     *
     * @param storage The storage to use for reading and storing of tasks.
     * @throws IOException If an IOException is thrown when loading the tasks.
     */
    public TaskList(Storage<Task> storage) throws IOException {
        this.storage = storage;
        this.list = this.storage.load();
    }

    /**
     * Adds the specified task to the task list.
     *
     * @param newTask The task to add.
     */
    public void add(Task newTask) {
        assert newTask != null;
        this.list.add(newTask);
        this.save();
    }

    public Task get(int taskIndex) throws InvalidTaskException {
        try {
            return this.list.get(taskIndex - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskException(String.format(CANNOT_FIND_TASK_MESSAGE, taskIndex));
        }
    }

    /**
     * Removes the specified task from the task list.
     *
     * @param taskIndex The index of the task to remove, starting from 1.
     * @return The removed task.
     * @throws InvalidTaskException If the provided index is invalid.
     */
    public Task remove(int taskIndex) throws InvalidTaskException {
        Task task = this.get(taskIndex);
        this.list.remove(task);
        this.save();
        return task;
    }

    /**
     * Marks a task as done.
     *
     * @param taskIndex The index of the task to mark as done.
     * @throws InvalidTaskException If the provided index is invalid.
     */
    public void markAsDone(int taskIndex) throws InvalidTaskException {
        this.get(taskIndex).markAsDone();
        this.save();
    }

    /**
     * Searches for a task matching the specified keyword.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks that match the keyword.
     */
    public List<Task> findByKeyword(String keyword) {
        List<Task> results = new ArrayList<>();
        for (Task task : this.list) {
            if (task.getDescription().contains(keyword)) {
                results.add(task);
            }
        }
        return results;
    }

    public int size() {
        return this.list.size();
    }

    private void save() {
        try {
            this.storage.save(this.list);
        } catch (IOException e) {
            throw new IllegalStateException("Error saving tasks to file", e);
        }
    }

    @Override
    public String toString() {
        if (this.size() == 0) {
            return EMPTY_LIST_MESSAGE;
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.list.size(); i++) {
            builder.append(String.format("%d. %s\n", i + 1, this.list.get(i).toString()));
        }
        return builder.toString();
    }
}
