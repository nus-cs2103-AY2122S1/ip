package iris.task;

import java.time.LocalDateTime;
import java.util.ArrayList;

import iris.exception.NoSuchTaskException;

/**
 * Encapsulates the representation of a list of task.
 * Handles all operations regarding tasks.
 *
 * @author Cheong Yee Ming
 * @version Iris Level-10
 */
public class TaskList {

    private ArrayList<Task> list;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Static method converting a task
     * from string form to Task form
     * using data from the local directory.
     *
     * @param str String representation of a task.
     * @return Task created from string.
     */
    public static Task stringToTask(String str) {
        String[] taskStr = str.split(" \\| ");
        boolean isDone = taskStr[1].equals("1");

        switch (taskStr[0]) {
        case "D":
            LocalDateTime by = LocalDateTime.parse(taskStr[3]);
            return new Deadline(taskStr[2], isDone, by);

        case "E":
            LocalDateTime at = LocalDateTime.parse(taskStr[3]);
            return new Event(taskStr[2], isDone, at);

        default:
            return new ToDo(taskStr[2], isDone);
        }
    }

    /**
     * Returns the task based on input task number.
     *
     * @param taskNumber The index of task to be returned.
     * @return Task at the index task number minus one.
     * @throws NoSuchTaskException when task does not exist.
     */
    public Task getTask(int taskNumber) throws NoSuchTaskException {
        try {
            return list.get(taskNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchTaskException();
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added to the list.
     */
    public void addTask(Task task) {
        assert task != null : "Task cannot be null.";
        list.add(task);
    }

    /**
     * Deletes a task from the list.
     * Throws an exception if task does not exist.
     *
     * @param taskNumber Index number of task to be deleted.
     * @return Task that is removed from the task list.
     * @throws NoSuchTaskException If task index number does not exist.
     */
    public Task deleteTask(int taskNumber) throws NoSuchTaskException {
        if (taskNumber <= 0 || taskNumber > list.size()) {
            throw new NoSuchTaskException();
        }
        return list.remove(taskNumber - 1);
    }

    /**
     * Marks a task as done in the list.
     * Throws an exception if task does not exist.
     *
     * @param taskNumber Index number of task to be marked as done.
     * @return Task that is marked as done in the list.
     * @throws NoSuchTaskException If task index number does not exist.
     */
    public Task markAsDone(int taskNumber) throws NoSuchTaskException {
        if (taskNumber <= 0 || taskNumber > list.size()) {
            throw new NoSuchTaskException();
        }
        Task task = list.get(taskNumber - 1);
        assert task != null : "Task cannot be null.";
        task.markDone();
        return task;
    }

    /**
     * Marks a task as undone in the list.
     * Throws an exception if task does not exist.
     *
     * @param taskNumber Index number of task to be marked as undone.
     * @return Task that is marked as undone in the list.
     * @throws NoSuchTaskException If task index number does not exist.
     */
    public Task markAsUndone(int taskNumber) throws NoSuchTaskException {
        if (taskNumber <= 0 || taskNumber > list.size()) {
            throw new NoSuchTaskException();
        }
        Task task = list.get(taskNumber - 1);
        assert task != null : "Task cannot be null.";
        task.markUndone();
        return task;
    }

    /**
     * Returns the size of the task list.
     * @return Size of task list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns the ArrayList of Tasks.
     * @return ArrayList of Tasks.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Loads the tasks saved in local directory
     * into newly initialised task list.
     *
     * @param listOfTasks List of task saved in local directory.
     */
    public void loadFromStorage(ArrayList<Task> listOfTasks) {
        list = listOfTasks;
    }

    /**
     * Returns an arraylist of tasks containing the keyword.
     *
     * @param keyword The keyword to look for in the description.
     * @return ArrayList of tasks containing the keyword in its description.
     */
    public ArrayList<Task> findRelatedTask(String keyword) {
        ArrayList<Task> relatedTaskList = new ArrayList<>();
        list.stream().filter(task -> task.containsKeyword(keyword)).forEach(relatedTaskList::add);
        return relatedTaskList;
    }
}
