package duke;

import java.util.ArrayList;

/**
 * duke.Tasklist creates the list of tasks for Duke.
 */

public class Tasklist {
    public static ArrayList<Task> dukeList;

    public Tasklist() {
        dukeList = new ArrayList<>();
    }

    /**
     * Add a task to the list of tasks.
     * @param task - the task which is to be added in the list
     */
    public static void add(Task task) {
        dukeList.add(task);
    }

    /**
     * Removing a task from the list of the tasks.
     * @param taskIndex - the index of the task to be deleted
     */
    public static void delete(int taskIndex) {
        dukeList.remove(taskIndex);
    }

    /**
     * Find the Tasks in the list which contain the keyword in their
     * description.
     * @param keyword   keyword to look for in the descriptions
     * @return          the list of Tasks which have the keyword
     */
    public static ArrayList<Task> find(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < dukeList.size(); i++ ) {
            if(dukeList.get(i).getDescription().contains(keyword)) {
                foundTasks.add(dukeList.get(i));
            }
        }
        return foundTasks;
    }

}
