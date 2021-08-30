package duke.task;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.io.Storage;

/**
 * Class representing the list of tasks
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private final Storage storage;

    /**
     * Constructor of task list. Loads tasks from specified savefile.
     *
     * @param storage The storage system that manages saving and loading
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        this.tasks = storage.load();
    }

    /**
     * Adds the task into the taskList
     * Also saves the taskList and queues a message to be printed
     *
     * @param task The task to be added
     * @return The response
     * @throws DukeException I/O exception from saving the taskList state
     */
    public String addTask(Task task) throws DukeException {
        tasks.add(task);
        save();

        return "Got it. I've added this task:\n  " + task.toString() + "\n" + getTaskLengthReport();
    }

    /**
     * Marks the specified task as done
     * Also saves the taskList and queues a message to be printed
     *
     * @param taskNum The number of the task to be marked as done (1-indexed)
     * @return The response
     * @throws DukeException Exception from out of range index, or from saving the list
     */
    public String doTask(int taskNum) throws DukeException {
        int idx = getTaskIndexFromTaskNum(taskNum);
        Task task = tasks.get(idx);
        task.doTask();
        save();

        return "Nice! I've marked this task as done:\n  " + task.toString();
    }

    /**
     * Deletes the specified task
     * Also saves the taskList and queues a message to be printed
     *
     * @param taskNum The number of the task to be deleted (1-indexed)
     * @return The response
     * @throws DukeException Exception from out of range index, or from saving the list
     */
    public String deleteTask(int taskNum) throws DukeException {
        int idx = getTaskIndexFromTaskNum(taskNum);

        Task task = tasks.get(idx);
        tasks.remove(idx);
        save();

        return "Noted! I've removed this task:\n  " + task.toString() + "\n" + getTaskLengthReport();
    }

    /**
     * Deletes all tasks that have been marked as done
     * Also saves the taskList and queues a message to be printed
     *
     * @return The response
     * @throws DukeException I/O exception from saving the taskList state
     */
    public String deleteDone() throws DukeException {
        tasks.removeIf(Task::isDone);
        save();
        return "Noted! I've removed all completed tasks.\n" + getTaskLengthReport();
    }

    /**
     * Deletes all tasks that are expired
     * Also saves the taskList and queues a message to be printed
     *
     * @return The response
     * @throws DukeException I/O exception from saving the taskList state
     */
    public String deleteExpired() throws DukeException {
        tasks.removeIf(Task::isExpired);
        save();
        return "Noted! I've removed all expired tasks.\n" + getTaskLengthReport();
    }

    /**
     * Queues a message listing all the tasks to be printed
     *
     * @return The response
     */
    public String list() {
        int size = tasks.size();
        StringBuilder sb = new StringBuilder();

        if (size == 0) {
            sb.append("No tasks yet!");
        } else {
            for (int i = 0; i < size; i++) {
                Task task = tasks.get(i);
                sb.append(i + 1);
                sb.append(". ");
                sb.append(task.toString());
                sb.append('\n');
            }
            // delete the last newline character
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

    /**
     * Finds all tasks with name matching the search string (case insensitive)
     * Prints the tasks and their status
     *
     * @param searchString the string to find in the tasks
     * @return The response
     */
    public String find(String searchString) {
        int size = tasks.size();
        StringBuilder sb = new StringBuilder();
        int foundCount = 0;
        sb.append("Here are the matching tasks in your list:");

        for (int i = 0; i < size; i++) {
            Task task = tasks.get(i);
            // case insensitive search
            if (task.getName().toLowerCase().contains(searchString.toLowerCase())) {
                // print each task indented, in a new line
                sb.append('\n');
                sb.append(i + 1);
                sb.append(". ");
                sb.append(task.toString());
                foundCount++;
            }
        }

        if (foundCount == 0) {
            return "No matching tasks!";
        } else {
            return sb.toString();
        }
    }

    /**
     * Returns the 0-indexed index of the task from it's 1-indexed index
     *
     * @param taskNum 1-indexed index of the task
     * @return 0-indexed index of the task
     * @throws DukeException Exception thrown if the index is out of range
     */
    private int getTaskIndexFromTaskNum(int taskNum) throws DukeException {
        if (taskNum <= 0 || taskNum > tasks.size()) {
            throw new DukeException("Please input the ID of a task!");
        }

        // tasks are 1-indexed to the user, but 0-indexed by implementation
        return taskNum - 1;
    }

    /**
     * Returns a string telling the user how many tasks are in the list
     *
     * @return string telling the user how many tasks are in the list
     */
    private String getTaskLengthReport() {
        return "Now you have " + tasks.size()
                + (tasks.size() != 1 ? " tasks" : " task") + " in the list.";
    }

    /**
     * Saves the state of the taskList
     *
     * @throws DukeException I/O exception from saving
     */
    private void save() throws DukeException {
        storage.save(tasks);
    }

    /**
     * Returns the taskList to operate on without any side effects
     *
     * @return the taskList
     */
    protected ArrayList<Task> getList() {
        return tasks;
    }
}
