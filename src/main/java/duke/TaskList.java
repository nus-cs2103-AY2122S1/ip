package duke;

import task.Task;
import dukeException.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TaskList {
    /** The data structure to store all the tasks. **/
    public ArrayList<Task> tasks;

    /** A enum type indicating the type of the task. **/

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task add(Task.taskType type, String description, LocalDate date, LocalTime time) {
        Task newTask = null;
        if (type == Task.taskType.TODO) {
            newTask = new task.Todo(description);
        } else if (type == Task.taskType.DEADLINE) {
            newTask = new task.Deadline(description, date, time);
        } else if (type == Task.taskType.EVENT) {
            newTask = new task.Event(description, date, time);
        }
        tasks.add(newTask);
        return newTask;
    }

    public Task delete(int taskIndex) throws DukeException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new DukeException("task index out of bound");
        }
        Task deletedTask = tasks.get(taskIndex);
        tasks.remove(taskIndex);
        return deletedTask;
    }

    public Task markAsDone(int taskIndex) throws DukeException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new DukeException("task index out of bound");
        }
        tasks.get(taskIndex).markAsDone();
        return tasks.get(taskIndex);
    }

    /**
     * A method that list all current tasks.
     *
     * @return An array of String, each String contains a task.
     */
    public String[] listAllTasks() {
        int n = tasks.size();
        String taskList[] = new String[n + 1];
        taskList[0] = "Here are the tasks in your list:";
        for (int i = 1; i <= n; i++) {
            taskList[i] = String.format("  %d. %s", i, tasks.get(i - 1));
        }
        return taskList;
    }

    public int amountOfTasks() {
        return tasks.size();
    }

    public Task getTask(int taskIndex) throws DukeException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new DukeException("task index out of bound");
        }
        return tasks.get(taskIndex);
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }
}
