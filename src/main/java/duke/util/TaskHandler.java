package duke.util;

import duke.exception.DukeIOException;
import duke.task.Task;
import duke.exception.DukeException;
import duke.exception.DukeNoSuchTaskException;
import duke.exception.DukeInvalidIndexException;

import java.util.ArrayList;

/**
 * This class encapsulates a Task Handler that manages all the tasks Duke can handle.
 *
 * @author Teo Sin Yee
 * @version CS2103T AY21/22 Semester 1
 */
public class TaskHandler {
    private static final String NO_TASKS_FOUND = "Nothing in the list... :( Type todo/event/deadline to add something first! :^)";
    private static final String TASK_ADDED_MESSAGE = "Voila! ^_^ I've added this task:\n\t  %s\n\tYou currently have %d task(s) in the list.";
    private static final String TASK_DONE_MESSAGE = "Good Job! :D I've marked this task as done:\n\t  %s\n\tYou currently have %d undone task(s) in the list.";
    private static final String TASK_DELETED_MESSAGE = "Voila! ^_^ I've deleted this task:\n\t  %s\n\tYou currently have %d task(s) in the list.";
    private static final String TASK_LIST = "Here are the task(s) in your list! ^_^\n\t";
    private static final String MATCHING_TASK_LIST = "Here are the matching task(s) in your list! ^_^:\n\t";
    private static final String NO_MATCHING_TASKS = "No matching tasks found :<";

    private final ArrayList<Task> taskList;

    /**
     * Constructor for TaskHandler
     *
     * @param taskList List of tasks.
     */
    public TaskHandler(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds the given task to the task list.
     * Prints out a message that informs the user that task is added successfully.
     *
     * @param task The task given to Duke.
     */
    public void addTask(Task task) {
        taskList.add(task);
        Ui.prettify(String.format(TASK_ADDED_MESSAGE, task.toString(), getTotalTasksCount()));
    }

    /**
     * Gets the number of incomplete tasks.
     *
     * @return The number of incomplete tasks.
     */
    private int getUndoneTasksCount() {
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
    private int getTotalTasksCount() {
        return taskList.size();
    }

    /**
     * Marks tasks as done
     *
     * @param taskNumber The index of the task to be mark as done
     * @throws DukeException For invalid task indexes given by the user.
     */
    public void markTaskAsDone(int taskNumber) throws DukeException {
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
        Ui.prettify(String.format(TASK_DONE_MESSAGE, t, getUndoneTasksCount()));
    }

    /**
     * Deletes tasks at indexes specified by user.
     *
     * @param taskNumber The index of the task to be deleted.
     * @throws DukeException For invalid task indexes given by the user.
     */
    public void deleteTask(int taskNumber) throws DukeException {
        if (taskNumber < 1) {
            throw new DukeInvalidIndexException();
        } else if (taskList.size() == 0) {
            throw new DukeException(NO_TASKS_FOUND);
        } else if (taskNumber > taskList.size()) {
            throw new DukeNoSuchTaskException();
        }
        int index = taskNumber - 1;
        Task t = taskList.remove(index);
        Ui.prettify(String.format(TASK_DELETED_MESSAGE, t, getTotalTasksCount()));
    }

    /**
     * Updates the storage after changes to the list.
     *
     * @throws DukeException If unable to update task list.
     */
    public void updateData() throws DukeIOException {
        Storage.updateData(taskList);
    }

    /** Prints the task list in a pretty format **/
    public void printTasks()  {
        StringBuilder allTasks = new StringBuilder(TASK_LIST);
        if (taskList.size() == 0) {
            Ui.prettify(NO_TASKS_FOUND);
        } else {
            for (int i  = 0; i < taskList.size(); i++) {
                int taskIndex =  i + 1;
                String task = taskList.get(i).toString();
                allTasks.append(String.format("\t%d. %s\n\t", taskIndex, task));
            }
            Ui.prettify(allTasks.toString());
        }
    }

    /**
     * Filters and prints out all tasks with the keyword specified by user.
     *
     * @param keyword Keyword to filter task list with.
     */
    public void filterListByKeyword(String keyword) {
        StringBuilder matchingTasks = new StringBuilder(MATCHING_TASK_LIST);
        if (taskList.size() == 0) {
            Ui.prettify((NO_TASKS_FOUND));
        } else {
            int index = 1;
            for (Task t : taskList) {
                if (t.toString().contains(keyword)) {
                    matchingTasks.append(String.format("\t%d. %s\n\t", index, t.toString()));
                    index++;
                }
            }
            if (index == 1) {
                Ui.prettify(NO_MATCHING_TASKS);
            } else {
                Ui.prettify(matchingTasks.toString());
            }
        }
    }
}