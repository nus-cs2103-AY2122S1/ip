package duke.tasktypes;

import java.util.ArrayList;

/**
 * TaskList containing all of user's tasks.
 */
public class TaskList {

    private final ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Retrieves taskList.
     *
     * @return ArrayList.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Gets the size of list.
     *
     * @return size of list.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Gets the task at specified index in list.
     *
     * @param index Index to be taken.
     * @return Task at index.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Removes the task from the list.
     *
     * @param index Index of task to be removed.
     */
    public void remove(int index) {
        taskList.remove(index);
    }

    /**
     * Adds the tasks to the list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Checks if there is an event that clashes with existing task list.
     *
     * @param dateOfEvent date of event to check.
     * @return Returns boolean value if there is indeed a clash.
     */
    public boolean clashChecker(String dateOfEvent) {
        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = this.taskList.get(i);
            if (task instanceof Event) {
                Event eventTask = (Event) task;
                String dateOfExistingEvent = eventTask.getDate();
                if (dateOfEvent.equals(dateOfExistingEvent)) {
                    return true;
                }
            }
        }
        return false;
    }

}
