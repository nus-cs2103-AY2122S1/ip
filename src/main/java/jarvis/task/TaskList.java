package jarvis.task;

import java.util.ArrayList;

import jarvis.exception.InvalidDateTimeInputException;

/**
 * Encapsulates the list of tasks
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor for TaskList
     *
     * @param taskList The array list in which the tasks are stored
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a todo with the given description
     *
     * @param todoDescription The todo description
     * @return The new Todo object
     */
    public Todo addTodo(String todoDescription) {
        Todo todo = new Todo(todoDescription);
        taskList.add(todo);
        return todo;
    }

    /**
     * Adds a deadline task with given description and deadline
     *
     * @param taskDescription The deadline task description
     * @param deadline The deadline
     * @return The new Deadline object
     * @throws InvalidDateTimeInputException If the deadline is of the wrong format
     */
    public Deadline addTaskWithDeadline(String taskDescription, String deadline) throws InvalidDateTimeInputException {
        Deadline taskWithDeadline = new Deadline(taskDescription, deadline);
        taskList.add(taskWithDeadline);
        return taskWithDeadline;
    }

    /**
     * Adds an event with given description and event time
     *
     * @param eventDescription The event description
     * @param eventTime The event time
     * @return The new Event object
     */
    public Event addEvent(String eventDescription, String eventTime) {
        Event event = new Event(eventDescription, eventTime);
        taskList.add(event);
        return event;
    }

    /**
     * Marks a specific task in list as done
     *
     * @param index The index of the task
     * @return The task that was marked as done
     */
    public Task markAsDone(int index) {
        Task task = taskList.get(index);
        task.markAsDone();
        return task;
    }

    /**
     * Deletes a specific task in list
     *
     * @param index The index of the task
     * @return The task that was deleted
     */
    public Task deleteTask(int index) {
        return taskList.remove(index);
    }

    /**
     * Gets the total size of the task list
     *
     * @return The size of list
     */
    public int getTaskListSize() {
        return taskList.size();
    }

    /**
     * A summary of the task list
     *
     * @return A string that is a summary of the task list
     */
    public String taskListSummary() {
        return String.format("Now you have %s task(s) in the list.", taskList.size());
    }

    /**
     * Gets the matching task list with tasks that contain the keyword
     *
     * @param keyword The search keyword
     * @return The task list with only matching tasks
     */
    public TaskList getListWithKeyword(String keyword) {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        for (Task task: taskList) {
            if (task.toString().toLowerCase().contains(keyword)) {
                taskArrayList.add(task);
            }
        }
        return new TaskList(taskArrayList);
    }

    /**
     * The string representation of the task list
     *
     * @return The string representation of the task list
     */
    @Override
    public String toString() {
        if (taskList.size() == 0) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            String task = String.format(
                    "%s. %s%s",
                    i + 1,
                    taskList.get(i).toString(),
                    i < taskList.size() - 1 ? "\n" : ""
            );
            stringBuilder.append(task);
        }

        return stringBuilder.toString();
    }

    /**
     * The string representation of the task list that is to be saved to storage file
     *
     * @return The string representation that is to be saved
     */
    public String toStorageFormatString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Task task : taskList) {
            stringBuilder.append(task.toStorageFormatString());
            stringBuilder.append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }
}
