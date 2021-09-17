package duke;

import java.util.ArrayList;

/**
 * Handles any interaction with task list
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructs an empty TaskList
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructs a TaskList using an ArrayList of Tasks
     *
     * @param list ArrayList of Tasks to be used
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds Task to TaskList
     *
     * @param task
     */
    public void addTask(Task task) {
        list.add(task);
    }


    /**
     * Returns a copy of the list
     *
     * @return ArrayList copy containing all of the tasks
     */
    public ArrayList<Task> getList() {
        ArrayList<Task> list = (ArrayList<Task>) this.list.clone();
        return list;
    }

    /**
     * Obtains Task from TaskList
     *
     * @param index Index of task to be retrieved
     * @return Task corresponding to index
     * @throws DukeException
     */
    public Task getTask(int index) throws DukeException {
        Task task;
        try {
            task = list.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry, there are only " + list.size() + " tasks.");
        }
        return task;
    }

    /**
     * Deletes Task from TaskList
     *
     * @param index Index of task to be deleted
     * @return Task that was deleted from list
     * @throws DukeException
     */
    public Task deleteTask(int index) throws DukeException {
        Task task = getTask(index);
        list.remove(index);
        return task;
    }

    /**
     * Sets Task from TaskList to completed
     *
     * @param index Index of task to be completed
     * @return Task that was completed
     * @throws DukeException
     */
    public Task doneTask(int index) throws DukeException {
        Task task = getTask(index);
        task.setComplete(true);
        return task;
    }

    /**
     * Outputs list of all Tasks with descriptions
     *
     * @return List of all Tasks with descriptions
     */
    public String allTasks() {
        int i = 1;
        String result = "";
        for (Task item : list) {
            result += i + ". " + item.getTaskType() + item.getStatusIcon() + " " + item.getDescription() + "\n";
            i++;
        }
        return result;
    }

    /**
     * Returns size of list
     *
     * @return Current size of task list
     */
    public int size() {
        return list.size();
    }

    /**
     * Gets toString() of all tasks in list
     *
     * @return String containing all tasks in list
     */
    @Override
    public String toString() {
        String data = "";
        for (Task task : list) {
            data += task.toString() + "\n";
        }
        return data;
    }
}
