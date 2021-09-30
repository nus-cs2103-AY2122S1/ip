package poseidon.tasklist;

import java.util.ArrayList;

import poseidon.task.Task;

/**
 * Represents an {@code TaskList} object that contains an {@code ArrayList} to keep track of the {@code Task}s during
 * runtime and dictates operations based for modifying the {@code ArrayList}.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs a new {@code TaskList} object by initializing a new {@code ArrayList} for runtime storage.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a new {@code TaskList} object by storing the given {@code ArrayList} for runtime storage.
     *
     * @param tasks ArrayList to be used runtime storage.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a {@code String} message indicating success after adding a {@code Task} to the list of tasks.
     *
     * @param newTask New {@code Task} to be added.
     * @return {@code String} success message.
     */
    public String addTask(Task newTask) {
        tasks.add(newTask);
        return "Got it. I've added this task:\n"
                + "  " + newTask + "\n"
                + countTasks();
    }

    /**
     * Returns a {@code String} message indicating success after marking a {@code Task} done in the list of tasks.
     *
     * @param taskIndex Index of the {@code Task}.
     * @return {@code String} success message.
     */
    public String markTaskDone(int taskIndex) {
        Task currTask = tasks.get(taskIndex - 1);
        currTask.setDone();
        assert currTask.toString().charAt(4) == 'X' : "Task is supposed to be marked done";
        return "Nice! I've marked this task as done:\n"
                + "  " + taskIndex + ". " + currTask;
    }

    /**
     * Returns a {@code String} message indicating success after deleting a {@code Task} from the list of tasks.
     *
     * @param taskIndex Index of the {@code Task}.
     * @return {@code String} success message.
     */
    public String deleteTask(int taskIndex) {
        Task currTask = tasks.get(taskIndex - 1);
        tasks.remove(taskIndex - 1);
        assert !tasks.contains(currTask) : "Removed task is not supposed to be in the TaskList";
        return "Noted. I've removed this task:\n"
                + "  " + taskIndex + ". " + currTask + "\n"
                + countTasks();
    }

    /**
     * Returns the {@code ArrayList} of {@code Task}s maintained by this {@code TaskList} object.
     *
     * @return {@code ArrayList} of {@code Task} objects.
     */
    public ArrayList<Task> getList() {
        return tasks;
    }

    /**
     * Returns the {@code String} representation of a {@code Task} (based on the Index) to be used for storage.
     *
     * @param taskIndex Index of the {@code Task}.
     * @return {@code String} for storage.
     */
    public String getTaskStorage(int taskIndex) {
        return tasks.get(taskIndex - 1).toStorage();
    }

    /**
     * Returns a {@code Boolean} indicating the validity of the {@code Task}'s Index.
     *
     * @param taskIndex Index of the {@code Task}.
     * @return Validity of the Index.
     */
    public boolean isIndexValid(int taskIndex) {
        return ((taskIndex - 1) < tasks.size() && (taskIndex - 1) > -1);
    }

    /**
     * Returns an {@code ArrayList} containing all the {@code Task}s that have the content in their description.
     *
     * @param content Content to be searched for.
     * @return {@code ArrayList} of {@code Task} objects.
     */
    public ArrayList<Task> findTasks(String content) {
        ArrayList<Task> filteredList = new ArrayList<>();

        tasks.stream()
                .filter((currTask) -> currTask.hasContent(content))
                .forEach(filteredList::add);

        return filteredList;
    }

    /**
     * Returns an {@code ArrayList} of {@code Task}s maintained by this {@code TaskList} object sorted by time.
     *
     * @return {@code ArrayList} of {@code Task} objects.
     */
    public ArrayList<Task> sortTasks() {
        ArrayList<Task> sortedList = new ArrayList<>();

        tasks.stream()
                .sorted()
                .forEach(sortedList::add);

        return sortedList;
    }

    private String countTasks() {
        if (tasks.size() > 0) {
            return "Now you have " + tasks.size() + " tasks in the list.";
        } else {
            return "There are no tasks in your list.";
        }
    }
}
