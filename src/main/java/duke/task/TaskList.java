package duke.task;

import duke.DukeException;
import duke.Ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks to do.
 */
public class TaskList {

    /**
     * The todo list that stores tasks.
     */
    private ArrayList<Task> todoList;

    /**
     * The types of tasks that this list contains.
     */
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Constructs a task list with no tasks in it.
     */
    public TaskList() {
        this.todoList = new ArrayList<>();
    }

    /**
     * Constructs a task list with the tasks given.
     * @param tasks An array of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.todoList = tasks;
    }

    /**
     * Prints this todo list in order.
     */
    public void displayList(Ui ui) {
        ui.showMessage("Your task list:");
        for (int i = 0; i < todoList.size(); i++) {
            Task task = todoList.get(i);
            int num = i + 1;
            ui.showMessage((num + "." + task.toString()));
        }
    }

    /**
     * Add a task to this todo list.
     *
     * @param taskType The type of task to be added.
     * @param details The details of the task.
     * @return The task added into the list.
     * @throws DukeException
     */
    public Task addTask(TaskType taskType, String details) throws DukeException {
        Task task;
        if (taskType.equals(TaskType.TODO)) {
            task = new ToDo(details);
        } else if (taskType.equals(TaskType.DEADLINE)) {
            int position = details.indexOf("/by");
            String description, by;
            if (position >= 0) {
                description = details.substring(0, position);
                by = details.substring(position + 3);
            } else {
                throw new DukeException("Please indicate the deadline eg \"/by Sunday\" ");
            }
            task = new Deadline(description.trim(), by.trim());
        } else if (taskType.equals(TaskType.EVENT)) {
            int position = details.indexOf("/at");
            String description, at;
            if (position >= 0) {
                description = details.substring(0, position);
                at = details.substring(position + 3);
            } else {
                throw new DukeException("Please indicate the event time eg \"/at Mon 2-4pm\" ");
            }
            task = new Event(description.trim(), at.trim());
        } else {
            // should not reach here
            throw new DukeException("Invalid task type.");
        }
        todoList.add(task);

        return task;
    }

    /**
     * Mark a task with given task number as done.
     *
     * @param taskNum The task number of the task to be marked as done.
     * @return The task marked as done.
     * @throws DukeException
     */
    public Task markTaskDone(Integer taskNum) throws DukeException {
        Task task = todoList.get(taskNum - 1);
        task.markAsDone();

        return task;
    }

    /**
     * Delete a task with given task number.
     *
     * @param taskNum The task number of the task to be deleted from the list.
     * @return The deleted task.
     * @throws DukeException
     */
    public Task deleteTask(Integer taskNum) throws DukeException {
        Task task = todoList.remove(taskNum - 1);

        return task;
    }

    /**
     * Finds tasks in the task list that matches a given keyword.
     * @param keyword The keyword that the user wants to search for.
     * @return A list of tasks that match the keyword.
     * @throws DukeException
     */
    public ArrayList<Task> findTask(String keyword) throws DukeException {
        if (keyword == "") {
            throw new DukeException("Looks like you forgot to enter a keyword.");
        }

        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : todoList) {
            if (task.contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Convert this task list into data string format.
     *
     * @return A list of tasks in data string format.
     */
    public List<String> getListData() {
        return todoList.stream().map(task -> task.toDataString("|")).collect(Collectors.toList());
    }

    /**
     * Returns the size of this task list.
     *
     * @return returns the size of this task list.
     */
    public Integer getListSize() {
        return todoList.size();
    }
}