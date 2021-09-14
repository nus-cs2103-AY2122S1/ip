package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.List;

/**
 * A TaskList class to store tasks.
 * @author KelvinSoo
 * @version A-MoreOOP
 */
public class TaskList {

    private List<Task> taskList;

    /**
     * A constructor to initialize a new task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * A constructor to initialize a given task list.
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * A method to get the task list.
     * @return The task list.
     */
    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * A method to add a task to the list.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * A method to delete a task from the list.
     * @param taskNo The task number to be removed.
     */
    public void deleteTask(int taskNo) throws DukeException {
        if (!   isValidTask(taskNo)) {
            // Task does not exist
            throw new DukeException(String.format("Task %d does not exist.\nUse \"list\" to see all tasks.", taskNo));
        }
        taskList.remove(taskNo - 1);
    }

    /**
     * A method to get the size of the task list.
     * @return int The size of the list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * A method to get a task from the task list.
     * @return Task The task to get.
     */
    public Task getTask(int i) throws DukeException {
        try {
            return taskList.get(i - 1);
        } catch (Exception e) {
            throw new DukeException(String.format("OOPS!!! Task %d does not exist.", i));
        }
    }

    /**
     * A method to mark a task as done.
     * @param taskNo The task to be marked.
     */
    public void markAsDone(int taskNo) throws DukeException {
        if (!isValidTask(taskNo)) {
            // Task does not exist
            throw new DukeException(String.format("Task %d does not exist.\nUse \"list\" to see all tasks.", taskNo));
        }
        taskList.get(taskNo - 1).markAsDone();
    }

    /**
     * A method to tag a task.
     * @param taskNo The task to be tagged.
     */
    public void tagTask(int taskNo, String tagDescription) throws DukeException {
        if (!isValidTask(taskNo)) {
            // Task does not exist
            throw new DukeException(String.format("Task %d does not exist.\nUse \"list\" to see all tasks.", taskNo));
        }
        taskList.get(taskNo - 1).addTag(tagDescription);
    }

    /**
     * A method to check if task is valid.
     * @param taskNo The task to be marked.
     */
    public boolean isValidTask(int taskNo) {
        return taskNo > 0 && taskNo - 1 <= taskList.size();
    }

    /**
     * A method to output all tasks as a string.
     * @return  String All the task as a string.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            Task task = taskList.get(i);
            sb.append(String.format("%s. %s %s \n", i + 1, task.getStatusIcon(), task.getDescription()));
        }
        return sb.toString();
    }
}
