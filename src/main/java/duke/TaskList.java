package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

import duke.task.Task;

/**
 * A class that keeps track of the tasks in the form of ArrayList of duke.task.Task objects.
 * It contains methods to provide information and execute operations over the ArrayList stored.
 *
 * @author Gu Geng
 */
public class TaskList {
    private ArrayList<duke.task.Task> taskList;

    /**
     * Returns a TaskList instance using an ArrayList of duke.task.Task objects.
     *
     * @param taskList A ArrayList of duke.task.Task objects.
     */
    public TaskList(ArrayList<duke.task.Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns a TaskList instance with an empty ArrayList of duke.task.Task objects.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Overrides the toString method to change the String representation of the TaskList.
     *
     * @return A String as the String representation of the TaskList in specified format.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String entry = String.format("%d. %s\n",
                    i + 1, task.toString());
            result.append(entry);
        }
        return result.toString();
    }

    /**
     * Returns the size of the ArrayList contained in the class instance.
     *
     * @return An int object indicating the size of the current ArrayList.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns a String representation of the ArrayList after being filtered by a LocalDate Object.
     *
     * @param dateFilter A LocalDate object used to filter the ArrayList of Task objects.
     * @return A String representation of the ArrayList after being filtered by a LocalDate Object.
     */
    public String listSchedule(LocalDate dateFilter) {
        ArrayList<Task> filteredTaskList = taskList.stream()
                .filter(ele -> ele.hasSchedule()
                        && ele.getTime().equals(dateFilter.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))))
                .collect(Collectors.toCollection(ArrayList::new));
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < filteredTaskList.size(); i++) {
            Task task = filteredTaskList.get(i);
            String entry = String.format("%d. %s \n",
                    i + 1, task.toString());
            result.append(entry);
        }
        return result.toString();
    }

    /**
     * Returns a String representation of the ArrayList after being filtered by a String.
     * @param searchFilter A String used to filter the ArrayList of Task objects.
     * @return A String representation of the ArrayList after being filtered by a String.
     */
    public String listFind(String searchFilter) {
        ArrayList<Task> filteredTaskList = this.taskList.stream()
                .filter(ele -> ele.getContent().contains(searchFilter))
                .collect(Collectors.toCollection(ArrayList::new));
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < filteredTaskList.size(); i++) {
            Task task = filteredTaskList.get(i);
            String entry = String.format("%d. %s \n",
                    i + 1, task.toString());
            result.append(entry);
        }
        return result.toString();
    }

    /**
     * Marks the specified Task in the ArrayList as done.
     *
     * @param index An int indicating the position of Task in the ArrayList that is to be marked as done.
     */
    public void doneTask(int index) {
        Task holder = taskList.get(index);
        holder.doneTask();
    }

    /**
     * Deletes the specified Task in the ArrayList.
     *
     * @param index An int indicating the position of Task in the ArrayList to be deleted.
     */
    public void deleteTask(int index) {
        String holder = taskList.get(index).toString();
        taskList.remove(index);
        taskList.trimToSize();
    }

    /**
     * Adds the given Task to the ArrayList.
     *
     * @param task A duke.task.Task object that is to be added.
     */
    public void addTask(duke.task.Task task) {
        taskList.add(task);
    }

    /**
     * Returns a duke.task.Task object specified by the index given from the ArrayList.
     *
     * @param index An int indicating the position of Task in the ArrayList to be obtained.
     * @return A duke.task.Task object specified by the index given from the ArrayList.
     */
    public duke.task.Task getTask(int index) {
        return taskList.get(index);
    }
}
