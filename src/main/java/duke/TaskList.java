package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that facilitates the organisation of tasks using a list.
 *
 * @author Toh Wang Bin
 */
public class TaskList {

    //The List containing the Tasks
    private List<Task> taskList;
    //An integer storing the number of tasks in the list
    private int taskNumber;

    /**
     * Constructor for a TaskList instance. Initialises the taskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
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
     * Deletes a Task from the taskList.
     *
     * @param number The index of the Task object to be deleted.
     */
    public void deleteTask(int number) {
        taskList.remove(number);
        taskNumber -= 1;
    }

    /**
     * Deletes a Task from the taskList.
     *
     * @param task The Task object to be deleted.
     */
    public void deleteTask(Task task) {
        taskList.remove(task);
        taskNumber -= 1;
    }

    /**
     * Adds a Task to the taskList.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
        taskNumber += 1;
    }

    /**
     * Retrieves the number of Tasks in the list.
     *
     * @return An integer representing the number of Tasks in the list.
     */
    public int getTaskNumber() {
        return taskNumber;
    }

    public void find(String string) {
        List<Task> list = new ArrayList<>();
        for (Task task: taskList) {
            if (task.name.contains(string)) {
                list.add(task);
            }
        }
        if (list.isEmpty()) {
            Ui.printNoMatch();
        } else {
            Ui.printMatch();
            int index = 1;
            for (Task task: list) {
                System.out.println(index + "." + task.toString());
                index += 1;
            }
        }

    }

}
