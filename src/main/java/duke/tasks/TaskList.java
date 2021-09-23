package duke.tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

import duke.exception.DukeException;
import duke.util.sort.SortByName;
import duke.util.sort.SortByType;

/**
 * The TaskList class the abstracts a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;


    /**
     * Constructor of the TaskList class.
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a Todo Task.
     *
     * @param input Name of the task.
     * @param isDone Completion status of the task.
     * @return The task that was added.
     */
    public Task addTodo(String input, boolean isDone) {
        Task t = new Todo(input, isDone);
        tasks.add(t);
        return t;
    }

    /**
     * Returns a Deadline Task.
     *
     * @param name Name of the deadline.
     * @param deadline The date and time of the deadline.
     * @param isDone Completion status of the deadline.
     * @return The task that was added.
     */
    public Task addDeadline(String name, LocalDateTime deadline, boolean isDone) {
        Task t = new Deadline(name, deadline, isDone);
        tasks.add(t);
        return t;
    }

    /**
     * Returns an Event Task.
     *
     * @param name Name of the event.
     * @param time The date and time of the event.
     * @param isDone Completion status of the event.
     * @return The task that was added.
     */
    public Task addEvent(String name, LocalDateTime time, boolean isDone) {
        Task t = new Event(name, time, isDone);
        tasks.add(t);
        return t;
    }

    /**
     * Deletes the task by index and returns the deleted task.
     *
     * @param index Index of the task to be deleted.
     * @return The task that was deleted.
     */
    public Task deleteTask(int index) {
        assert index < 0 && index > tasks.size();
        Task delete = tasks.get(index - 1);
        tasks.remove(index - 1);
        return delete;
    }

    /**
     * Marks the task by index as done and returns the completed task.
     *
     * @param index Index of the task to be completed.
     * @return The task that was completed.
     */
    public Task completeTask(int index) {
        assert index < 0 && index > tasks.size();
        Task complete = tasks.get(index - 1);
        complete.completeTask();
        return complete;
    }

    /**
     * Returns all the tasks in the list in their string representation.
     *
     * @return String representation of all the tasks in the list.
     */
    public String getAllTasks() {
        String listMessage = "";
        for (int i = 0; i < tasks.size(); i++) {
            listMessage = listMessage + (i + 1) + "." + tasks.get(i).toString() + "\n";
        }
        return listMessage;
    }

    /**
     * Returns the task by the given index.
     *
     * @param index Index of the task to be returned
     * @return The task at that index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Gets the total number of tasks in the task list.
     *
     * @return The total number of tasks.
     */
    public int getTotalTasksNumber() {
        return tasks.isEmpty() ? 0: tasks.size();
    }

    /**
     * Finds all tasks that has user input keyword in their description.
     *
     * @param name User input keyword.
     * @return String representation of all task that match keyword.
     */
    public String findTask(String name) {
        String foundTask = "";
        int index = 1;
        for (int i = 0; i < tasks.size(); i++) {
            Task find = tasks.get(i);
            String taskName = find.getName();
            String[] splitName = taskName.split(" ", 0);
            for (int j = 0; j < splitName.length; j++) {
                if (splitName[j].equals(name)) {
                    foundTask = foundTask + index + "." + find + "\n";
                    index += 1;
                }
            }
        }
        return foundTask;
    }

    /**
     * Sorts all tasks based on user input sorting keyword.
     *
     * @param sortName User input sorting keyword.
     * @return String representation of all task that match keyword.
     */
    public String sortTask(String sortName) throws DukeException {
        String key = sortName;
        switch (key) {
        case "name":
            Collections.sort(tasks, new SortByName());
            return getAllTasks();
        case "type":
            Collections.sort(tasks, new SortByType());
            return getAllTasks();
        default:
            throw new DukeException("Please enter a valid sort parameter.");
        }
    }
}
