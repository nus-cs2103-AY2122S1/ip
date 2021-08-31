package duke;

import duke.tasks.Task;

import java.util.ArrayList;

/**
 * This class represents a list of Tasks.
 */
public class TaskList {
    ArrayList<Task> tasks;

    /**
     * Constructs a TaskList that contains all the tasks in the given param.
     * 
     * @param tasks ArrayList of Tasks that will be stored in the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs a TaskList with no Tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Prints the tasks in the TaskList.
     */
    public void printTaskList() {
        if (tasks.size() == 0) {
            System.out.println("There are no tasks in the list.");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
    
    protected int getLength() {
        return tasks.size();
    }

    /**
     * Adds a Task to the TaskList.
     * 
     * @param task Task to be added to the TaskList.
     */
    public void addNewTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks a Task with the given taskIndex as done and returns the Task.
     * 
     * @param taskIndex Index of the Task to be marked as done.
     * @return Task that was marked as done.
     * @throws DukeException
     */
    public Task markTaskDone(int taskIndex) throws DukeException {
        validateTaskIndex(taskIndex);
        Task task = tasks.get(taskIndex);
        task.markAsDone();
        return task;
    }

    /**
     * Deletes a Task and returns it.
     * 
     * @param taskIndex Index of Task to be deleted.
     * @return Task that was deleted.
     * @throws DukeException
     */
    public Task deleteTask(int taskIndex) throws DukeException {
        validateTaskIndex(taskIndex);
        Task task = tasks.get(taskIndex);
        tasks.remove(task);
        return task;
    }

    /**
     * Returns the String representations of all Tasks in an ArrayList in the format to be stored in a file.
     * 
     * @return ArrayList of String representations of all Tasks in TaskList in the format to be stored in a file.
     */
    public ArrayList<String> getTaskStrings() {
        ArrayList<String> taskStrings = new ArrayList<>();
        for (Task task : tasks) {
            taskStrings.add(task.toFileString());
        }
        return taskStrings;
    }
    
    private void validateTaskIndex(int taskIndex) throws DukeException { 
        int taskCount = tasks.size();
        if (taskCount == 0) {
            throw new DukeException("There are no tasks in the list.");
        } else if (taskIndex >= taskCount) {
            throw new DukeException("Invalid task number. There are only " + taskCount + "tasks in the list");
        }
    }

    /**
     * Returns true if object being compared is a TaskList that contains the same Tasks.
     * 
     * @param obj Object to be compared to TaskList.
     * @return true if object is equal to TaskList.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TaskList) {
            TaskList other = (TaskList) obj;
            return tasks.equals(other.tasks);
        }
        return false;
    }
}
