import java.util.ArrayList;
import java.util.List;

/**
 * This class encapsulates a Task Handler that manages all the tasks Duke can handle.
 *
 * @author Teo Sin Yee
 * @version CS2103T AY21/22 Semester 1
 */
public class TaskHandler {
    // Initialising constants
    private static final String NO_TASKS_FOUND = "Nothing in the list... :( Type todo/event/deadline to add something first! :^)";
    private static final String TASK_ADDED_MESSAGE = "Voila! ^_^ I've added this task:\n\t  %s\n\tYou currently have %d task(s) in the list.";
    private static final String TASK_DONE_MESSAGE = "Good Job! :D I've marked this task as done:\n\t  %s\n\tYou currently have %d undone task(s) in the list.";
    private static final String TASK_DELETED_MESSAGE = "Voila! ^_^ I've deleted this task:\n\t  %s\n\tYou currently have %d task(s) in the list.";
    private static final String TASK_LIST = "Here are the task(s) in your list! n_n\n\t";
    private static final String NO_SUCH_TASK_ID = "Awwww, I can't seem to find this task index. Try again? U_U";
    private static final String NEGATIVE_TASK_ID = "Please enter a number starting from 1! V_V";

    private List<Task> taskList;

    /** Instanbtiates a new Task Handler **/
    public TaskHandler() {
        taskList = new ArrayList<>();
    }

    /**
     * Adds the given task to the task list
     * and prints out a message that informs the user that task is added successfully.
     *
     * @param task the task given to Duke.
     */
    public void addTask(Task task) {
        taskList.add(task);
        Duke.prettify(String.format(TASK_ADDED_MESSAGE, task.toString(), getTotalTasksCount()));
    }

    /**
     * Gets the number of incomplete tasks
     *
     * @return the number of incomplete tasks.
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
     * @return the total number of tasks.
     */
    private int getTotalTasksCount() {
        return taskList.size();
    }

    /**
     * Marks tasks as done
     *
     * @param taskNumber the index of the task to be mark as done
     * @throws DukeException for invalid task indexes given by the user.
     */
    public void markTaskAsDone(int taskNumber) throws DukeException {
        if (taskNumber < 1) {
            throw new DukeException(NEGATIVE_TASK_ID);
        } else if (taskList.size() == 0) {
            throw new DukeException(NO_TASKS_FOUND);
        } else if (taskNumber > taskList.size()) {
            throw new DukeException(NO_SUCH_TASK_ID);
        }
        int index = taskNumber - 1;
        Task t = taskList.get(index);
        t.markAsDone();
        Duke.prettify(String.format(TASK_DONE_MESSAGE, t, getUndoneTasksCount()));
    }

    /**
     * Deletes tasks at indexes specified by user.
     *
     * @param taskNumber the index of the task to be deleted.
     * @throws DukeException for invalid task indexes given by the user.
     */
    public void deleteTask(int taskNumber) throws DukeException {
        if (taskNumber < 1) {
            throw new DukeException(NEGATIVE_TASK_ID);
        } else if (taskList.size() == 0) {
            throw new DukeException(NO_TASKS_FOUND);
        } else if (taskNumber > taskList.size()) {
            throw new DukeException(NO_SUCH_TASK_ID);
        }
        int index = taskNumber - 1;
        Task t = taskList.remove(index);
        Duke.prettify(String.format(TASK_DELETED_MESSAGE, t, getTotalTasksCount()));
    }

    /**
     * String representation of the task list, with the tasks indexed.
     *
     * @return string representation of the task list.
     */
    @Override
    public String toString() {
        String allTasks = TASK_LIST;
        if (taskList.size() == 0) {
            return NO_TASKS_FOUND;
        } else {
            for (int i  = 0; i < taskList.size(); i++) {
                int taskNumber =  i + 1;
                String taskName = taskList.get(i).toString();
                allTasks += String.format("\t%d. %s\n\t", taskNumber, taskName);
            }
        }
        return allTasks;
    }
}
