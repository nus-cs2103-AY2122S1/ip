package duke;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;
/**
 * A class that facilitates the organisation of tasks using a list.
 *
 * @author Toh Wang Bin
 */
public class TaskList {

    //The List containing the Tasks
    private List<Task> taskList;
    //An integer storing the number of tasks in the list
    private int totalTasks;

    /**
     * Constructor for a TaskList instance. Initialises the taskList.
     */
    public TaskList() {
        taskList = new ArrayList<>();
        totalTasks = 0;
    }

    /**
     * Retrieves a specific Task from the taskList.
     *
     * @param index The index of the Task to be retrieved.
     * @return The Task object to be retrieved.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Deletes a Task from the taskList, using its index in the list.
     *
     * @param number The index of the Task object to be deleted.
     */
    public void deleteTask(int number) {
        taskList.remove(number);
        totalTasks -= 1;
        assert totalTasks >= 0 : "Number of Tasks should be more than or equals to 0";
    }

    /**
     * Deletes a Task from the taskList, using its instance.
     *
     * @param task The Task object to be deleted.
     */
    public void deleteTask(Task task) {
        taskList.remove(task);
        totalTasks -= 1;
        assert totalTasks >= 0 : "Number of Tasks should be more than or equals to 0";
    }

    /**
     * Adds a Task to the taskList.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
        totalTasks += 1;
    }

    /**
     * Retrieves the number of Tasks in the list.
     *
     * @return An integer representing the number of Tasks in the list.
     */
    public int getTotalTasks() {
        return totalTasks;
    }

    /**
     * Searches for a input string among stored Tasks, and returns a string indicating the results.
     *
     * @param string The input string to search for matches.
     * @return A string representing the result.
     */
    public String find(String string) {
        List<Task> list = new ArrayList<>();
        for (Task task: taskList) {
            if (task.getName().contains(string)) {
                list.add(task);
            }
        }
        if (list.isEmpty()) {
            return Ui.getNoMatch();
        } else {
            StringBuilder str = new StringBuilder();
            str.append(Ui.getMatch());

            int index = 1;
            for (Task task: list) {
                str.append(index + "." + task.toString() + "\n");
                index += 1;
            }
            return str.toString();
        }

    }

}
