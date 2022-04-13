package duke.main;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.exceptions.DucOutOfBoundException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


public class TaskList {

    private List<Task> taskList;

    /**
     * Constructor for TaskList
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Returns the TaskList itself
     * @return task list
     */
    public List<Task> getTaskList() {
        return this.taskList;
    }


    /**
     * Retrieve the size of the Task List in form of a String
     * @return size of task list
     */
    public int size() {
        return taskList.size();
    }


    /**
     * Indicates that a task in the list is finished
     * @param index index number of the queried task
     * @return confirmation that the task is completed
     */
    public Task done(int index) {
        if (index > taskList.size() || index <= 0) {
            throw new DucOutOfBoundException();
        } else {
            index--;
            Task doneTask = taskList.remove(index);
            doneTask.markAsCompleted();
            taskList.add(index, doneTask);
            return doneTask;
        }
    }


    /**
     * Indicates that all tasks in the list are completed
     */
    public void doneAll() {
        taskList = taskList.stream().map(Task::completedTask).collect(Collectors.toList());
    }

    /**
     * Deletes a specified task on the task list
     * @param index index of the task queried from the list
     * @return Confirmation that the specified task is deleted from the list
     */
    public Task delete(int index) {
        if (index > taskList.size() || index <= 0) {
            throw new DucOutOfBoundException();
        } else {
            index--;
            return taskList.remove(index);
        }
    }

    public Task get(int index) {
        if (index > taskList.size() || index <= 0) {
            throw new DucOutOfBoundException();
        } else {
            index--;
            return taskList.get(index);
        }
    }

    /**
     * Deletes all tasks and reset the Task List to empty
     */
    public void deleteAll() {
        taskList.clear();
        assert (taskList.isEmpty()) : "Is not empty";
    }

    /**
     * Adds a particular task into the list
     * @param task details of the task being added
     * @param type type of task being added (TODO, DEADLINE, or EVENT)
     * @return Confirmation that a task is added
     */
    public Task addTask(String task, Task.Type type) {
        task = task.trim();
        Task newTask;
        switch (type) {
        case DEADLINE:
            newTask = new Deadline(task);
            break;
        case EVENT:
            newTask = new Event(task);
            break;
        default:
            newTask = new Todo(task);
        }
        this.taskList.add(newTask);
        return newTask;
    }

    /**
     * Adds a Task object to the list (for shortcutting purposes)
     * @param task the task being added
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Updates a task at a specify index number
     * @param task Task description to be replaced
     * @param type Type of task being replaced
     * @param index Index number indicated
     * @return task replacement
     */
    public Task replaceTask(String task, Task.Type type, int index) {
        if (index <= 0 || index > taskList.size()) {
            throw new DucOutOfBoundException();
        }
        task = task.trim();
        Task newTask;
        switch (type) {
        case DEADLINE:
            newTask = new Deadline(task);
            break;
        case EVENT:
            newTask = new Event(task);
            break;
        default:
            newTask = new Todo(task);
        }
        index--;
        Task deleted = this.taskList.remove(index);
        assert (!taskList.contains(deleted));
        this.taskList.add(index, newTask);
        return newTask;
    }

    /**
     * Represents a task list by string
     * @return String representation of a task list
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("Here is the list of all tasks: \n");
        for (int i = 0; i < taskList.size(); i++) {
            output.append(i + 1).append(". ").append(taskList.get(i)).append("\n");
        }
        return output.toString();
    }
}
