package duke.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import duke.exception.DukeException;
import duke.task.DateTimeTask;
import duke.task.Task;

/**
 * <h2>TaskList</h2>
 * Organises all tasks created by the user.
 * Handles details from the {@link duke.utility.Parser} class and adds, modifies, or deletes tasks accordingly.
 */

public class TaskList {

    public static final int MAX_NUMBER_OF_TASKS = 100;
    public static final String DUPLICATE_TASK_MESSAGE = "Task already in list!";
    public static final String TASK_ALREADY_COMPLETE_MESSAGE = "Task is already complete!!";
    public static final String MISSING_SEARCH_KEYWORD_MESSAGE = "Please enter some keywords to search for!";
    public static final String NO_SUCH_TASK_MESSAGE = "No tasks found containing the keyword(s) ";
    public static final String EMPTY_LIST_MESSAGE = "There are no items in your list!";
    public static final String DATE_FORMAT = "E, dd MMM yyyy";
    protected final HashSet<String> existingTasks;
    private final List<Task> tasks;


    /**
     * Creates a new taskList object with a capacity of 100 and a hashset to track tasks that are already in the list
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>(MAX_NUMBER_OF_TASKS);
        this.existingTasks = new HashSet<String>();
    }


    /**
     * Adds tasks from a provided list of tasks to this taskList. Used in conjunction with
     * {@link duke.utility.Storage} for loading from previously saved task logs
     * @param previousTasks a list of tasks to add to this taskList
     */
    public TaskList(List<Task> previousTasks) {
        this.tasks = new ArrayList<Task>(MAX_NUMBER_OF_TASKS);
        this.existingTasks = new HashSet<String>();
        for (Task previousTask : previousTasks) {
            this.tasks.add(previousTask);
            this.existingTasks.add(previousTask.getTaskName());
        }
    }


    protected String add(Task task) throws DukeException.DuplicateTaskException {
        if (this.existingTasks.contains(task.getTaskName())) {
            throw new DukeException.DuplicateTaskException(DUPLICATE_TASK_MESSAGE);
        }
        this.tasks.add(task);
        this.existingTasks.add(task.getTaskName());
        return String.format("New task added to list:\n%s", task);
    }


    protected String markAsCompleted(String taskName) throws DukeException.TaskAlreadyCompleteException,
            DukeException.NoSuchTaskException {
        // assert this.existingTask~s.contains(taskName) : "task to delete does not exist";
        int taskIndex = this.getTaskIndex(taskName);
        Task completedTask = this.tasks.get(taskIndex);
        if (completedTask.getIsCompleted()) {
            throw new DukeException.TaskAlreadyCompleteException(TASK_ALREADY_COMPLETE_MESSAGE);
        }
        this.tasks.remove(taskIndex);
        this.tasks.add(taskIndex, completedTask.markAsCompleted());
        return String.format("Task marked as completed:\n%s", this.tasks.get(taskIndex));
    }


    protected String deleteTask(int taskNum) throws DukeException.InvalidTaskNumException {
        // assert taskNum > 0 && taskNum <= this.tasks.size() : "invalid task number";
        if (taskNum > this.tasks.size() || taskNum < 1) {
            throw new DukeException.InvalidTaskNumException("Task number " + taskNum + " does not exist!");
        }
        Task toRemove = this.tasks.get(taskNum - 1);
        this.tasks.remove(taskNum - 1);
        this.existingTasks.remove(toRemove.getTaskName());
        return String.format("Successfully deleted:\n%s", toRemove);
    }


    protected int getTaskIndex(String taskName) throws DukeException.NoSuchTaskException {
        // assert this.existingTasks.contains(taskName) : "task does not exist";
        int currentTaskNum = 0;
        while (currentTaskNum < this.tasks.size()) {
            if (this.tasks.get(currentTaskNum).getTaskName().equals(taskName)) {
                return currentTaskNum;
            } else {
                currentTaskNum++;
            }
        }
        throw new DukeException.NoSuchTaskException("Task is not in list!");
    }

    protected String getTasksOnDate(LocalDate dateToView) throws DukeException.EmptyScheduleException {
        StringBuilder sb = new StringBuilder();
        for (Task task: this.tasks) {
            if (task instanceof DateTimeTask) {
                DateTimeTask t = (DateTimeTask) task;
                if (t.getDate().isEqual(dateToView)) {
                    sb.append(t);
                    sb.append("\n");
                }
            }
        }
        String date = dateToView.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        if (sb.length() == 0) {
            throw new DukeException.EmptyScheduleException(String.format("You have no tasks scheduled for %s", date));
        }
        return String.format("Here are your tasks scheduled for %s:\n\n", date) + sb;
    }


    protected String searchTasks(String keywords) throws DukeException.InvalidTaskDescriptionException,
            DukeException.NoSuchTaskException {
        if (keywords.equals("")) {
            throw new DukeException.InvalidTaskDescriptionException(MISSING_SEARCH_KEYWORD_MESSAGE);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            String taskName = this.tasks.get(i).getTaskName();
            if (taskName.contains(keywords)) {
                if (sb.length() != 0) {
                    sb.append("\n");
                }
                sb.append(i + 1);
                sb.append(". ");
                sb.append(this.tasks.get(i).toString());
            }
        }

        if (sb.length() == 0) {
            throw new DukeException.NoSuchTaskException(String.format(NO_SUCH_TASK_MESSAGE + "\"%s\"", keywords));
        }

        return String.format("Tasks found with names containing \"%s\" as a substring:\n", keywords) + sb;
    }

    /**
     * Aggregates all the tasks in the list and presents it neatly to output to the user.
     * @return all the tasks currently in the list as a <code>String</code> representation.
     * @throws DukeException.EmptyTaskListException if the taskList currently contains no tasks.
     */
    String getAllTasks() throws DukeException.EmptyTaskListException {
        StringBuilder sb = new StringBuilder();
        if (this.tasks.size() == 0) {
            throw new DukeException.EmptyTaskListException(EMPTY_LIST_MESSAGE);
        }
        sb.append("Your list contains:\n");
        for (int i = 0; i < this.tasks.size(); i++) {
            String itemNum = i + 1 + ". ";
            sb.append(itemNum);
            sb.append(this.tasks.get(i).toString());
            if (i < this.tasks.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
