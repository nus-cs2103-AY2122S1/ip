package duke.utility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * <h2>TaskList</h2>
 * Organises all tasks created by the user.
 * Handles details from the {@link duke.utility.Parser} class and adds, modifies, or deletes tasks accordingly.
 */

public class TaskList {

    protected final HashSet<String> existingTasks;
    private final List<Task> tasks;

    /**
     * Creates a new tasklist object with a capacity of 100 and a hashset to track tasks that are already in the list
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>(100);
        this.existingTasks = new HashSet<String>();
    }

    /**
     * Adds tasks from a provided list of tasks to this tasklist. Used in conjunction with
     * {@link duke.utility.Storage} for loading from previously saved task logs
     * @param previousTasks a list of tasks to add to this tasklist
     */
    public TaskList(List<Task> previousTasks) {
        this.tasks = new ArrayList<Task>(100);
        this.existingTasks = new HashSet<String>();
        for (Task previousTask : previousTasks) {
            this.tasks.add(previousTask);
            this.existingTasks.add(previousTask.getTaskName());
        }
    }

    protected String add(Task task) {
        this.tasks.add(task);
        this.existingTasks.add(task.getTaskName());
        return String.format("New task added to list:\n%s", task);
    }

    protected String markAsCompleted(String taskName) throws DukeException.TaskAlreadyCompleteException,
            DukeException.NoSuchTaskException {
        int taskIdx = this.getTaskIndex(taskName);
        Task completedTask = this.tasks.get(taskIdx);
        if (completedTask.getIsCompleted()) {
            throw new DukeException.TaskAlreadyCompleteException("Task is already complete!!");
        }
        this.tasks.remove(taskIdx);
        this.tasks.add(taskIdx, completedTask.markAsCompleted());
        return "Task marked as completed:\n" + this.tasks.get(taskIdx).toString();
    }

    protected String deleteTask(int taskNum) throws DukeException.InvalidTaskNumException {
        if (taskNum > this.tasks.size() || taskNum < 1) {
            throw new DukeException.InvalidTaskNumException("Task number " + taskNum + " does not exist!");
        } else {
            Task toRemove = this.tasks.get(taskNum - 1);
            this.tasks.remove(taskNum - 1);
            this.existingTasks.remove(toRemove.getTaskName());
            return "Successfully deleted:\n" + toRemove;
        }
    }

    /**
     * Aggregates all the tasks in the list and presents it neatly to output to the user.
     * @return all the tasks currently in the list as a <code>String</code> representation.
     * @throws DukeException.EmptyTaskListException if the taskList currently contains no tasks.
     */
    public String getAllTasks() throws DukeException.EmptyTaskListException {
        StringBuilder sb = new StringBuilder();
        if (this.tasks.size() == 0) {
            throw new DukeException.EmptyTaskListException("There are no items in your list!");
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

    protected int getTaskIndex(String taskName) throws DukeException.NoSuchTaskException {
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

    protected String search(String keywords) throws DukeException.InvalidTaskDescriptionException,
            DukeException.NoSuchTaskException {
        if (keywords.equals("")) {
            throw new DukeException.InvalidTaskDescriptionException("Please enter some keywords to search for!");
        } else {
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
                throw new DukeException.NoSuchTaskException(String.format("No tasks found containing the keyword(s) "
                        + "\"%s\"", keywords));
            } else {
                return String.format("Tasks found with names containing \"%s\" as a substring:\n", keywords) + sb;
            }
        }
    }
}
