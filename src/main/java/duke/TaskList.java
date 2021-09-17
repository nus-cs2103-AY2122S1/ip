package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents a list of tasks with its operations.
 */
public class TaskList {
    private ArrayList<Task> ls;
    private ArrayList<Task> searchList;

    /**
     * Constructor to create a taskList object.
     *
     * @param ls The list of tasks.
     */
    TaskList(ArrayList<Task> ls) {
        this.ls = ls;
    }

    /**
     * Returns the description from a task entered by the user.
     *
     * @param arr The array that contains strings from the user input.
     * @return A string containing the description only.
     */
    public static String getDescription(String[] arr) {
        String str = "";
        for (int i = 1; i < arr.length; i++) {
            if (!(arr[i].charAt(0) == '/')) {
                str += arr[i] + " ";
            } else {
                break;
            }
        }
        return str;
    }

    /**
     * Returns the deadline from a task entered by the user.
     *
     * @param arr The array that contains strings from the user input.
     * @return A string containing the deadline only.
     */
    public static String getDeadline(String[] arr) throws DateTimeParseException {
        String str = "";
        boolean isSlash = false;
        for (int i = 1; i < arr.length; i++) {
            if (!isSlash) {
                if (arr[i].charAt(0) == '/') {
                    isSlash = true;
                    str += arr[i].substring(1) + ": ";
                }
            } else {
                if (arr[i].length() == 10 && arr[i].charAt(4) == '-' && arr[i].charAt(7) == '-') {
                    str += getDateDeadline(arr, i);
                } else if (arr[i].length() == 5 && arr[i].charAt(2) == ':') {
                    str += getTimeDeadline(arr, i);
                } else {
                    str += getNormalDeadline(arr, i);
                }
            }
        }
        return str;
    }

    /**
     * Converts a date input to a modified date output.
     *
     * @param arr The input from the user.
     * @param i The current index in the loop.
     * @return A string containing the modified date.
     */
    public static String getDateDeadline(String[] arr, int i) {
        String str = "";
        LocalDate ld = LocalDate.parse(arr[i]);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formatted = formatter.format(ld);
        return str + " " + formatted;
    }

    /**
     * Converts a time input to a modified time output.
     *
     * @param arr The input from the user.
     * @param i The current index in the loop.
     * @return A string containing the modified time.
     */
    public static String getTimeDeadline(String[] arr, int i) {
        String str = "";
        LocalTime lt = LocalTime.parse(arr[i]);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String formatted = formatter.format(lt);
        return str + " " + formatted;
    }

    /**
     * Extracts the deadline from the user input..
     *
     * @param arr The input from the user.
     * @param i The current index in the loop.
     * @return A string containing the deadline.
     */
    public static String getNormalDeadline(String[] arr, int i) {
        String str = arr[i];
        if (i != arr.length - 1) {
            str += " ";
        }
        return str;
    }

    /**
     * Adds a task to the collection of tasks.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        ls.add(task);
    }

    /**
     * Removes a task at a certain index in the collection.
     *
     * @param index Index of the task to be removed.
     */
    public void removeTask(int index) {
        System.out.println(index);
        ls.remove(index);
    }

    /**
     * Sets isBoolean to true.
     *
     * @param index Index of the task to be marked as done.
     */
    public void markAsDone(int index) {
        ls.get(index).markAsDone();
    }

    /**
     * Returns a task a certain index in the location.
     *
     * @param index Index of the task to be returned.
     * @return a Task at a certain index.
     */
    public Task getTask(int index) {
        return ls.get(index);
    }

    /**
     * Returns the size of the list.
     *
     * @return The size of the list.
     */
    public int getSize() {
        return ls.size();
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < getSize(); i++) {
            str += (i + 1) + "."
                    + getTask(i).toString().charAt(1)
                    + "|" + getTask(i).getStatus()
                    + " " + getTask(i).getDescription();
            if (getTask(i) instanceof Deadline || getTask(i) instanceof Event) {
                str += "(" + getTask(i).getDeadline() + ")";
            }
            if (i != getSize() - 1) {
                str += "\n";
            }
        }
        return str;
    }
}
