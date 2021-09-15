package duke.util;

import java.util.ArrayList;
import java.util.Comparator;

import duke.exception.DukeException;
import duke.exception.DukeInvalidIndexException;
import duke.exception.DukeIoException;
import duke.exception.DukeNoSuchTaskException;
import duke.task.Task;

/**
 * This class encapsulates a Task Handler that manages all the tasks Duke can handle.
 *
 * @author Teo Sin Yee
 * @version CS2103T AY21/22 Semester 1
 */
public class TaskHandler {
    private static final String NO_TASKS_FOUND = "Nothing in the list... :(\n"
            + "Use todo/event/deadline to add something first! :^)";
    private static final String TASK_ADDED_MESSAGE = "Voila! ^_^ I've added this task:\n"
            + "- %s\nYou currently have %d task(s) in the list.";
    private static final String TASK_DONE_MESSAGE = "Good Job! :D I've marked this task as done:\n"
            + "- %s\nYou currently have %d undone task(s) in the list.";
    private static final String TASK_DELETED_MESSAGE = "Voila! ^_^ I've deleted this task:\n"
            + "- %s\nYou currently have %d task(s) in the list.";
    private static final String TASK_LIST = "Here are the task(s) in your list! ^_^\n";
    private static final String MATCHING_TASK_LIST = "Here are the matching task(s) in your list!\n";
    private static final String NO_MATCHING_TASKS = "No matching tasks found :<";
    private static final String SORT_TASK_LIST = "Task list is sorted!";

    private final ArrayList<Task> taskList;

    /**
     * Constructor for TaskHandler.
     *
     * @param taskList List of tasks.
     */
    public TaskHandler(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds the given task to the task list and informs user that
     * task is added successfully.
     *
     * @param task The task given to Duke.
     * @return String message informing user that task is added successfully.
     */
    public String addTask(Task task) {
        taskList.add(task);
        return String.format(TASK_ADDED_MESSAGE, task.toString(), getNumTotal());
    }

    /**
     * Gets the number of undone tasks.
     *
     * @return The number of undone tasks.
     */
    private int getNumUndone() {
        int count = 0;
        for (Task task : taskList) {
            if (!task.isComplete()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Gets the total number of tasks.
     *
     * @return The total number of tasks.
     */
    private int getNumTotal() {
        return taskList.size();
    }

    /**
     * Marks tasks as done.
     *
     * @param taskNumber The index of the task to be mark as done.
     * @return Message informing user task is marked as done.
     * @throws DukeException For invalid task indexes given by the user.
     */
    public String markTaskAsDone(int taskNumber) throws DukeException {
        if (taskNumber < 1) {
            throw new DukeInvalidIndexException();
        } else if (taskList.size() == 0) {
            throw new DukeException(NO_TASKS_FOUND);
        } else if (taskNumber > taskList.size()) {
            throw new DukeNoSuchTaskException();
        }
        int index = taskNumber - 1;
        Task t = taskList.get(index);
        t.markAsDone();
        updateData();
        return String.format(TASK_DONE_MESSAGE, t, getNumUndone());
    }

    /**
     * Deletes tasks at index specified by user.
     *
     * @param taskNumber The index of the task to be deleted.
     * @return Message informing user task is deleted.
     * @throws DukeIoException For invalid task index given by the user.
     */
    public String deleteTask(int taskNumber) throws DukeIoException {
        if (taskNumber < 1) {
            return new DukeInvalidIndexException().getMessage();
        } else if (taskList.size() == 0) {
            return new DukeException(NO_TASKS_FOUND).getMessage();
        } else if (taskNumber > taskList.size()) {
            return new DukeNoSuchTaskException().getMessage();
        }
        int index = taskNumber - 1;
        Task t = taskList.remove(index);
        updateData();
        return String.format(TASK_DELETED_MESSAGE, t, getNumTotal());
    }

    /**
     * Updates the storage after changes to the list.
     */
    private void updateData() throws DukeIoException {
        Storage.updateData(taskList);
    }

    /**
     * Generates the entire task list.
     *
     * @return String representation of the task list.
     */
    public String generateList() {
        StringBuilder allTasks = new StringBuilder(TASK_LIST);
        if (taskList.size() == 0) {
            return NO_TASKS_FOUND;
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                int taskIndex = i + 1;
                String task = taskList.get(i).toString();
                allTasks.append(String.format("\n%d. %s", taskIndex, task));
            }
            return allTasks.toString();
        }
    }

    /**
     * Filters task list with specific keyword and generates it.
     *
     * @param keyword Keyword specified by user.
     * @return String representation of filtered list.
     */
    public String generateFilteredList(String keyword) {
        StringBuilder matchingTasks = new StringBuilder(MATCHING_TASK_LIST);
        if (taskList.size() == 0) {
            return NO_TASKS_FOUND;
        } else {
            int index = 1;
            for (Task t : taskList) {
                if (t.toString().contains(keyword)) {
                    matchingTasks.append(String.format("\n%d. %s", index, t.toString()));
                    index++;
                }
            }
            if (index == 1) {
                return NO_MATCHING_TASKS;
            } else {
                return matchingTasks.toString();
            }
        }
    }

    /**
     * Sorts the task list in chronological order
     *
     * @param isReverse Reverse chronological order of task list if true.
     * @return Sorted task list.
     */
    public String sortTaskList(boolean isReverse) {
        Comparator<Task> cmp = (Task t1, Task t2) -> isReverse
                ? t1.compareTo(t2) * -1
                : t1.compareTo(t2);
        taskList.sort(cmp);
        return SORT_TASK_LIST + '\n' + generateList();
    }
}
