package poseidon;

import java.util.ArrayList;

import poseidon.task.Task;

public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList object by initializing a new ArrayList for runtime storage.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a new TaskList object by storing the given ArrayList for runtime storage.
     *
     * @param tasks ArrayList to be used runtime storage.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a string message indicating success after adding a task to the list of tasks.
     *
     * @param newTask New task to be added.
     * @return String success message.
     */
    public String addTask(Task newTask) {
        tasks.add(newTask);
        return "Got it. I've added this task:\n"
                + "  " + newTask + "\n"
                + countTasks();
    }

    /**
     * Returns a string message indicating success after marking a task done in the list of tasks.
     *
     * @param taskIndex Index of the Task.
     * @return String success message.
     */
    public String markTaskDone(int taskIndex) {
        Task currTask = tasks.get(taskIndex - 1);
        currTask.setDone();
        assert currTask.toString().charAt(5) == 'X' : "Task is supposed to be marked done";
        return "Nice! I've marked this task as done:\n"
                + "  " + taskIndex + ". " + currTask;
    }

    /**
     * Returns a string message indicating success after deleting a task from the list of tasks.
     *
     * @param taskIndex Index of the Task.
     * @return String success message.
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
     * Returns the ArrayList of tasks maintained by this TaskList object.
     *
     * @return ArrayList of Task objects.
     */
    public ArrayList<Task> getList() {
        return tasks;
    }

    /**
     * Returns the string representation of a Task (based on the Index) to be used for storage.
     *
     * @param taskIndex Index of the Task.
     * @return String for storage.
     */
    public String getTaskStorage(int taskIndex) {
        return tasks.get(taskIndex - 1).toStorage();
    }

    /**
     * Returns a boolean indicating the validity of the Task's Index.
     *
     * @param taskIndex Index of the Task.
     * @return Validity of the Index.
     */
    public boolean isIndexValid(int taskIndex) {
        return ((taskIndex - 1) < tasks.size() && (taskIndex - 1) > -1);
    }

    /**
     * Returns an ArrayList containing all the tasks that have the content in their description.
     *
     * @param content Content to be searched for.
     * @return ArrayList of Task objects.
     */
    public ArrayList<Task> findTasks(String content) {
        ArrayList<Task> filteredList = new ArrayList<>();

        tasks.stream()
                .filter((currTask) -> currTask.hasContent(content))
                .forEach(filteredList::add);

        return filteredList;
    }

    /**
     * Returns an ArrayList of tasks maintained by this TaskList object sorted by time.
     *
     * @return ArrayList of Task objects.
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
