package duke.task;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.io.TaskStorage;

/**
 * Class representing the list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructor of task list. Loads tasks from the saved tasks.
     */
    public TaskList() {
        this.tasks = TaskStorage.load();
    }

    /**
     * Adds the task into the taskList.
     * Also saves the taskList and returns a message to be printed.
     *
     * @param task The task to be added.
     * @return The response that confirms the task has been added.
     * @throws DukeException I/O exception from saving the taskList state.
     */
    public String addTask(Task task) throws DukeException {
        int oldLength = tasks.size();
        tasks.add(task);
        int newLength = tasks.size();
        assert newLength == oldLength + 1 : "TaskList size should increase by one";

        save();

        return "Got it. I've added this task:\n  " + task.toString() + "\n" + getTaskLengthReport();
    }

    /**
     * Marks the specified task as done.
     * Also saves the taskList and returns a message to be printed.
     *
     * @param taskNum The number of the task to be marked as done (1-indexed).
     * @return The response confirming the marking of the task to be done.
     * @throws DukeException Exception from out of range index, or from saving the list.
     */
    public String doTask(int taskNum) throws DukeException {
        int idx = getTaskIndexFromTaskNum(taskNum);
        assert idx == taskNum - 1 : "Task index should be one lower than task number";

        Task task = tasks.get(idx);
        task.doTask();
        assert task.isDone() : "Task should be done";

        save();

        return "Nice! I've marked this task as done:\n  " + task.toString();
    }

    /**
     * Deletes the specified task.
     * Also saves the taskList and returns a message to be printed.
     *
     * @param taskNum The number of the task to be deleted (1-indexed).
     * @return The response that confirms the deletion of the task.
     * @throws DukeException Exception from out of range index, or from saving the list.
     */
    public String deleteTask(int taskNum) throws DukeException {
        int idx = getTaskIndexFromTaskNum(taskNum);
        assert idx == taskNum - 1 : "Task index should be one lower than task number";

        int oldLength = tasks.size();
        Task task = tasks.get(idx);
        tasks.remove(idx);
        int newLength = tasks.size();
        assert newLength == oldLength - 1;

        save();

        return "Noted! I've removed this task:\n  " + task.toString() + "\n" + getTaskLengthReport();
    }

    /**
     * Deletes all tasks that have been marked as done.
     * Also saves the taskList and returns a message to be printed.
     *
     * @return The response confirming deletion of the tasks.
     * @throws DukeException I/O exception from saving the taskList state.
     */
    public String deleteDone() throws DukeException {
        tasks.removeIf(Task::isDone);
        assert tasks.stream().noneMatch(Task::isDone) : "All tasks should not be done";

        save();
        return "Noted! I've removed all completed tasks.\n" + getTaskLengthReport();
    }

    /**
     * Deletes all tasks that are expired.
     * Also saves the taskList and returns a message to be printed.
     *
     * @return The response confirming deletion of the tasks.
     * @throws DukeException I/O exception from saving the taskList state.
     */
    public String deleteExpired() throws DukeException {
        tasks.removeIf(Task::isExpired);
        assert tasks.stream().noneMatch(Task::isExpired) : "All tasks should not be expired";

        save();
        return "Noted! I've removed all expired tasks.\n" + getTaskLengthReport();
    }

    /**
     * Returns a message listing all the tasks to be printed.
     *
     * @return The response which lists all tasks.
     */
    public String list() {
        int size = tasks.size();
        if (size == 0) {
            return "No tasks yet!";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < size; i++) {
            sb.append(i + 1);
            sb.append(". ");
            sb.append(tasks.get(i).toString());
            sb.append('\n');
        }

        // delete the last newline character
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    /**
     * Finds all tasks with name matching the search string (case insensitive).
     *
     * @param searchString The string to find in the tasks.
     * @return The response containing all the tasks that match the search string.
     */
    public String find(String searchString) {
        int size = tasks.size();
        StringBuilder sb = new StringBuilder();
        int foundCount = 0;
        sb.append("Here are the matching tasks in your list:");

        for (int i = 0; i < size; i++) {
            Task task = tasks.get(i);
            // case insensitive search
            String taskName = task.getName().toLowerCase();
            if (taskName.contains(searchString.toLowerCase())) {
                // print each task indented, in a new line
                sb.append("\n  ");
                sb.append(i + 1);
                sb.append(". ");
                sb.append(task.toString());
                foundCount++;
            }
        }

        if (foundCount == 0) {
            return "No matching tasks!";
        }

        return sb.toString();
    }

    /**
     * Returns the 0-indexed index of the task from it's 1-indexed index.
     *
     * @param taskNum 1-indexed index of the task.
     * @return 0-indexed index of the task.
     * @throws DukeException Exception thrown if the index is out of range.
     */
    private int getTaskIndexFromTaskNum(int taskNum) throws DukeException {
        if (taskNum <= 0 || taskNum > tasks.size()) {
            throw new DukeException("Please input the ID of a task!");
        }

        // tasks are 1-indexed to the user, but 0-indexed by implementation
        return taskNum - 1;
    }

    /**
     * Returns a string telling the user how many tasks are in the list.
     *
     * @return String telling the user how many tasks are in the list.
     */
    private String getTaskLengthReport() {
        return "Now you have " + tasks.size()
                + (tasks.size() != 1 ? " tasks" : " task") + " in the list.";
    }

    /**
     * Saves the state of the taskList.
     *
     * @throws DukeException I/O exception from saving.
     */
    private void save() throws DukeException {
        TaskStorage.save(tasks);
    }

    /**
     * Returns the taskList to operate on without any side effects.
     *
     * @return The list of tasks.
     */
    protected ArrayList<Task> getList() {
        return tasks;
    }
}
