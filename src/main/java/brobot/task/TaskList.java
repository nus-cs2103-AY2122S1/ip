package brobot.task;

import java.util.ArrayList;

import brobot.exception.NoSuchTaskException;

/**
 * Represents a task list.
 */
public class TaskList {
    private final ArrayList<Task> list;

    /**
     * Constructor.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds the specified Task to the list.
     *
     * @param task The Task.
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Retrieves the nth Task from the list.
     *
     * @param taskNumber The position of the task in the list.
     * @return The Task.
     */
    public Task getTask(int taskNumber) {
        assert(taskNumber > 0);
        int taskIndex = taskNumber - 1;
        return this.list.get(taskIndex);
    }

    /**
     * Returns the amount of tasks in the specified list.
     *
     * @return The amount of tasks.
     */
    public int getTaskAmount() {
        return list.size();
    }

    /**
     * Marks the nth Task as done.
     *
     * @param taskIndex The task position in the list.
     * @throws NoSuchTaskException
     */
    public void markDone(int taskIndex) throws NoSuchTaskException {
        if (taskIndex >= 0 && taskIndex < list.size()) {
            list.get(taskIndex).markComplete();
        } else {
            throw new NoSuchTaskException();
        }
    }

    /**
     * Deletes the nth task from the list.
     *
     * @param taskIndex The task position in the list.
     * @return The deleted task.
     * @throws NoSuchTaskException
     */
    public Task deleteTask(int taskIndex) throws NoSuchTaskException {
        if (taskIndex >= 0 && taskIndex < list.size()) {
            //Store the task getting deleted temporarily for printing
            Task temp = list.get(taskIndex);
            list.remove(taskIndex);
            return temp;
        } else {
            throw new NoSuchTaskException();
        }
    }

    /**
     * Returns a Task List with tasks matching the search word.
     *
     * @param searchWord The search word.
     * @return The task list.
     */
    public TaskList searchTask(String searchWord) {
        TaskList output = new TaskList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).contains(searchWord)) {
                output.addTask(list.get(i));
            }
        }
        return output;
    }

    /**
     * String representation of the task list.
     *
     * @return String representation of the task list.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            s.append("\n").append(i + 1).append(".").append(list.get(i));
        }
        return s.toString();
    }

    /**
     * String representation of the task list for brobot.storage.
     *
     * @return String representation of the task list(brobot.storage format).
     */
    public String toStorageString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            s.append(list.get(i).toStorageString()).append("\n");
        }
        return s.toString();
    }

}
