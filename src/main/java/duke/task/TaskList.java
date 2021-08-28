package duke.task;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/** A class that represents a task list. */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Serializes the task list into string representation.
     *
     * @return Serialized string representation.
     */
    public String serialize() {
        String serializedData = "";

        for (Task task : this.taskList) {
            serializedData += task.serialize() + "\n";
        }

        return serializedData;
    }

    /**
     * Deserializes the string representation into a TaskList object.
     *
     * @param list A list of strings to be deserialized as individual task.
     * @return TaskList object.
     * @throws IllegalArgumentException If string is in invalid format.
     * @throws DateTimeParseException If date is in invalid format.
     */
    public static TaskList deserialize(List<String> list) throws IllegalArgumentException, DateTimeParseException {
        TaskList taskList = new TaskList();

        for (String data : list) {
            taskList.addTask(Task.deserialize(data));
        }

        return taskList;
    }

    /**
     * Adds task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes task at the specified index of the list.
     *
     * @param index The index of task to be deleted.
     */
    public void deleteTask(int index) {
        this.taskList.remove(index);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of task to be returned.
     * @return The task.
     */
    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * Returns the length of the list.
     *
     * @return Length of the list.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Marks task at the specified index to done.
     *
     * @param index The index of task to be marked.
     */
    public void markDone(int index) {
        this.taskList.get(index).markAsDone();
    }

    /**
     * Marks task at the specified index to not done.
     *
     * @param index The index of task to be marked.
     */
    public void markNotDone(int index) {
        this.taskList.get(index).markAsNotDone();
    }

    @Override
    public String toString() {
        String s = "";

        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = taskList.get(i);

            s += (i + 1) + ". " + task + "\n";
        }

        return s;
    }
}
