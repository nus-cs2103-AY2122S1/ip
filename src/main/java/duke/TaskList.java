package duke;

import java.util.ArrayList;

import duke.task.Task;


/**
 * Class that modifies and keeps track of the ArrayList of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Class constructor. Initialises the TaskList with an existing
     * arraylist of tasks.
     *
     * @param taskList An existing arraylist of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    /**
     * Class constructor. Initialises a new empty arraylist.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Indicates if the arraylist is empty.
     *
     * @return boolean indicating if the arraylist is empty.
     */
    public boolean isEmpty() {
        return tasks.size() == 0;
    }

    /**
     * Returns the size of the arrayList.
     * @return int indicating the current size of the arraylist.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the Task object at the specified index in the arraylist.
     *
     * @param index The position of the Task object in the arraylist.
     * @return Task object.
     * @throws DukeException If the Task does not exist in the arraylist.
     */
    public Task getTask(int index) throws DukeException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid Task. Please try again.");
        }
    }

    /**
     * Adds the Task object to the arraylist.
     *
     * @param task The Task object to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the Task object at the specified index from the taskList.
     *
     * @param index The position of the Task object in the arraylist.
     * @throws DukeException If Task does not exist in the arraylist.
     */
    public void deleteTask(int index) throws DukeException {
        try {
            tasks.remove(index);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Invalid Task. Please try again.\n");
        }
    }

    /**
     * Marks the Task object at the specified index in the arraylist as done.
     *
     * @param index The position of the Task object in the arraylist.
     * @throws DukeException If Task does not exist in the arraylist.
     */
    public void markTask(int index) throws DukeException {
        try {
            Task task = tasks.get(index);
            task.markDone();
            assert(task.getStatusIcon().equals("X"));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Invalid Task. Please try again.\n");
        }
    }

    /**
     * Searches the arraylist for tasks that contain the filter string.
     *
     * @param filter The string to search for.
     * @return TaskList object that contains the arraylist of tasks that match the filter.
     */
    public TaskList findTasks(String filter) {
        ArrayList<Task> searchResult = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.toString().contains(filter)) {
                searchResult.add(task);
                assert(!searchResult.isEmpty());
            }
        }
        return new TaskList(searchResult);
    }

    /**
     * Checks if the taskList has a duplicate of the given task.
     * @param other The given task.
     * @return True if a duplicate exists, False if a duplicate does not exist.
     */
    public Boolean hasDuplicate(Task other) {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.equals(other)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the formatted string to write to the file.
     *
     * @return String to write to the file.
     */
    public String toSaveFormat() {
        String saveTasks = "";
        for (int i = 0; i < tasks.size(); i++) {
            String taskToWrite = tasks.get(i).toWriteFormat();
            saveTasks = saveTasks.concat(taskToWrite + "\n");
        }
        return saveTasks;
    }

    /**
     * Returns the formatted string representation of all the Tasks in the arraylist.
     *
     * @return Formatted string of all the tasks in the arraylist.
     */
    public String toString() {
        String list = "";
        for (int i = 0; i < tasks.size(); i++) {
            String task = tasks.get(i).toString();
            list = list.concat(String.format("%d.%s%n", i + 1, task));
        }
        assert (list.length() > 0);
        return list;
    }
}
