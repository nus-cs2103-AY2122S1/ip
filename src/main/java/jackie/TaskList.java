package jackie;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

import jackie.task.Task;

/**
 * A class that keeps track of the tasks in the form of ArrayList of jackie.task.Task objects.
 * It contains methods to provide information and execute operations over the ArrayList stored.
 *
 * @author Gu Geng
 */
public class TaskList {
    private ArrayList<jackie.task.Task> taskList;
    private ArrayList<jackie.task.Task> previousList;

    /**
     * Returns a TaskList instance using an ArrayList of jackie.task.Task objects.
     *
     * @param taskList A ArrayList of jackie.task.Task objects.
     */
    public TaskList(ArrayList<jackie.task.Task> taskList) {
        this.taskList = taskList;
        previousList = taskList;
    }

    /**
     * Returns a TaskList instance with an empty ArrayList of jackie.task.Task objects.
     */
    public TaskList() {
        taskList = new ArrayList<>();
        previousList = new ArrayList<>();
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
        // stream here
        ArrayList<Task> filteredTaskList = taskList.stream()
                .filter(ele -> ele.hasSchedule()
                        && scheduleFormatChecker(ele, dateFilter))
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

    private boolean scheduleFormatChecker(jackie.task.Task task, LocalDate dateFilter) {
        return task.getTime().equals(dateFilter.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }

    /**
     * Returns a String representation of the ArrayList after being filtered by a String.
     * @param searchFilter A String used to filter the ArrayList of Task objects.
     * @return A String representation of the ArrayList after being filtered by a String.
     */
    public String listFind(String searchFilter) {
        // stream here
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
    public void doneTask(int index) throws JackieException {
        previousList = taskList.stream().map(x -> x).collect(Collectors.toCollection(ArrayList::new));
        Task holder = (Task) taskList.get(index).getCopy();
        holder.doneTask();
        taskList.remove(index);
        taskList.add(index, holder);
    }

    /**
     * Deletes the specified Task in the ArrayList.
     *
     * @param index An int indicating the position of Task in the ArrayList to be deleted.
     */
    public void deleteTask(int index) {
        previousList = taskList.stream().map(x -> x).collect(Collectors.toCollection(ArrayList::new));
        String holder = taskList.get(index).toString();
        taskList.remove(index);
        taskList.trimToSize();
    }

    /**
     * Adds the given Task to the ArrayList.
     *
     * @param task A jackie.task.Task object that is to be added.
     */
    public void addTask(jackie.task.Task task) {
        previousList = taskList.stream().map(x -> x).collect(Collectors.toCollection(ArrayList::new));
        taskList.add(task);
    }

    /**
     * Returns a jackie.task.Task object specified by the index given from the ArrayList.
     *
     * @param index An int indicating the position of Task in the ArrayList to be obtained.
     * @return A jackie.task.Task object specified by the index given from the ArrayList.
     */
    public jackie.task.Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Undos the last operation done and restores the previous taskList.
     *
     * @return A boolean indicating if the undone operation is done.
     */
    public boolean ifUndone() {
        if (!previousList.equals(taskList) && !ifStatusSame()) {
            taskList = previousList;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns a boolean indicating if all status of the tasks in both versions are same.
     *
     * @return a boolean indicating if all status of the tasks in both versions are same.
     */
    private boolean ifStatusSame() {
        if (taskList.size() != previousList.size()) {
            return false;
        }
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getStatus() != previousList.get(i).getStatus()) {
                return false;
            }
        }
        return true;
    }
}
