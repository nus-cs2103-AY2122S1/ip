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
     * Adds a task to the list of tasks.
     *
     * @param task - the task which is to be added in the list
     */
    public static void add(Task task) {
        dukeList.add(task);
    }

    /**
     * Removes a task from the list of the tasks.
     *
     * @param taskIndex - the index of the task to be deleted
     */
    public static void delete(int taskIndex) {
        dukeList.remove(taskIndex);
    }

    /**
     * Finds the Tasks in the list which contain the keyword in their
     * description.
     *
     * @param keyword keyword to look for in the descriptions
     * @return the list of Tasks which have the keyword
     */
    public static ArrayList<Task> find(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < dukeList.size(); i++) {
            if (dukeList.get(i).getDescription().contains(keyword)) {
                foundTasks.add(dukeList.get(i));
            }
        }
        return foundTasks;
    }

    /**
     * Updates the description of the task.
     *
     * @param taskIndex index of the task to be updated
     * @param newDescription new description of the task
     */
    public static void updateDescription(int taskIndex, String newDescription) {
        Task currentTask = dukeList.get(taskIndex);
        currentTask.description = newDescription;
    }

    /**
     * Updates the time of the task.
     *
     * @param taskIndex index of the task to be updated
     * @param newTime new time of the task
     */
    public static void updateTime(int taskIndex, String newTime) {
        Task currentTask = dukeList.get(taskIndex);
        if (currentTask instanceof ToDo) {
            // error
        } else if (currentTask instanceof Event) {
            ((Event)currentTask).time = newTime;
        } else if(currentTask instanceof Deadline) {
            ((Deadline)currentTask).time = newTime;
        }
    }

}
