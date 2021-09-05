package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Represents a list of tasks with its operations.
 */
public class TaskList {
    private ArrayList<Task> ls;
    private ArrayList<Task> searchList;

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
        for(int i = 1; i < arr.length; i++) {
            if (!(arr[i].charAt(0) == '/')) {
                str += arr[i] + " ";
            }
            else {
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
        boolean bool = false;
        for(int i = 1; i < arr.length; i++) {
            if (!bool) {
                if (arr[i].charAt(0) == '/') {
                    bool = true;
                    str += arr[i].substring(1) + ": ";
                }
            } else {
                if (arr[i].length() == 10 && arr[i].charAt(4) == '-' && arr[i].charAt(7) == '-') {
                    LocalDate ld = LocalDate.parse(arr[i]);
                    str += ld.getDayOfMonth() + " "
                            + ld.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " "
                            + ld.getYear();
                    if (i != arr.length - 1) {
                        str += " ";
                    }
                } else if (arr[i].length() == 5 && arr[i].charAt(2) == ':') {
                    LocalTime lt = LocalTime.parse(arr[i]);
                    int hour = lt.getHour();
                    String hourSuffix = hour < 12 ? "AM" : "PM";
                    if (hour == 0) {
                        hour = 12;
                    } else if (hour > 12) {
                        hour -= 12;
                    }
                    int minute = lt.getMinute();
                    String minutePrefix = minute < 10 ? "0" : "";
                    str += hour + ":" + minutePrefix + minute + " " + hourSuffix;
                    if (i != arr.length - 1) {
                        str += " ";
                    }

                } else {
                    str += arr[i];
                    if (i != arr.length - 1) {
                        str += " ";
                    }
                }
            }
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

    /**
     * Prints the list of tasks.
     * @return A string that contains the list of tasks.
     */
    public String displayList() {
        if (ls.size() == 0) {
            return "You currently do not have any task!";
        } else {
            String str = "";
            str += "Here are the tasks in your list: \n ";
            for (int i = 0; i < ls.size(); i++) {
                str += (i + 1) + "." + ls.get(i).toString() + "\n";
            }
            return str;
        }
    }

    /**
     * Prints the acknowledgement and the current number of tasks in the list.
     * @return A string to show that a task has been added.
     */
    public String printAddTask() {
        String str = "";
        str += "Got it. I've added this task: \n";
        str += ls.get(ls.size() - 1).toString() + "\n";
        str += "Now you have " + ls.size() + " tasks in the list. \n";
        return str;
    }

    /**
     * Allows the user to find a task in the list.
     *
     * @param search The search term.
     * @return A string to show if a task is found in the list of not.
     */
    public String findTask(String search) {
        searchList = new ArrayList<>();
        for (int i = 0; i < ls.size(); i++) {
            String[] arr = ls.get(i).getDescription().split(" ");
            for(int j = 0; j < arr.length; j++) {
                if (arr[j].equals(search)) {
                    searchList.add(ls.get(i));
                    break;
                }
            }
        }
        if (searchList.size() == 0) {
            return "No matching tasks!";
        } else {
            String str = "";
            str += "Here are the matching tasks in your list: \n";
            for (int i = 0; i < searchList.size(); i++) {
                str += (i + 1) + "." + searchList.get(i).toString() + "\n";
            }
            return str;
        }
    }
}
