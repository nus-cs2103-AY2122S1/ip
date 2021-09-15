package duke.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private List<Task> taskList;

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
            if (!t.isDone()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Overwrites the existing <code>taskList</code>.
     */
    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a <code>Task</code> into the list of tasks.
     *
     * @param task Task to be added.
     * @return Message containing the task just added and the updated number of tasks.
     */
    public String addTask(Task task) {
        assert task != null : "Task should be initialised within xCommand::execute";
        taskList.add(task);
        int taskCount = getTaskCount();
        String pluralised = taskCount > 1 ? "tasks" : "task";
        return String.format(TASK_ADDED_MESSAGE, task, taskCount, pluralised);
    }

    /**
     * Marks a <code>Task</code> as completed.
     *
     * @param taskNumber Number of the task to be marked as completed.
     * @return Message containing the task just marked as completed and the updated number of incomplete tasks.
     * @throws DukeException If a task with that number cannot be found.
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
     *
     * @param taskNumber Number of the task to be deleted.
     * @return Message containing the task just deleted and the updated number of tasks.
     * @throws DukeException If a task with that number cannot be found.
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
        return formatTasksAsList(taskList);
    }

    /**
     * Displays the user's tasks that fall on a specific date.
     *
     * @param dateTime Date to filter by.
     * @return String representation of the filtered tasks.
     */
    public String list(DukeDateTime dateTime) {
        List<Task> filteredTasks = filterByDate(dateTime);
        return formatTasksAsList(filteredTasks);
    }

    /**
     * Displays the user's tasks which have names that contain any of the specified search strings.
     *
     * @param searchStrings Target search strings.
     * @return String representation of the filtered tasks.
     */
    public String list(String... searchStrings) {
        List<Task> filteredTasks = filterByName(searchStrings);
        return formatTasksAsList(filteredTasks);
    }

    /**
     * Returns a list of tasks that occur on the specified date.
     *
     * @param dateTime Date to filter by.
     * @return Filtered list.
     */
    private List<Task> filterByDate(DukeDateTime dateTime) {
        Predicate<Task> isSameDate = task ->
                task instanceof Timestampable && ((Timestampable) task).onSameDayAs(dateTime);
        Stream<Task> filteredTasks = taskList.stream().filter(isSameDate);
        return filteredTasks.collect(Collectors.toList());
    }

    /**
     * Returns a list of tasks that have names that include any of the specified search strings.
     *
     * @param searchStrings Strings to filter by.
     * @return Filtered list.
     */
    private List<Task> filterByName(String... searchStrings) {
        Predicate<Task> hasMatchingString = task ->
                Arrays.stream(searchStrings).anyMatch(searchString ->
                        task.getName().toLowerCase().contains(searchString.toLowerCase()));
        Stream<Task> filteredTasks = taskList.stream().filter(hasMatchingString);
        return filteredTasks.collect(Collectors.toList());
    }

    private static String prependNumberToTask(int taskNumber, Task task) {
        return String.format("%d. %s", taskNumber, task.toString());
    }

    private static String formatTasksAsList(List<Task> tasks) {
        if (tasks.size() == 0) {
            return "You do not have any tasks.";
        }

        String[] tasksStrings = new String[tasks.size()];
        for (int i = 0; i < tasksStrings.length; i++) {
            Task task = tasks.get(i);
            int taskNumber = i + 1;
            tasksStrings[i] = prependNumberToTask(taskNumber, task);
        }
        return String.join("\n", tasksStrings);
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
