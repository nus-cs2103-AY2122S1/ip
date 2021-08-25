package duke;

import duke.task.Task;

import java.util.ArrayList;

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
    public TaskList(ArrayList<Task> taskList){
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
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Invalid Task. Please try again.\n");
        }
    }

    /**
     * Returns the formatted string to write to the file.
     *
     * @return String to write to the file.
     */
    public String toSave() {
        String saveTasks = "";
        for (int i = 0; i<tasks.size(); i++) {
            String taskToWrite = tasks.get(i).toWrite();
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
        return list;
    }
}
