package duke.task;

import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;
import duke.util.DukeDateTime;

/**
 * Represents the interface that deals with user's tasks
 */
public class TaskManager {
    // Success Messages
    private static final String TASKS_COUNT_MESSAGE = "Now you have %d %s in the list.";
    private static final String UNDONE_TASKS_COUNT_MESSAGE = "You have %d incomplete %s remaining.";
    private static final String TASK_ADDED_MESSAGE = "Got it. I've added this task:\n  %s\n\n" + TASKS_COUNT_MESSAGE;
    private static final String MARKED_TASK_AS_DONE_MESSAGE = "Nice! I've marked this task as done:\n  %s\n\n"
            + UNDONE_TASKS_COUNT_MESSAGE;
    private static final String DELETED_TASK_MESSAGE = "Noted. I've removed this task:\n  %s\n\n" + TASKS_COUNT_MESSAGE;

    // Error Messages
    private static final String TASK_NOT_FOUND_MESSAGE =
            "You don't have a task with that number.";

    private final List<Task> taskList;

    public TaskManager(List<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskManager() {
        taskList = new ArrayList<>();
    }

    /**
     * Returns the number of tasks.
     */
    public int getTaskCount() {
        return taskList.size();
    }

    /**
     * Returns the number of incomplete tasks.
     */
    private int getUndoneTaskCount() {
        int count = 0;
        for (Task t : taskList) {
            if (!t.checkTaskDone()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Adds a <code>Task</code> into the list of tasks.
     * @param task the task to be added
     * @return a message containing the task just added and the updated number of tasks
     */
    public String addTask(Task task) {
        taskList.add(task);
        int taskCount = getTaskCount();
        String pluralised = taskCount > 1 ? "tasks" : "task";
        return String.format(TASK_ADDED_MESSAGE, task, taskCount, pluralised);
    }

    /**
     * Marks a <code>Task</code> as completed.
     * @param taskNumber the number of the task to be marked as completed
     * @return a message containing the task just marked as completed and the updated number of incomplete tasks
     * @throws DukeException if a task with that number cannot be found
     */
    public String markTaskAsDone(int taskNumber) throws DukeException {
        try {
            // User input is 1-indexed
            int taskIndex = taskNumber - 1;
            Task task = taskList.get(taskIndex);
            task.markAsDone();
            int undoneTaskCount = getUndoneTaskCount();
            String pluralised = undoneTaskCount > 1 || undoneTaskCount == 0 ? "tasks" : "task";
            return String.format(MARKED_TASK_AS_DONE_MESSAGE, task, undoneTaskCount, pluralised);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(TASK_NOT_FOUND_MESSAGE);
        }
    }

    /**
     * Deletes a <code>Task</code> from the list.
     * @param taskNumber the number of the task to be deleted
     * @return a message containing the task just deleted and the updated number of tasks
     * @throws DukeException if a task with that number cannot be found
     */
    public String deleteTask(int taskNumber) throws DukeException {
        try {
            // User input is 1-indexed
            int taskIndex = taskNumber - 1;
            Task task = taskList.remove(taskIndex);
            int taskCount = getTaskCount();
            String pluralised = taskCount > 1 || taskCount == 0 ? "tasks" : "task";
            return String.format(DELETED_TASK_MESSAGE, task, taskCount, pluralised);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(TASK_NOT_FOUND_MESSAGE);
        }
    }

    /**
     * Displays the user's tasks.
     */
    public String list() {
        String[] allTasks = new String[taskList.size()];
        for (int i = 0; i < allTasks.length; i++) {
            Task task = taskList.get(i);
            // Display numbers are 1-indexed
            int taskNumber = i + 1;
            allTasks[i] = prependNumberToTask(taskNumber, task);
        }
        return String.join("\n", allTasks);
    }

    /**
     * Displays the user's tasks that fall on a specific date.
     * @param dateTime the date to filter by
     * @return the string representation of the filtered tasks
     */
    public String list(DukeDateTime dateTime) {
        List<Task> filteredTasks = filterByDate(dateTime);
        String[] filteredTasksStrings = new String[filteredTasks.size()];
        for (int i = 0; i < filteredTasksStrings.length; i++) {
            Task task = filteredTasks.get(i);
            int taskNumber = i + 1;
            filteredTasksStrings[i] = prependNumberToTask(taskNumber, task);
        }
        return String.join("\n", filteredTasksStrings);
    }

    /**
     * Displays the user's tasks which have names that contain any of the specified search strings.
     * @param searchStrings the target search strings
     * @return the string representation of the filtered tasks
     */
    public String list(String... searchStrings) {
        List<Task> filteredTasks = filterByName(searchStrings);
        String[] filteredTasksStrings = new String[filteredTasks.size()];
        for (int i = 0; i < filteredTasksStrings.length; i++) {
            Task task = filteredTasks.get(i);
            int taskNumber = i + 1;
            filteredTasksStrings[i] = prependNumberToTask(taskNumber, task);
        }
        return String.join("\n", filteredTasksStrings);
    }

    /**
     * Returns a list of tasks that occur on the specified date.
     * @param dateTime the date to filter by
     * @return the filtered list
     */
    private List<Task> filterByDate(DukeDateTime dateTime) {
        List<Task> tasks = new ArrayList<>();
        for (Task t : taskList) {
            if (!(t instanceof Timestampable)) {
                continue;
            }
            Timestampable timestampableTask = (Timestampable) t;
            if (timestampableTask.onSameDayAs(dateTime)) {
                tasks.add(t);
            }
        }
        return tasks;
    }

    /**
     * Returns a list of tasks that have names that include any of the specified search strings.
     * @param searchStrings the strings to filter by
     * @return the filtered list
     */
    private List<Task> filterByName(String... searchStrings) {
        List<Task> tasks = new ArrayList<>();
        for (Task t : taskList) {
            for (String s : searchStrings) {
                if (t.getName().toLowerCase().contains(s.toLowerCase())) {
                    tasks.add(t);
                    break;
                }
            }
        }
        return tasks;
    }

    private String prependNumberToTask(int taskNumber, Task task) {
        return String.format("%d. %s", taskNumber, task.toString());
    }

    /**
     * Returns the text representation of the task list.
     */
    public String toText() {
        String[] tasks = new String[taskList.size()];
        for (int i = 0; i < taskList.size(); i++) {
            tasks[i] = taskList.get(i).toText();
        }
        return String.join("\n", tasks);
    }

    @Override
    public String toString() {
        return list();
    }
}
