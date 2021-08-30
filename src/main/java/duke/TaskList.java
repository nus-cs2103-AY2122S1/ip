package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import duke.exception.DukeException;
import task.Task;



public class TaskList {
    /** The data structure to store all the tasks. **/
    private ArrayList<Task> tasks;

    /**
     * A public constructor to initialized the TaskList by declaring a new empty ArrayList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * A public constructor to initialized the TaskList by
     * giving a list of tasks loaded from the file.
     *
     * @param tasks An ArrayList of Task, the initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * A method to insert a new task to the TaskList.
     *
     * @param type The type of the task.
     * @param description A String, the description of the task.
     * @param date The date of the task.
     * @param time The time of the task.
     * @return A Task object, the inserted task.
     */
    public Task add(Task.TaskType type, String description, LocalDate date, LocalTime time) {
        Task newTask = null;
        if (type == Task.TaskType.TODO) {
            newTask = new task.Todo(description);
        } else if (type == Task.TaskType.DEADLINE) {
            newTask = new task.Deadline(description, date, time);
        } else if (type == Task.TaskType.EVENT) {
            newTask = new task.Event(description, date, time);
        }
        tasks.add(newTask);
        return newTask;
    }

    /**
     * A method to remove a task from the TaskList.
     *
     * @param taskIndex The index of the task to be deleted.
     * @return A Task object, the deleted task.
     * @throws DukeException Exception thrown when taskIndex is greater than total number of tasks.
     */
    public Task delete(int taskIndex) throws DukeException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new DukeException("task index out of bound");
        }
        Task deletedTask = tasks.get(taskIndex);
        tasks.remove(taskIndex);
        return deletedTask;
    }

    /**
     * A method to mark a task as done.
     *
     * @param taskIndex The index of the task.
     * @return A Task object, the task marked as done.
     * @throws DukeException Exception thrown when taskIndex is greater than total number of tasks.
     */
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
        String[] taskList = new String[n + 1];
        taskList[0] = "Here are the tasks in your list:";
        for (int i = 1; i <= n; i++) {
            taskList[i] = String.format("  %d. %s", i, tasks.get(i - 1));
        }
        return taskList;
    }

    /**
     * A method to get the total number of tasks in the data structure.
     *
     * @return An integer, the total number of tasks.
     */
    public int amountOfTasks() {
        return tasks.size();
    }

    /**
     * A method to get a certain task by entering its index.
     *
     * @param taskIndex The index of the task.
     * @return A Task object, the target task.
     * @throws DukeException Exception thrown when taskIndex is greater than total number of tasks.
     */
    public Task getTask(int taskIndex) throws DukeException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new DukeException("task index out of bound");
        }
        return tasks.get(taskIndex);
    }

    /**
     * A method to get the data structure storing the tasks.
     *
     * @return An ArrayList of Task, the data structure.
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }
}
