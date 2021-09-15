package duke;

import java.util.ArrayList;

/**
 * The tasks in the Duke app.
 */
public class TaskList {

    /** The tasks are stored in a list */
    private ArrayList<Task> tasks;

    /** The data storage */
    private Storage storage;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.storage = new Storage("./data", "duke.txt");
    }

    /**
     * Constructor for TaskList
     * @param storage The storage to keep the saved data.
     */
    public TaskList(Storage storage) {
        assert storage != null : "[duke.TaskList.TaskList]: storage parameter should not be null.";

        this.tasks = new ArrayList<>();
        this.storage = storage;
    }

    /**
     * Constructor for TaskList.
     * @param tasks An ArrayList of Tasks.
     * @param storage The storage to keep the saved data.
     */
    public TaskList(ArrayList<Task> tasks, Storage storage) {
        assert tasks != null : "[duke.TaskList.TaskList]: tasks parameter should not be null.";
        assert storage != null : "[duke.TaskList.TaskList]: storage parameter should not be null.";

        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Adds an item to the list and return a status message.
     * @param task A Task to be added to the list.
     * @return A status message to be displayed.
     */
    public String addItem(Task task) throws DukeException {
        assert tasks != null : "[duke.TaskList.addItem]: tasks parameter should not be null.";

        tasks.add(task);
        String res = "Got it. I've added this task: \n"
                + "  " + task.toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the tasks";

        storage.addToFile(task.savedToString());
        return res;
    }

    /**
     * Marks the task at the given index as done and returns a status message.
     * @param index The index to be marked as done
     * @return  A status message to be displayed
     * @throws DukeException when a task is not found
     */
    public String markDone(int index) throws DukeException {
        Task task = getTask(index);
        task.markDone();

        storage.markLineDone(index);
        return "Great success! Task Complete: \n" + "  " + task.toString();
    }

    /**
     * Removes a task in the list and returns a status message.
     * @param index The index of the task to be removed.
     * @return A status message to be displayed.
     * @throws DukeException when a task is not found.
     */
    public String removeItem(int index) throws DukeException {
        Task task = getTask(index);
        tasks.remove(index);
        String res = "Got it. I've removed this task: \n"
                + "  " + task.toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list";

        storage.removeFromFile(index);
        return res;
    }

    /**
     * Returns all the task (represented by a string) that contains the given keyword.
     * @param keyword The keyword to search the task.
     * @return all the tasks (string) that contains the given keyword.
     */
    public String find(String keyword) {
        assert keyword != null : "[duke.TaskList.find]: keyword parameter should not be null.";

        StringBuilder str = new StringBuilder();
        str.append("Here are the matching tasks in your list:\n");
        int id = 1;
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                str.append(id++).append(". ").append(task.toString()).append("\n");
            }
        }
        return str.toString();
    }

    /**
     * Returns all the tasks as a string in the Task List
     * @return All the tasks as a string in the Task List
     */
    public String getAllTask() {
        StringBuilder str = new StringBuilder("This your task in list:\n");
        int size = tasks.size();
        for (int i = 0; i < size; ++i) {
            str.append(" ").append(i + 1).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return size == 0 ? "You currently have nothing in your list" : str.substring(0, str.length() - 1);
    }

    /**
     * The String representation of the TaskList object.
     * @return The string representation of the TaskList object.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Task task : tasks) {
            str.append(task.toString()).append("\n");
        }
        return str.toString();
    }

    private Task getTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            if (tasks.size() == 0) {
                throw new DukeException(DukeException.Errors.TASK_NOT_FOUND.toString()
                        + " Task list is empty.");
            } else if (tasks.size() == 1) {
                throw new DukeException(DukeException.Errors.TASK_NOT_FOUND.toString()
                        + " Only 1 item in the list.");
            } else {
                throw new DukeException(DukeException.Errors.TASK_NOT_FOUND.toString()
                        + " Input a number from [1..." + tasks.size() + "].");
            }
        }
        return tasks.get(index);
    }
}
