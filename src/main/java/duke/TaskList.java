package duke;
import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

/**
 * Class to abstract the List of the Tasks
 */
public class TaskList {

    private final List<Task> taskList;

    /**
     * Constructor to initialize the Task List
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Constructor to initialize the Task List
     * @param storedTasks The Tasks initially Stored in the Duke
     */
    public TaskList(List<Task> storedTasks) {
        this.taskList = new ArrayList<>();
        taskList.addAll(storedTasks);
    }

    /**
     * Method to add a Task to the Task List
     * @param task The Task to be added in the Task List
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Method to return the deleted Task at the specified index
     * @param index The Index of the Task to be deleted
     * @return The deleted Task which was initially at the given Index
     */
    public Task deleteTaskAtIndex(int index) {
        return taskList.remove(index);
    }

    /**
     * Method to return the Task at the specified Index
     * @param index The Index of the Task to be returned
     * @return The Task at the specified Index
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Method to return the number of Tasks in the Task List
     * @return The number of Tasks in the Task List
     */
    public int getTaskListLength() {
        return taskList.size();
    }

    /**
     * Method to Mark the Task at the specified Index as Complete
     * @param index The Index of the Task to be Marked as Complete
     */
    public void completeTask(int index) {
        taskList.get(index).markAsCompleted();
    }

    /**
     * Method to find all the tasks of with the given search String
     * @param description The String to be searched
     * @return A list of tasks having the given search String
     */
    public TaskList searchTaskList(String description) {
        TaskList matchingTasks = new TaskList();
        for (Task task : taskList) {
            System.out.println(task);
            if (task.getDescription().contains(description)) {
                matchingTasks.addTask(task);
            }
        }
        return matchingTasks;
    }
}
