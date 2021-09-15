package jarvis.task;

import java.util.ArrayList;
import java.util.stream.IntStream;

import jarvis.exception.InvalidDateTimeInputException;
import jarvis.exception.JarvisException;
import jarvis.storage.Storage;

/**
 * Encapsulates the list of tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;
    private ArrayList<String> versionHistory = new ArrayList<>();

    /**
     * Constructor for TaskList.
     *
     * @param taskList The array list in which the tasks are stored.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a todo with the given description.
     *
     * @param todoDescription The todo description.
     * @return The new Todo object.
     */
    public Todo addTodo(String todoDescription) {
        addToVersionHistory();
        Todo todo = new Todo(todoDescription);
        taskList.add(todo);
        return todo;
    }

    /**
     * Adds a deadline task with given description and deadline.
     *
     * @param taskDescription The deadline task description.
     * @param deadline The deadline.
     * @return The new Deadline object.
     * @throws InvalidDateTimeInputException If the deadline is of the wrong format.
     */
    public Deadline addTaskWithDeadline(String taskDescription, String deadline) throws InvalidDateTimeInputException {
        addToVersionHistory();
        Deadline taskWithDeadline = new Deadline(taskDescription, deadline);
        taskList.add(taskWithDeadline);
        return taskWithDeadline;
    }

    /**
     * Adds an event with given description and event time.
     *
     * @param eventDescription The event description.
     * @param eventDate The event date.
     * @param eventStartTime The event start time.
     * @param eventEndTime The event end time.
     * @return The new Event object
     * @throws InvalidDateTimeInputException If there is an error parsing the date and time.
     */
    public Event addEvent(String eventDescription, String eventDate, String eventStartTime, String eventEndTime)
            throws InvalidDateTimeInputException {
        addToVersionHistory();
        Event event = new Event(eventDescription, eventDate, eventStartTime, eventEndTime);
        taskList.add(event);
        return event;
    }

    /**
     * Marks a specific task in list as done.
     *
     * @param index The index of the task.
     * @return The task that was marked as done.
     */
    public Task markAsDone(int index) {
        assert index >= 0 && index < taskList.size() : "Task index out of bounds";
        addToVersionHistory();
        Task task = taskList.get(index);
        task.markAsDone();
        return task;
    }

    /**
     * Deletes a specific task in list.
     *
     * @param index The index of the task.
     * @return The task that was deleted.
     */
    public Task deleteTask(int index) {
        assert index >= 0 && index < taskList.size() : "Task index out of bounds";
        addToVersionHistory();
        return taskList.remove(index);
    }

    /**
     * Gets the total size of the task list.
     *
     * @return The size of list.
     */
    public int getTaskListSize() {
        return taskList.size();
    }

    /**
     * Gets the summary string representation of the task list.
     *
     * @return A string that is a summary of the task list.
     */
    public String getTaskListSummary() {
        return String.format("Now you have %s task(s) in the list.", taskList.size());
    }

    /**
     * Gets the matching task list with tasks that contain the keyword.
     *
     * @param keyword The search keyword.
     * @return The task list with only matching tasks.
     */
    public TaskList getListWithKeyword(String keyword) {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                taskArrayList.add(task);
            }
        }
        return new TaskList(taskArrayList);
    }

    /**
     * Reverts the version history by the amount specified, rewrites the storage file and changes the taskList.
     *
     * @param amount The amount the revert.
     * @param storage Storage to save or load tasks to hard-disk.
     * @throws JarvisException If there is an error.
     */
    public void revertHistory(int amount, Storage storage) throws JarvisException {
        String previousHistory = versionHistory.get(versionHistory.size() - amount);
        storage.rewriteStorageFile(previousHistory);
        versionHistory.subList(versionHistory.size() - amount, versionHistory.size()).clear();
        this.taskList = storage.loadTasksFromFile();
    }

    /**
     * Gets the total size of the version history list.
     *
     * @return The size of version history list.
     */
    public int getVersionHistorySize() {
        return versionHistory.size();
    }

    /**
     * Gets the string representation of the task list.
     *
     * @return The string representation of the task list.
     */
    @Override
    public String toString() {
        if (taskList.size() == 0) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();

        IntStream.range(0, taskList.size()).mapToObj(i -> String.format(
                "%s. %s%s",
                i + 1,
                taskList.get(i).toString(),
                i < taskList.size() - 1 ? "\n" : ""
        )).forEach(stringBuilder::append);

        return stringBuilder.toString();
    }

    /**
     * Gets the string representation of the task list that is to be saved to storage file.
     *
     * @return The string representation that is to be saved.
     */
    public String toStorageFormatString() {
        StringBuilder stringBuilder = new StringBuilder();

        taskList.stream()
                .map(Task::toStorageFormatString)
                .forEach(taskString -> {
                    stringBuilder.append(taskString);
                    stringBuilder.append(System.lineSeparator());
                });

        return stringBuilder.toString();
    }

    private void addToVersionHistory() {
        versionHistory.add(this.toStorageFormatString());
    }
}
