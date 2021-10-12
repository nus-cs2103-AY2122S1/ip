package duke.classes;

import static java.lang.Integer.parseInt;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import duke.classes.tasks.Task;

/**
 * Lists of Tasks with Custom Functions
 */
public class TaskList {
    private static List<Task> taskList = new ArrayList<>();

    /**
     * Class Constructor
     *
     * @param taskList List of Tasks to be manipulated and saved in the object
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the List of Tasks
     *
     * @return Returns List of Tasks
     */
    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * Adds a Task to the List of Tasks
     *
     * @param task Task to be added into the List
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * //Can be optimized / cleaned @usage
     * Removes a task at a specific index and returns it
     *
     * @param index Index of the task to be removed
     * @return Returns the removed task
     */
    public Task remove(int index) {
        return taskList.remove(index);
    }

    /**
     * Retrieves the task at a specific index and returns it. Does not modify the list
     *
     * @param index Index of the task to be returned
     * @return Returns the task at the index
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Gets the size of the List
     *
     * @return Returns the size of the List of Tasks
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Gets the index of the last task in the list
     *
     * @return Returns the index of the last task in the list
     */
    public int last() {
        return taskList.size() - 1;
    }

    /**
     * Checks if the list of tasks if empty
     *
     * @return Returns boolean to indicate if the List isEmpty
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Checks if String is an int
     *
     * @param str Target string to check
     */
    public static boolean checkForInt(String str) {
        try {
            parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Completes the task at the specified index
     *
     * @param index Index of the task to complete
     */
    public void completeTask(int index) {
        taskList.get(index).markAsDone();
    }

    /**
     * Filters taskList for tasks containing input filter in the description
     *
     * @param filter String that contains the word(s) that the function filters by
     * @return Returns List of Tasks that contain tasks with the filter word(s) in their description
     */
    public static List<Task> filter(String filter) {
        return taskList.stream().filter(task -> task.descContains(filter))
                                .collect(Collectors.toList());
    }

    /**
     * Binary Function that processes String into separate Desc and Time.
     * The Desc and Time will be separated into 2 strings that will be returned in a list
     *
     * @param divider The pivot string to divide the input (\by or \at)
     * @param input   Array of Strings containing individual words that was input
     * @return Returns a list of 2 Strings,
     * index 0 contains description,
     * index 1 contains the date (and optionally time) of the event or the deadline
     */
    public static List<String> separateDesc(String divider, String[] input) {
        List<String> list = Arrays.asList(input);
        int index = list.indexOf(divider);
        String desc = "";
        String time = "";
        for (int i = 1; i < index; i++) {
            desc += input[i] + " ";
        }
        for (int i = index + 1; i < input.length; i++) {
            time += input[i] + " ";
        }
        List<String> temp = new ArrayList<>();
        temp.add(desc.trim());
        temp.add(time.trim());
        return temp;
    }

    /**
     * Returns toString() of the specified task
     */
    public String toString(int index) {
        return taskList.get(index).toString();
    }
}
