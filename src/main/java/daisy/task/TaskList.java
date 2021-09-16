package daisy.task;

import java.time.LocalDate;
import java.util.ArrayList;

import daisy.DaisyException;

/**
 * TaskList class handles the task list of Daisy.
 */
public class TaskList {
    static final String ZERO_TASK_STRING = "\nThere are no tasks in the list";
    static final String ONE_TASK_STRING = "\nThere is currently 1 task in your list";
    static final String MULTIPLE_TASK_STRING = "\nThere are currently %d tasks in your list";
    private ArrayList<Task> tasks;

    /**
     * Constructs the TaskList object.
     *
     * @param tasks Arraylist of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves a task from the task list and marks it as done.
     *
     * @param i Index of task in task list.
     * @return Completed task in task list.
     * @throws DaisyException  If taskNumber is invalid.
     */
    public Task markTaskAsDone(int i) throws DaisyException {
        if (i < 0 || i >= tasks.size()) {
            throw new DaisyException("I cannot find this task number!\n");
        }
        Task completedTask = tasks.get(i);
        completedTask.markAsDone();
        return completedTask;
    }

    /**
     * Checks for tasks in the task list that contain the query string
     * and returns the tasks in a string.
     *
     * @param query Query string to look for in task descriptions.
     * @return String of tasks containing query string.
     */
    public String checkForQuery(String query) {
        StringBuilder message = new StringBuilder();
        int n = 1;
        for (Task task : tasks) {
            if (task.containsQuery(query)) {
                message.append(n).append(".").append(task).append("\n");
                n++;
            }
        }
        return message.toString();
    }
    /**
     * Checks for tasks in the task list that have the same date
     * and returns the tasks in a string.
     *
     * @param date Date to check for in tasks.
     * @return String of tasks with the same date.
     */
    public String checkForDate(LocalDate date) {
        StringBuilder message = new StringBuilder();
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.isSameBy(date)) {
                    message.append(deadline).append("\n");
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.isSameTime(date)) {
                    message.append(event).append("\n");
                }
            }
        }
        return message.toString();
    }
    /**
     * Removes a task from the task list.
     *
     * @param i Index of task in task list.
     * @return Task removed from task list.
     * @throws DaisyException  If taskNumber is invalid.
     */
    public Task removeTask(int i) throws DaisyException {
        if (i < 0 || i >= tasks.size()) {
            throw new DaisyException("I cannot find this task number!\n");
        }
        return tasks.remove(i);
    }

    /**
     * Converts the task list to file format for saving.
     *
     * @return List of tasks in file format.
     */
    public String convertToFileFormat() {
        StringBuilder content = new StringBuilder();
        for (Task task : tasks) {
            content.append(task.convertToFileFormat()).append("\n");
        }
        return content.toString();
    }
    /**
     * Returns String representation of task list.
     *
     * @return String representation of task list.
     */
    public String getTaskList() {
        StringBuilder list = new StringBuilder("These are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            list.append(i + 1).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return list.toString();
    }

    /**
     * Returns String representation of list status.
     *
     * @return String representation of list status.
     */
    public String getListStatus() {
        if (tasks.size() == 0) {
            return ZERO_TASK_STRING;
        } else if (tasks.size() == 1) {
            return ONE_TASK_STRING;
        } else {
            return String.format(MULTIPLE_TASK_STRING, tasks.size());
        }
    }
    /**
     * Removes a task from the task list.
     *
     * @param i Index of task in task list.
     * @return Task removed from task list.
     * @throws DaisyException  If taskNumber is invalid.
     */
    public Task addTags(int i, String[] tags) throws DaisyException {
        if (i < 0 || i >= tasks.size()) {
            throw new DaisyException("I cannot find this task number!\n");
        }
        Task task = tasks.get(i);
        task.addTags(tags);
        return task;
    }
}
