package duke;

import java.util.ArrayList;

/**
 * Encapsulates the TodoList which is the overall list of all Tasks in Duke.
 */
public class TaskList {

    private static ArrayList<Task> dukeList;
    private static Data data;

    /**
     * Constructor for TaskList
     *
     * @param dukeList List of Tasks.
     * @param data     Data object.
     */
    public TaskList(ArrayList<Task> dukeList, Data data) {
        TaskList.dukeList = dukeList;
        TaskList.data = data;
    }

    public static void add(Task task) {
        dukeList.add(task);
    }

    /**
     * Shows all Tasks in the list that the user has given to Duke to store.
     * Tasks are ordered from least recent to most recent. If no tasks have been given to Duke,
     * the appropriate message is shown.
     */
    public static String showList() {
        String showListText = "Here are the tasks in your list:";
        String emptyListText = "☹ Oops! Looks like you have no tasks in your list!";

        if (dukeList.size() == 0) {
            return emptyListText;
        } else {
            for (int i = 0; i < dukeList.size(); i++) {
                showListText += "\n" + (i + 1) + "." + dukeList.get(i).toString();
            }
            return showListText;
        }
    }

    /**
     * Gets the number of Tasks in the TodoList.
     *
     * @return An integer representing the number of Tasks.
     */
    public static int numberOfTasks() {
        return TaskList.dukeList.size();
    }

    /**
     * Gets size of dukeList
     *
     * @return
     */
    public int getSize() {
        return dukeList.size();
    }

    /**
     * Remove the task at index i in dukeList.
     *
     * @param i index of task to be removed.
     * @return
     */
    public Task removeTask(int i) {
        return dukeList.remove(i);
    }

    /**
     * Check if dukeList is empty.
     *
     * @return Boolean relating to if dukeList is empty or not.
     */
    public boolean isEmpty() {
        return dukeList.isEmpty();
    }

    /**
     * Return Task at index i of dukeList
     *
     * @param i index of task in dukeList.
     * @return Task
     */
    public Task getTask(int i) {
        return dukeList.get(i);
    }

    /**
     * Searches the entire dukeList for Tasks that contain the keyword.
     *
     * @param keyword User input that they wish to search for.
     * @return An ArrayList of Tasks that contain the keyword.
     */
    public static ArrayList<Task> searchList(String keyword) {
        ArrayList<Task> results = new ArrayList<>();
        for (Task task : dukeList) {
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(task);
            }
        }
        return results;
    }
}
